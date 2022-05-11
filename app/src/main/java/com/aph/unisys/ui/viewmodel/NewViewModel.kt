package com.aph.unisys.ui.viewmodel

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aph.unisys.data.model.NewModel
import com.aph.unisys.domain.GetNews
import com.aph.unisys.domain.model.New
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(
    private val getNew:GetNews,

): ViewModel() {

    val newsModel = MutableLiveData<MutableList<New>>()//Filtered List
    val isLoading = MutableLiveData<Boolean>()

    private var completeNewsList= listOf<New>()

    fun onCreate() {

        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getNew()

            if(!result.isNullOrEmpty()){
                completeNewsList=result
                newsModel.postValue(result.toMutableList())

            }
            isLoading.postValue(false)
        }
    }


    fun FilterNews(toSearch:String){
        viewModelScope.launch {
            isLoading.postValue(true)
            if (toSearch.isNotBlank()) {
                val result=mutableListOf<New>()
                completeNewsList.forEach { if(it.title?.lowercase(Locale.getDefault())?.contains(toSearch) == true){result.add(it)} }
                newsModel.postValue(result)
            } else {
                newsModel.postValue(completeNewsList.toMutableList())
            }
            isLoading.postValue(false)
        }
    }




}