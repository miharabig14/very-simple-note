package com.example.simplenote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simplenote.databinding.ActivityCreateArticleBinding
import com.example.simplenote.databinding.ActivityMainBinding

class CreateArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClick()
    }
    private fun onClick() = with(binding) {
        button2.setOnClickListener {
            val dbh = DBHelper(this@CreateArticleActivity, null)
            val title = editTextText.text.toString()
            val content = editTextText3.text.toString()
            dbh.insertData(Article(title, content, null))
            Intent(this@CreateArticleActivity, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}
