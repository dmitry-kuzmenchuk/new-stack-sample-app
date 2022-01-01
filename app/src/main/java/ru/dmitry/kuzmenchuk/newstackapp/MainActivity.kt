package ru.dmitry.kuzmenchuk.newstackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.dmitry.kuzmenchuk.newstackapp.data.data_source.UnsplashRepository
import ru.dmitry.kuzmenchuk.newstackapp.data.retrofit.UnsplashRequestInterceptor
import ru.dmitry.kuzmenchuk.newstackapp.domain.interactor.UnsplashInteractor
import ru.dmitry.kuzmenchuk.newstackapp.ui.main.MainEvent
import ru.dmitry.kuzmenchuk.newstackapp.ui.main.MainViewModel
import ru.dmitry.kuzmenchuk.newstackapp.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val retrofit: Retrofit = initRetrofit()

    private val unsplashInteractor: UnsplashInteractor = initUnsplashInteractor()

    private val viewModelFactory: ViewModelProvider.Factory = initViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val viewModel by viewModels<MainViewModel> { viewModelFactory }
                val state by viewModel.stateFlow.collectAsState()

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val imageVector = ImageVector.vectorResource(id = R.drawable.ic_surf)
                    val surfLogo = rememberVectorPainter(image = imageVector)

                    LaunchedEffect(Unit) {
                        viewModel.sendEvent(MainEvent.EditorialPhotosRequested)
                    }

                    Image(painter = surfLogo, contentDescription = null)
                    Greeting("Android")
                }
            }
        }
    }

    private fun initRetrofit(): Retrofit {
        val unsplashRequestInterceptor = UnsplashRequestInterceptor(
            apiToken = "LT_iCBkGAix5X-LKQ3fC8xNnGVwiBSCUEUB4TKxzL2o",
            apiVersion = 1
        )
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(unsplashRequestInterceptor)
            .addInterceptor(HttpLoggingInterceptor())
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .client(okHttpClient)
            .build()
    }

    private fun initUnsplashInteractor(): UnsplashInteractor {
        return UnsplashInteractor(UnsplashRepository(retrofit))
    }

    private fun initViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    MainViewModel::class.java -> MainViewModel(unsplashInteractor)
                    else -> modelClass.getConstructor().newInstance()
                } as T
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}