package com.aph.unisys.ui.view

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.aph.unisys.R
import com.aph.unisys.databinding.ActivityDetailBinding
import com.aph.unisys.domain.model.New
import com.aph.unisys.utils.APH_toolbox
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val b = intent.extras
        val new = b!!.getParcelable<New>("new")

        if(new!=null) {

            Picasso.get().load(new.urlToImage).into(binding.ivNewDetails)
            binding.tvTitleDetails.text = new.title
            binding.tvDescriptionDetails.text = new.description
            binding.tvContentDetails.text = new.content
            binding.tvReadMore.movementMethod = LinkMovementMethod.getInstance();
            binding.tvReadMore.text = Html.fromHtml("<html><a href=\"${new.url}\">${this.resources.getString(R.string.txt_read_more)}</a></html>")

            binding.tvDateDetails.text = APH_toolbox.Fix_Date_Cutom(
                new.publishedAt.toString(),
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                "HH:mm - dd/MM/yyyy"
            )
            binding.ivClose.setOnClickListener {
                this.onBackPressed()
            }
        }
    }
}