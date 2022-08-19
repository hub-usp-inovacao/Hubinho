
import core.VerificationEngine
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

suspend fun main() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    VerificationEngine(httpClient = client).loop()
}