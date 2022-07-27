package com.finalproj.safely.user

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.finalproj.safely.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.json.JSONObject

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification2)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(TAG, token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            var msg = findViewById<EditText>(R.id.mMessage)
            var title = findViewById<EditText>(R.id.mTitle)


            //find view by id
            var button = findViewById<Button>(R.id.mSend)
            button.setOnClickListener {
                var ttle = title.text.toString().trim()
                var mesg = msg.text.toString().trim()
                if (ttle.isEmpty() || mesg.isEmpty()) {
                    Toast.makeText(baseContext, "Please fill all the fields", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    pushNotification(this,"fZPQWipHQ4WxJPq5HLME8X:APA91bH5O2YZY7OXiHJ36Ofl5v3NvKRJ6tzBDzYLqkoSsXsO6Dsf5v1rdFNn7cLPUxxbm8nyUs0HY36EP8b_v2e3sGHg-oxfABnDv0ReDisz7YlgQFUCCWbXygjeXrX4INNjRZuQ0611","Hello","HIIII")
                }
            }
        })
    }
    val BASE_URL: String = "https://fcm.googleapis.com/fcm/send"
    val SERVER_KEY: String =
        "AAAA0CIXcSM:APA91bHxmduHsSSKR0349Mf-w45UnqM1szmMkgDmf8OlIl-hHOs2PLteJ3LxDr11cbsKSJgaE8-ffQ2aXcu1TFA5qxWXYlx-VLElooY3b9aXI9h00vCF2-X6AS9QisPSOxGfx3waC8ib"


    fun pushNotification(context: Context, token: String, title: String, message: String) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val requestQueue = Volley.newRequestQueue(context)
        try {
            val json = JSONObject()
            val notification = JSONObject()
            notification.put("title", title)
            notification.put("body", message)
            json.put("to", token)
            json.put("notification", notification)
            val request : JsonObjectRequest =object : JsonObjectRequest(Request.Method.POST,
                BASE_URL,
                json,
                Response.Listener<JSONObject>
                { response ->
                    Log.d("TAGTest", response.toString())
                },
                Response.ErrorListener {
                    Log.d("TAGTest", "error: ${it.message}")
                })

            {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Authorization"] = "key=$SERVER_KEY"
                    headers["Content-Type"] = "application/json"
                    return headers
                }

            }
            requestQueue.add(request)

//            sendNotification(context, jsonObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }
}}