
import app.NotifierImpl
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

    val token = System.getenv("TELEGRAM_BOT_TOKEN")
    val chatId= System.getenv("TELEGRAM_CHAT_ID").toLong()
    val bot = NotifierImpl(token,chatId)

    VerificationEngine(httpClient = client,notifier = bot).loop()

}