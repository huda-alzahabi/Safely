package com.finalproj.safely

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        val languages = resources.getStringArray(R.array.nationalities)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown, languages)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setAdapter(arrayAdapter)
        val marital = resources.getStringArray(R.array.marital_status)
        val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown, marital)
        val autocompleteTV2 = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView2)
        autocompleteTV2.setAdapter(arrayAdapter2)
    }
}
