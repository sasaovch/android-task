package ru.sample.duckapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ru.sample.duckapp.service.DuckService
import ru.sample.duckapp.service.DuckServiceImpl

class MainActivity : AppCompatActivity() {
    private lateinit var duckServiceImpl: DuckService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val duckImageView: ImageView = findViewById(R.id.duckImageView)
        val inputField: EditText = findViewById(R.id.inputField)
        val nextButton: Button = findViewById(R.id.nextButton)

        duckServiceImpl = DuckServiceImpl(
            duckImageView,
            inputField,
            this,
        )

        nextButton.setOnClickListener {
            duckServiceImpl.fetchRandomDuckImage()
        }
    }
}
