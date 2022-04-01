package com.eneas.cognizantkmm_mvi

import com.eneas.cognizantkmm_mvi.core.Either

interface ProdRepository {
    suspend fun login(email: String, password: String): Either<Error, Boolean>
    suspend fun profile(): Either<Error, String>
}
