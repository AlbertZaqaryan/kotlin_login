package com.example.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.loginregister.api.RetrofitClient
import com.example.loginregister.DataModel.KotlinUser
import retrofit2.Call

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val name: EditText = findViewById(R.id.Name)
        val email: EditText = findViewById(R.id.Email)
        val password: EditText = findViewById(R.id.Password)
        val confirmpassword: EditText = findViewById(R.id.ConfirmPassword)
        val reg: TextView = findViewById(R.id.register)

        reg.setOnClickListener {
            val user =
                KotlinUser(name.text.toString(), email.text.toString(), password.text.toString())
            if (password.text.toString() == confirmpassword.text.toString()) {
                RetrofitClient.service.createUsers(user)
                    .enqueue(object : retrofit2.Callback<KotlinUser> {
                        override fun onResponse(
                            call: retrofit2.Call<KotlinUser>,
                            response: retrofit2.Response<KotlinUser>
                        ) {
                            if (response.isSuccessful) {
                                val intent = Intent(this@Register, UserPage::class.java)
                                startActivity(intent)
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<KotlinUser>, t: Throwable) {
                            // Handle failure, e.g., by logging or showing an error to the user
                        }
                    })
            }
        }
    }
}
