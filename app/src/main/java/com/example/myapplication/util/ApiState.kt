package com.example.myapplication.util

sealed class ApiState<out T> {
    data class ACTIVE_SESSION<out R>(val data: R): ApiState<R>()
    data class INACTIVE_SESSION<out R>(val data: R): ApiState<R>()
}
