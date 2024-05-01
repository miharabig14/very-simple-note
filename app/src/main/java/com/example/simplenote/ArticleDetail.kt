package com.example.simplenote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.simplenote.databinding.ActivityArticleDetailBinding
import com.example.simplenote.databinding.ActivityMainBinding

class ArticleDetail : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleId = intent.extras!!.getInt("id").toInt()
        val dbh = DBHelper(this, null)
        val article = dbh.getArticleById(articleId)
        binding.title.text = article?.title.toString()
        binding.content.text = article?.content.toString()

        binding.imageButton.setOnClickListener {
            dbh.deleteArticleById(articleId)

            Intent(this@ArticleDetail, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}