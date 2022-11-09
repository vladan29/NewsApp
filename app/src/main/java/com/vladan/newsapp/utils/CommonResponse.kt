package com.vladan.newsapp.utils

class CommonResponse<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): CommonResponse<T> {
            return CommonResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): CommonResponse<T> {
            return CommonResponse(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): CommonResponse<T> {
            return CommonResponse(Status.LOADING, data, null)
        }

    }
}
