package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.databinding.AdapterMovieBinding
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleEntity
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.models.Article

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var articleList = mutableListOf<ArticleEntity>()
    var longClicked: ((ArticleEntity) -> Unit)? = null

    fun setMovies(articleList: List<ArticleEntity>) {
        this.articleList.clear()
        this.articleList = articleList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val article = articleList[position]
        if (ValidationUtil.validateArticle(article)) {
            holder.binding.name.text = article.title
            Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.binding.imageview)
        }
        holder.binding.cardItem.setOnLongClickListener {
            longClicked?.invoke(article)
            true
        }


    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}