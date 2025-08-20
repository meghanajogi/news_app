import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.news_app.data.model.NewsItem
import com.example.news_app.data.repository.NewsRepository
import com.example.news_app.presentation.viewmodel.NewsViewModel
import com.example.news_app.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
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

@OptIn(ExperimentalCoroutinesApi::class)
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
        val mockItems = listOf(
           NewsItem(id=1, userId=1, title="sunt aut facere repellat provident occaecati excepturi optio reprehenderit", body="quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto")

        )

        // Mock Flow return from repository
        `when`(mockRepository.getNews()).thenReturn(flowOf(Resource.Success(mockItems)))

        viewModel.loadItems()

        advanceUntilIdle()

        val result = viewModel.items.first()
        assertTrue(result is Resource.Success)
        assertEquals(mockItems, (result as Resource.Success).data)
    }


    @Test
    fun `test loadItems handles empty list`() = runTest {
        // Arrange
        val emptyList = emptyList<NewsItem>()
        `when`(mockRepository.getNews()).thenReturn(flowOf(Resource.Success(emptyList)))

        // Act
        viewModel.loadItems()
        advanceUntilIdle()

        // Assert
        val result = viewModel.items.first()
        assertTrue(result is Resource.Success)
        assertTrue((result as Resource.Success).data?.isEmpty() == true)
    }


    @Test
    fun `test loadItems handles error gracefully`() = runTest {
        `when`(mockRepository.getNews()).thenReturn(flowOf(Resource.Error("Network Error")))

        viewModel.loadItems()
        advanceUntilIdle()

        val result = viewModel.items.first()
        assertTrue(result is Resource.Error)
        assertEquals("Network Error", (result as Resource.Error).message)
    }


    @Test
    fun `test loadItemById fetches a specific item`() = runTest {
        val item= NewsItem(id=1, userId=1, title="sunt aut facere repellat provident occaecati excepturi optio reprehenderit", body="quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto")


        `when`(mockRepository.getItemById(1)).thenReturn(flowOf(Resource.Success(item)))

        viewModel.loadItemById(1)
        advanceUntilIdle()

        val result = viewModel.selectedItem.first()
        assertTrue(result is Resource.Success)
        assertEquals(item, (result as Resource.Success).data)
    }



    @Test
    fun `test loadItemById returns error for non-existent item`() = runTest {
        `when`(mockRepository.getItemById(99)).thenReturn(flowOf(Resource.Error("Item not found")))

        viewModel.loadItemById(99)
        advanceUntilIdle()

        val result = viewModel.selectedItem.first()
        assertTrue(result is Resource.Error)
        assertEquals("Item not found", (result as Resource.Error).message)
    }


    @After
    fun tearDown() {
        // Reset the dispatcher after the test
        Dispatchers.resetMain()
    }
}
