package com.thirkazh.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import coil.api.load
import coil.size.Scale
import com.thirkazh.newsapi.databinding.ActivityDetailBinding
import com.thirkazh.newsapi.model.ArticlesItem

class DetailActivity : AppCompatActivity() {
    companion object{
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        //Untuk mengirimkan data yang dikirimkan oleh MainActivity melalui CDVHeadlineAdapter
        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        binding.run {
            setContentView(root)

            //untuk membuild actionbar
            setSupportActionBar(toolBar)

            //untuk menampilkan tombol back
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = data.title
            imgToolbar.apply {
                load(data.urlToImage) {
                    scale(Scale.FILL)
                }
                contentDescription = data.description
            }
            //untuk get content
            txtContent.text = data.content

            //untuk get publishAt
            txtDate.text = data.publishedAt
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}