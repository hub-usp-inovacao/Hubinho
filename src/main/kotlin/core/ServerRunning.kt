package core

import io.ktor.client.*
import java.net.SocketException

class ServerRunning (
    private val url : String,
    private val httpClient: HttpClient
) : Verification(url, httpClient) {

    override suspend fun handleResponse() {
        try {
            makeRequest()
        } catch (_:SocketException) {
            throw VerificationException(message = "The site ($url) is offline")
        }
    }
}