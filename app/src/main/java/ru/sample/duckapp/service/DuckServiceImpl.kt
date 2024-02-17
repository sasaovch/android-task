package ru.sample.duckapp.service

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sample.duckapp.R
import ru.sample.duckapp.data.DuckUrlProvider
import ru.sample.duckapp.domain.DuckPayload
import ru.sample.duckapp.infra.Api

class DuckServiceImpl(
    private var duckImageView: ImageView,
    private var inputField: EditText,
    private val appContext: AppCompatActivity,
) : DuckService {
    private val ducksApi = Api.ducksApi
    private val userInputServiceImpl = UserInputServiceImpl()
    private val validatorDuckInput = DuckInputValidatorImpl()
    private val duckUrlProvider: DuckUrlProvider = Api
    override fun fetchRandomDuckImage() {
        val inputText = inputField.text.toString()
        if (inputText.isEmpty()) {
            getRandomDuckAsync()
        } else {
            val inputDuck = userInputServiceImpl.parseInput(inputText)
            if (validatorDuckInput.validateInput(inputDuck)) {
                loadImage(duckUrlProvider.getDuckUrl(inputDuck))
            } else {
                showToast("Invalid http code!")
            }
        }
    }

    private fun getRandomDuckAsync() {
        ducksApi.getRandomDuck().enqueue(object : Callback<DuckPayload> {
            override fun onResponse(call: Call<DuckPayload>, response: Response<DuckPayload>) {
                if (response.isSuccessful) {
                    val duck = response.body()
                    duck?.let {
                        loadImage(it.url)
                    }
                } else {
                    switchToImageScreen()
                }
            }

            override fun onFailure(call: Call<DuckPayload>, t: Throwable) {
                switchToImageScreen()
                setErrorScreen()
                showToast("Error while getting duck")
            }
        })
    }

    private fun loadImage(imageUrl: String) {
        Picasso.get().load(imageUrl).into(duckImageView, object : com.squareup.picasso.Callback {
            override fun onSuccess() {
                switchToImageScreen()
            }

            override fun onError(e: Exception?) {
                switchToImageScreen()
                setErrorScreen()
                showToast("Service did not returned your duck(!")
            }
        })
    }

    private fun switchToImageScreen() {
        duckImageView.visibility = View.VISIBLE
    }

    private fun setErrorScreen() {
        duckImageView.setImageResource(R.drawable.fail)
    }

    private fun showToast(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_LONG).show()
    }
}