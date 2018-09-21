package io.github.tdd.android.presentation.base

interface BasePresenter<V> {

    fun onCreate(view: V)

    fun onDestroy()
}