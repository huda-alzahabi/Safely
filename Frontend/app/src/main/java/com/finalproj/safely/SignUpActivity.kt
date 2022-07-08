package com.finalproj.safely

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


class SignUpActivity : AppCompatActivity() {
  lateinit var retrofitInterface: RetrofitInterface
    private var jsonObjectString=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //initialize api
        val retrofit= RetrofitClient.getInstance()
        retrofitInterface=retrofit.create(RetrofitInterface::class.java)



        val signUp = findViewById<Button>(R.id.signup)

        signUp.setOnClickListener {
            var name = findViewById<EditText>(R.id.signup_name).getText().toString()
            var email = findViewById<EditText>(R.id.signup_email).text.toString()
            var password = findViewById<EditText>(R.id.signup_pass).text.toString()
            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("email", email)
            jsonObject.put("password", password)
            Log.d("NAMEEE",name)
            // Convert JSONObject to String
            jsonObjectString = jsonObject.toString()
            if (name.isEmpty()) Toast.makeText(this@SignUpActivity,"Name cannot be empty!",Toast.LENGTH_SHORT).show()
            if (email.isEmpty()) Toast.makeText(this@SignUpActivity,"Email cannot be empty!",Toast.LENGTH_SHORT).show()
            if (password.isEmpty()) Toast.makeText(this@SignUpActivity,"Password cannot be empty!",Toast.LENGTH_SHORT).show()
            else registerUser()
        }
      }

    private fun registerUser() {
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
          try{  // Do the POST request and get response
              delay(5000)

            val response = retrofitInterface.registerUser(requestBody)

            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )

                    Log.d("Pretty Printed JSON :", prettyJson)

                } else {

                    Log.d("RETROFIT_ERROR", response.code().toString())

                }
            }
        }catch(e: Exception){
            e.printStackTrace()
              Log.d("COROUTINEEXCEPTION", "KKKKK")
        }
    }}}