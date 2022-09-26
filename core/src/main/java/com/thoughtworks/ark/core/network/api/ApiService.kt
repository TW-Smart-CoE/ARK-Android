package com.thoughtworks.ark.core.network.api

import com.thoughtworks.ark.core.network.entity.ApiException
import com.thoughtworks.ark.core.network.entity.NetworkReachableException
import com.thoughtworks.ark.core.network.entity.Result
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Response

open class ApiService {
    suspend fun <T : Any> performRequest(apiCall: suspend () -> Response<T>) = flow {
        emit(Result.Loading)
        emit(
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    Result.Success(response.body())
                } else {
                    Result.Error(
                        parseResponseError(
                            response.code(),
                            response.message(),
                            response.errorBody()
                        )
                    )
                }
            } catch (error: NetworkReachableException) {
                Result.Error(error)
            } catch (throwable: Throwable) {
                Result.Error(mapNetworkThrowable(throwable))
            }
        )
    }

    open fun mapNetworkThrowable(throwable: Throwable): ApiException = ApiException()

    open fun parseResponseError(
        code: Int,
        message: String,
        errorBody: ResponseBody?
    ): ApiException = ApiException()
}