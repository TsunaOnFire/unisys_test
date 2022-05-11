package com.aph.unisys.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aph.unisys.databinding.ActivityMainBinding
import com.aph.unisys.domain.model.New
import com.aph.unisys.ui.adapters.NewsAdapter
import com.aph.unisys.ui.viewmodel.NewViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var mActivity:Activity
    private val newsViewModel : NewViewModel by viewModels()
    private lateinit var newsadapter: NewsAdapter
    private val news= mutableListOf<New>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity=this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        newsViewModel.onCreate()

        binding.searchView.setOnQueryTextListener(this)

        binding.swipeRefreshLayout.setOnRefreshListener {//SWIPE TO REFRESH ACTION
            binding.searchView.setQuery("", false);
            binding.searchView.isIconified = true;
            hideKeyboard()
            newsViewModel.onCreate()
        }

        newsViewModel.isLoading.observe(this,Observer{ isLoading -> binding.swipeRefreshLayout.isRefreshing=isLoading })

        newsViewModel.newsModel.observe(this, Observer{ updated_list ->

            news.clear()
            news.addAll(updated_list)
            newsadapter.notifyDataSetChanged()
        })
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun initRecyclerView() {
        newsadapter=NewsAdapter(news) { onItemSelected(it) }
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.adapter=newsadapter
    }
    private fun onItemSelected(new: New){
        val intent = Intent(this, DetailActivity::class.java)
        val b = Bundle()
        b.putParcelable("new", new)
        intent.putExtras(b)

        startActivity(intent)
    }

    /*
    private fun cutomNewShowDialog(new: New){
        val dialogBinding = DialogNewDetailsBinding.inflate(mActivity.layoutInflater)
        val infoDialogBuilder = AlertDialog.Builder(mActivity)
        infoDialogBuilder.setView(dialogBinding.root)
        val dialog = infoDialogBuilder.create()

        Picasso.get().load(new.urlToImage).into(dialogBinding.ivNewDetails)
        dialogBinding.tvTitleDetails.text = new.title
        dialogBinding.tvDescriptionDetails.text = new.description
        dialogBinding.tvContentDetails.text = new.content
        dialogBinding.tvReadMore.movementMethod = LinkMovementMethod.getInstance();
        dialogBinding.tvReadMore.text = Html.fromHtml("<html><a href=\"${new.url}\">${mActivity.resources.getString(R.string.txt_read_more)}</a></html>")

        dialogBinding.tvDateDetails.text = APH_toolbox.Fix_Date_Cutom(new.publishedAt,"yyyy-MM-dd'T'HH:mm:ss'Z'","HH:mm - dd/MM/yyyy")
        dialogBinding.ivClose.setOnClickListener {
            dialog.cancel()
        }


        dialog.setCancelable(true)
        dialog.show()

        dialog.create()
    }
    */
    override fun onQueryTextSubmit(toSearch: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(toSearch: String?): Boolean {
        if(!toSearch.isNullOrEmpty()) {
            newsViewModel.FilterNews(toSearch.lowercase(Locale.getDefault()))
        }
        return true
    }

}


