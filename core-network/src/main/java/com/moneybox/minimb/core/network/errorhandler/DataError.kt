package com.moneybox.minimb.core.network.errorhandler

sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        UNAUTHORIZED,
        UNKNOWN,
        EMPTY_RESPONSE,

    }
}