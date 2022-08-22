package com.garibyan.armen.tbc_midterm.repository

import android.util.Log.d
import com.garibyan.armen.tbc_midterm.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> apiCall(
        apiCall: suspend () -> T
    ) = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(apiCall.invoke()))
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    emit(
                        Resource.Error(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    )
                }
                else -> {
                    emit(Resource.Error(true, null, null))
                    d("mylog", throwable.message.toString())
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}