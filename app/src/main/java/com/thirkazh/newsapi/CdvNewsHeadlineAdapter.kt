package com.thirkazh.newsapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.thirkazh.newsapi.databinding.ActivityMainBinding
import com.thirkazh.newsapi.databinding.CdvNewsHeadlineBinding
import com.thirkazh.newsapi.model.ArticlesItem
import kotlinx.android.synthetic.main.cdv_news_headline.view.*

class CdvNewsHeadlineAdapter : RecyclerView.Adapter<CdvNewsHeadlineVH> (){
    private val listData = ArrayList<ArticlesItem> ()

    // Fungsi ini berfungsi untuk add data ke dalam recyclerview
    fun addData(item: List<ArticlesItem>) {
        listData.clear()
        listData.addAll(item)
        notifyDataSetChanged()
    }

    // Berfungsi untuk menginflate atau menerapkan operasi yang dibuat kedalam layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlineVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater,parent, false)
        return CdvNewsHeadlineVH(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: CdvNewsHeadlineVH, position: Int) {
        holder.bind(listData[position])
    }
}
//Sebagai
class CdvNewsHeadlineVH (private val binding: CdvNewsHeadlineBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(article: ArticlesItem){
        binding.run {
            txtTitle.text = cropText(article.title?: "Tidak ada judul")
            txtSubtitle.text = article.publishedAt
            imgHeadline.apply {
                load(article.urlToImage) {
                    scale(Scale.FILL)
                }
                contentDescription = article.description
            }
            //Untuk melakukan aksi klik pada gambar
            root.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.DETAIL_NEWS, article) //DETAIL_NEWS BERFUNGSI SEBAGAI VARIABLE YANG BERISI DATA YANG AKAN DIKIRIMKAN KE DETAIL ACTIVITY
                }

                it.context.startActivity(intent)
            }
        }
    }
    //Untuk memotong text yang lebih dari 50
    private fun cropText(text: String): String{
        return text.take(50) + if (text.length > 50) "..." else ""
    }

}
