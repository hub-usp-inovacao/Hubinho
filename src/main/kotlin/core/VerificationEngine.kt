package core

import io.ktor.client.*
import java.lang.Thread.sleep

class VerificationEngine(
    private val httpClient : HttpClient,
    private val delta : Long = 1.hoursInMillis()
) {
    private val IF_BASE_URL =  "https://hubuspinovacao.if.usp.br"
    private val STI_BASE_URL = "https://hubusp.inovacao.usp.br"

    private suspend fun verifyIfFrontend() {
        try {
            ServerRunning(IF_BASE_URL, httpClient).run()
        } catch (e: VerificationException) {
            println(e.message)
        }
    }

    private suspend fun verifyStiFrontend() {
        try {
            ServerRunning(STI_BASE_URL, httpClient).run()
        } catch (e: VerificationException) {
            println(e.message)
        }
    }

    private suspend fun verifyIfBackend() {
        try {
            ServerRunning("$IF_BASE_URL/api", httpClient).run()
            OldCatalogReturningJson("$IF_BASE_URL/api/disciplines", httpClient).run()
            NewCatalogReturningJson("$IF_BASE_URL/api/catalog/disciplines", httpClient).run()
        } catch (e: VerificationException) {
            println(e.message)
        }
    }

    private suspend fun verifyStiBackend() {
        try {
            ServerRunning("$STI_BASE_URL/api", httpClient).run()
            OldCatalogReturningJson("$STI_BASE_URL/api/disciplines", httpClient).run()
        } catch (e: VerificationException) {
            println(e.message)
        }
    }

    suspend fun loop() {

        while(true) {
            println("oi")
            verifyIfFrontend()
            verifyStiFrontend()
            verifyIfBackend()
            verifyStiBackend()

            sleep(delta)
        }
    }

}