package com.example.loginregisdata.activities

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginregisdata.R
import com.example.loginregisdata.models.LoginResponse
import com.example.loginregisdata.network.RetrofitClient
import com.example.loginregisdata.storage.SharePrefManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferenceManager: SharePrefManager
    var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressDialog = ProgressDialog(this)
        sharedPreferenceManager = SharePrefManager(this)

        if (sharedPreferenceManager.spSigned!!) {
            startActivity(Intent(this, ))
        }

        btn_Login.setOnClickListener {
            val email = et_email_login.text.toString().trim()
            val password = et_password_login.text.toString().trim()

            if (email.isEmpty()){
                et_email_login.error = "Email required"
            }

            RetrofitClient.instance.userLogin(email, password).enqueue(object : Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (! response.body()?.error!!){
                        sharedPreferenceManager.saveBoolean(SharePrefManager.SP_SIGNED, true)
                        sharedPreferenceManager.saveId(SharePrefManager.SP_ID, 1)

                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()

                    }else{
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                }

            })


        }
    }
}