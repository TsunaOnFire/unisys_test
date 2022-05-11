package com.aph.unisys.data

import com.aph.unisys.data.database.dao.NewDao
import com.aph.unisys.data.database.entites.NewEntity
import com.aph.unisys.data.network.NewService
import com.aph.unisys.domain.model.New
import com.aph.unisys.domain.model.toDomain
import javax.inject.Inject

class NewRepository @Inject constructor(
    private val api:NewService,
    private val newDao:NewDao
) {

    suspend fun getAllNewFromApi():List<New>{
        val response = api.getNew("")
        return response.map{it.toDomain()}
    }

    suspend fun getAllNewFromDatabase():List<New>{
        val response: List<NewEntity> = newDao.getAllQuotes()

        return response.map{it.toDomain()}
    }

    suspend fun insertNews(news:List<NewEntity>){
        newDao.insertAll(news)

    }

    suspend fun clearNews(){
        newDao.deleteAllNews()
    }

}