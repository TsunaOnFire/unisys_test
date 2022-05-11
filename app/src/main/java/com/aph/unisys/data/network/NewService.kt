package com.aph.unisys.data.network

import android.util.Log
import com.aph.unisys.data.model.NewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewService @Inject constructor(private val api:NewApiClient){
    suspend fun getNew(contenToSerch: String):List<NewModel>{
        return withContext(Dispatchers.IO) {
            val response = api.getAllNew("us",contenToSerch)
            Log.d("Response","Error:"+response?.errorBody()?.charStream()?.readText())
            response.body()?.articles ?: emptyList()
        }

    }
}