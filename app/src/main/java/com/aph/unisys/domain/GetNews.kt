package com.aph.unisys.domain

import android.content.Context
import android.widget.Toast
import com.aph.unisys.core.InternetConnection
import com.aph.unisys.data.NewRepository
import com.aph.unisys.data.database.entites.toDatabase
import com.aph.unisys.data.model.NewModel
import com.aph.unisys.domain.model.New
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


//Return the final list of news for use
class GetNews @Inject() constructor(
    @ApplicationContext private val context: Context,
    private val repository:NewRepository
) {
    suspend operator fun invoke():List<New>{
        if(InternetConnection.checkConnection(context)) {//CHECK CONNECTION
            val news = repository.getAllNewFromApi()
            if (news.isNotEmpty()) {
                repository.clearNews()
                repository.insertNews(news.map { it.toDatabase() })
                return news
            } else {
                return repository.getAllNewFromDatabase()
            }
        } else{
            val news =repository.getAllNewFromDatabase()
            if(news.isEmpty()){
                Toast.makeText(context,"No Internet & No data to recover\nCheck your internet connection and Swipe Down to refresh",Toast.LENGTH_LONG).show()
            }
            return news
        }
    }
}