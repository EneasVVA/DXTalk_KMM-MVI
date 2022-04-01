package com.eneas

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.eneas.plugins.*
import io.ktor.auth.*
import io.ktor.application.install

fun main() {
    embeddedServer(Netty, port = 8080, host = "eneas.com") {
        install(Authentication) {
            basic("auth-basic") {
                realm = "Access to the '/' path"
                validate { credentials ->
                    if (credentials.name == "eneas@cognizant.com" && credentials.password == "cognizant") {
                        UserIdPrincipal("Eneas M.")
                    } else {
                        null
                    }
                }
            }
        }
        configureRouting()
    }.start(wait = true)
}
