package com.example.loginregister
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.loginregister.api.RetrofitClient
import com.example.loginregister.DataModel.KotlinUser
import com.example.loginregister.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)

        val buttonGetHuman: Button = findViewById(R.id.btn_joke)
        val textViewJoke: TextView = findViewById(R.id.tv_joke)
        val loadingProgressBar: ProgressBar = findViewById(R.id.idLoadingPB)

        buttonGetHuman.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            RetrofitClient.service.getUsers().enqueue(object : Callback<List<KotlinUser>> {
                override fun onResponse(call: Call<List<KotlinUser>>, response: Response<List<KotlinUser>>) {
                    loadingProgressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        val humans = response.body()
                        textViewJoke.text = humans?.joinToString(separator = "\n") {
                            "${it.name} is ${it.email}"
                        }
                    } else {
                        textViewJoke.text = "Error: ${response.errorBody()?.string()}"
                    }
                }

                override fun onFailure(call: Call<List<KotlinUser>>, t: Throwable) {
                    loadingProgressBar.visibility = View.GONE
                    textViewJoke.text = "Failure: ${t.message}"
                }
            })
        }
    }
}