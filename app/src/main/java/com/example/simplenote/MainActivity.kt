package com.example.simplenote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplenote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        onClick()
    }
    private fun onClick() = with(binding) {
        button.setOnClickListener {
            Intent(this@MainActivity, CreateArticleActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun init() = with(binding) {
        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerview.adapter = adapter

        val dbh = DBHelper(this@MainActivity, null)
        val articles = dbh.readData()

        if(articles.isNotEmpty()) {
            for (article in articles) {
                adapter.addArticle(article)
            }
        }
        else {
            val dynamicTextview = TextView(this@MainActivity)

            dynamicTextview.text = "Записей пока что нет"
            dynamicTextview.textSize = 48f
            dynamicTextview.gravity = Gravity.CENTER_VERTICAL
            dynamicTextview.gravity = Gravity.CENTER_HORIZONTAL

            binding.linearLayout.addView(dynamicTextview)
        }
    }
}

