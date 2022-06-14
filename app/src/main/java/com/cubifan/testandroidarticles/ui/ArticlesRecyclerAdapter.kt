package com.cubifan.testandroidarticles.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cubifan.testandroidarticles.R
import com.cubifan.testandroidarticles.api.responses.Article
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ArticlesRecyclerAdapter(
    private val dataSet: Array<Article>,
    private val context: Context,
    private val addToFavList: Boolean,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ArticlesRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View, onItemClick: (position: Int) -> Unit, addToFavList: Boolean) :
        RecyclerView.ViewHolder(view) {
        val articleTitle: TextView
        val articlePublished: TextView
        val articleImage: ImageView

        init {
            articleTitle = view.findViewById(R.id.articleTitle)
            articlePublished = view.findViewById(R.id.published)
            articleImage = view.findViewById(R.id.articleImage)
            view.setOnClickListener {
                onItemClick(adapterPosition)
                val toastText =
                    if (addToFavList) it.context.getString(R.string.add_to_favorites) else it.context.getString(
                        R.string.remove_from_favorites
                    )
                Toast.makeText(it.context, toastText, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.article_view, viewGroup, false)

        return ViewHolder(view, onItemClick, addToFavList)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            with(dataSet[position]) {
                articleTitle.text = title
                if (medias.imageCount != 0)
                    Picasso.with(context)
                        .load(medias.images[0].original.url)
                        .into(articleImage)

                articlePublished.text =
                    LocalDateTime
                        .parse(published, DateTimeFormatter.ISO_ZONED_DATE_TIME)
                        .format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy hh:mm"))
                        .toString()
            }
        }
    }

    override fun getItemCount() = dataSet.size

}