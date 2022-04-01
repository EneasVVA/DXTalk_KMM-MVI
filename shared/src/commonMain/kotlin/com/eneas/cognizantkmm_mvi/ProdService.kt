package com.eneas.cognizantkmm_mvi
import com.eneas.cognizantkmm_mvi.core.Either
import com.eneas.cognizantkmm_mvi.core.Platform
import com.eneas.cognizantkmm_mvi.core.PlatformType
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ProdService : ProdRepository {
    companion object {
        private val ENDPOINT: String =
            if(Platform == PlatformType.ANDROID)
                "http://10.0.2.2:8080"
            else
                "http://eneas.com:8080"
    }

    private lateinit var client: HttpClient

    private fun buildClient(email: String, password: String) {
        this.client = HttpClient(CIO) {
            /*
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }*/
            install(Auth) {
                basic {
                    credentials {
                        BasicAuthCredentials(
                            username = email,
                            password = password
                        )
                    }
                    realm = "Access to the '/' path"
                }
            }
            defaultRequest {
                url.takeFrom(URLBuilder().takeFrom(ENDPOINT).apply {
                    encodedPath += url.encodedPath
                })
            }
        }
    }

    override suspend fun login(email: String, password: String): Either<Error, Boolean> = execute {
        buildClient(email, password)

        if(client.get<HttpResponse> { url("/") }.status != HttpStatusCode.Forbidden)
            true
        else
            throw RuntimeException("Forbidden")
    }

    override suspend fun profile(): Either<Error, String> = execute {
        client.get<HttpResponse> {
            url("/")
        }.readText()
    }

    private suspend fun <R> execute(block: suspend () -> R): Either<Error, R> = try {
        Either.Right(block())
    } catch (t: Throwable) {
        t.printStackTrace()
        Either.Left(Error(t.message))
    }

}
