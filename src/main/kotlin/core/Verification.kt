package core

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

abstract class Verification (
    private val url : String,
    private val httpClient : HttpClient
) {
    protected lateinit var response : HttpResponse

    suspend fun makeRequest() {
        response = httpClient.get(urlString = url)
    }

    abstract suspend fun handleResponse()

    suspend fun run() {
        handleResponse()
    }
}