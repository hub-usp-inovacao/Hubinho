package core

import io.ktor.client.*
import java.lang.Thread.sleep

class VerificationEngine(
    private val httpClient : HttpClient,
    private val notifier: Notifier,
    private val delta : Long = 1*60*60*1000
) {
    private val IF_BASE_URL =  "https://hubuspinovacao.if.usp.br"
    private val STI_BASE_URL = "https://hubusp.inovacao.usp.br"

    private suspend fun verifyIfFrontend() {
        try {
            ServerRunning(IF_BASE_URL, httpClient).run()
        } catch (e: VerificationException) {
            notifier.sendText(e.message!!)
        }
    }

    private suspend fun verifyStiFrontend() {
        try {
            ServerRunning(STI_BASE_URL, httpClient).run()
        } catch (e: VerificationException) {
            notifier.sendText(e.message!!)
        }
    }

    private suspend fun verifyIfBackend() {
        try {
            ServerRunning("$IF_BASE_URL/api", httpClient).run()
            OldCatalogReturningJson("$IF_BASE_URL/api/disciplines", httpClient).run()
            NewCatalogReturningJson("$IF_BASE_URL/api/catalog/disciplines", httpClient).run()
        } catch (e: VerificationException) {
            notifier.sendText(e.message!!)
        }
    }

    private suspend fun verifyStiBackend() {
        try {
            ServerRunning("$STI_BASE_URL/api", httpClient).run()
            OldCatalogReturningJson("$STI_BASE_URL/api/disciplines", httpClient).run()
        } catch (e: VerificationException) {
            notifier.sendText(e.message!!)
        }
    }

    suspend fun loop() {
        notifier.sendText("VRUM to executando")
        while(true) {
            verifyIfFrontend()
            verifyStiFrontend()
            verifyIfBackend()
            verifyStiBackend()

            sleep(delta)
        }
    }

}
