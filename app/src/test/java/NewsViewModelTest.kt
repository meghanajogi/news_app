import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.news_app.data.repository.NewsRepository
import com.example.news_app.presentation.viewmodel.NewsViewModel
import com.example.news_app.data.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
    private val mockRepository: NewsRepository = mock()


    @OptIn(ExperimentalCoroutinesApi::class)
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
            Item(1, "Item 1", "item1@example.com", body = "news1"),
            Item(2, "Item 2", "item2@example.com",body="news2")
        )
        whenever(mockRepository.getUsers()).thenReturn(mockItems)

        // Act
        viewModel.loadItems()

        // Assert
        val result = viewModel.items.first()  // Collect the items from the StateFlow
        assertEquals(mockItems, result)
    }

    @Test
    fun `test loadItems handles empty list`() = runTest {
        // Arrange
        val emptyList = emptyList<Item>()
        whenever(mockRepository.getUsers()).thenReturn(emptyList)

        // Act
        viewModel.loadItems()

        // Assert
        val result = viewModel.items.first()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `test loadItems handles error gracefully`() = runTest {
        // Arrange
        whenever(mockRepository.getUsers()).thenThrow(RuntimeException("Network Error"))

        // Act
        viewModel.loadItems()

        // Assert
        val result = viewModel.items.first()
        assertTrue(result.isEmpty())  // You can assert for a default empty list or handle error gracefully in your ViewModel
    }

}

