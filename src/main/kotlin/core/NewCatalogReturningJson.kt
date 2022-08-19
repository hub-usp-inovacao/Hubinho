package core

import io.ktor.client.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray

class NewCatalogReturningJson (
    private val url : String,
    private val httpClient: HttpClient
) : Verification(url, httpClient) {

    override suspend fun handleResponse() {
        makeRequest()

        val body = response.bodyAsText().let {
            Json.decodeFromString<Map<String, JsonArray>>(it)
        }

        val key : String = body.keys.first()

        if (body[key]!!.isEmpty())
            throw VerificationException(message = "Data base ($url) is empty")
    }
}