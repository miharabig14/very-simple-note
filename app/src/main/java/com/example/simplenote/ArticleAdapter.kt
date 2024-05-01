package com.example.simplenote

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenote.databinding.ArticleItemBinding

class ArticleAdapter(): RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {
    private val articlesList = ArrayList<Article>()

    class ArticleHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ArticleItemBinding.bind(item)

        fun bind(article: Article) = with(binding) {
            articleTitle.text = article.title
            articleTitle.setOnClickListener {
                val intent = Intent(itemView.context, ArticleDetail::class.java).apply {
                    putExtra("id", article.id)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bind(articlesList[position])
    }
    fun addArticle(article: Article) {
        articlesList.add(article)
        notifyDataSetChanged()
    }
}