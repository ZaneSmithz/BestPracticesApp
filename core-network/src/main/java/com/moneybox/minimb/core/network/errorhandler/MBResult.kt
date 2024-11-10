package com.moneybox.minimb.core.network.errorhandler

typealias RootError = Error

sealed interface MBResult<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D): MBResult<D, E>
    data class Error<out D, out E: RootError>(val message: E): MBResult<D, E>
}