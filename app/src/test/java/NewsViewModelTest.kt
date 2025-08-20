import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.news_app.data.model.NewsItem
import com.example.news_app.data.repository.NewsRepository
import com.example.news_app.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.junit.Assert.*


class NewsViewModelTest {

    // Rule to allow live data to work synchronously
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Create a TestDispatcher to control coroutines
    private val testDispatcher = StandardTestDispatcher()

    // ViewModel to test
    private lateinit var viewModel: NewsViewModel

    // Mock the repository
    @Mock
    private lateinit var mockRepository: NewsRepository

    // Initialize Mockito mocks
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        // Set the dispatcher to our test dispatcher to run coroutines synchronously
        Dispatchers.setMain(testDispatcher)

        // Create ViewModel with the mock repository
        viewModel = NewsViewModel(mockRepository)
    }

    @Test
    fun `test loadItems returns a list of items successfully`() = runTest {
        // Arrange
        val mockItems = listOf(
            NewsItem(id=1, userId=1, title="sunt aut facere repellat provident occaecati excepturi optio reprehenderit", body="quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto")
        )
        `when`(mockRepository.getNews()).thenReturn(mockItems)

        // Act
        viewModel.loadItems()

        // Assert
        val result = viewModel.items.first() // Collect the items from the StateFlow
        assertEquals(mockItems, result)
    }

    @Test
    fun `test loadItems handles empty list`() = runTest {
        // Arrange
        val emptyList = emptyList<NewsItem>()
        `when`(mockRepository.getNews()).thenReturn(emptyList)

        // Act
        viewModel.loadItems()

        // Assert
        val result = viewModel.items.first()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `test loadItems handles error gracefully`() = runTest {
        // Arrange
        `when`(mockRepository.getNews()).thenThrow(RuntimeException("Network Error"))

        // Act
        viewModel.loadItems()

        // Assert
        val result = viewModel.items.first()
        assertTrue(result.isEmpty())  // You can assert for a default empty list or handle error gracefully in your ViewModel
    }

    @Test
    fun `test loadItemById fetches a specific item`() = runTest {


       val item= NewsItem(id=1, userId=1, title="sunt aut facere repellat provident occaecati excepturi optio reprehenderit", body="quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto")
        // Arrange
       // val item = NewsItem(1, 1, "Test Title", "Test Description")
        `when`(mockRepository.getItemById(1)).thenReturn(item)

        // Act
        viewModel.loadItemById(1)

        advanceUntilIdle()

        // Assert
        val result = viewModel.selectedItem.first()
        assertEquals(item, result)
    }

    @Test
    fun `test loadItemById returns null for non-existent item`() = runTest {
        // Arrange
        `when`(mockRepository.getItemById(99)).thenThrow(NoSuchElementException("Item not found"))

        // Act
        viewModel.loadItemById(99)

        // Assert
        val result = viewModel.selectedItem.first()
        assertNull(result)
    }

    @After
    fun tearDown() {
        // Reset the dispatcher after the test
        Dispatchers.resetMain()
    }
}
