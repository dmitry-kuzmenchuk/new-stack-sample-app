package ru.dmitry.kuzmenchuk.newstackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.dmitry.kuzmenchuk.newstackapp.data.UnsplashRepository
import ru.dmitry.kuzmenchuk.newstackapp.data.retrofit.UnsplashRequestInterceptor
import ru.dmitry.kuzmenchuk.newstackapp.domain.UnsplashInteractor
import ru.dmitry.kuzmenchuk.newstackapp.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val retrofit: Retrofit = initRetrofit()

    private val unsplashInteractor: UnsplashInteractor = initUnsplashInteractor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
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
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .client(okHttpClient)
            .build()
    }

    private fun initUnsplashInteractor(): UnsplashInteractor {
        return UnsplashInteractor(UnsplashRepository(retrofit))
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