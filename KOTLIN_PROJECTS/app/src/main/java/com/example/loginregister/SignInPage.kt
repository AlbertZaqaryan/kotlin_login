package com.example.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.loginregister.DataModel.KotlinUser
import com.example.loginregister.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)
        val email: EditText = findViewById(R.id.Email)
        val password: EditText = findViewById(R.id.Password)
        val login: TextView = findViewById(R.id.Login_txt)
        var info_user: Boolean = false
        var all_humans: List<KotlinUser>? = emptyList()
        RetrofitClient.service.getUsers().enqueue(object : Callback<List<KotlinUser>> {
            override fun onResponse(call: Call<List<KotlinUser>>, response: Response<List<KotlinUser>>) {
                if (response.isSuccessful) {
                    val humans = response.body()
                    all_humans = humans
                }
            }
            override fun onFailure(call: Call<List<KotlinUser>>, t: Throwable) {
                var info = "Failure: ${t.message}"

            }
        })

        login.setOnClickListener {
            for(i in all_humans!!){
                if (i.email == email.text.toString() && i.password == password.text.toString()){
                    info_user = true
                }
            }
            if (info_user){
                val intent = Intent(this, UserPage::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this@SignInPage, "Invalid Login", Toast.LENGTH_LONG).show()
            }
            }

        val signin: TextView = findViewById(R.id.sign_in_txt)
        signin.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}