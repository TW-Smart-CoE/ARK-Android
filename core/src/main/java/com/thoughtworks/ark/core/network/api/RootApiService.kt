package com.thoughtworks.ark.core.network.api

import com.thoughtworks.ark.core.network.entity.ApiException
import com.thoughtworks.ark.core.network.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Response

open class RootApiService {
    suspend fun <T : Any> performFlowRequest(apiCall: suspend () -> Response<T>): Flow<Result<T>> {
        return flow {
            emit(Result.Loading)
            emit(
                try {
                    val response = apiCall()
                    if (!response.isSuccessful) {
                        val errorBody = response.errorBody()
                        Result.Error(
                            parseResponseError(
                                response.code(),
                                response.message(),
                                errorBody
                            )
                        )
                    } else {
                        Result.Success(response.body())
                    }
                } catch (throwable: Throwable) {
                    Result.Error(mapNetworkThrowable(throwable))
                }
            )
        }
    }

    open fun mapNetworkThrowable(throwable: Throwable): ApiException = ApiException()

    open fun parseResponseError(
        code: Int,
        message: String,
        errorBody: ResponseBody?
    ): ApiException = ApiException()
}