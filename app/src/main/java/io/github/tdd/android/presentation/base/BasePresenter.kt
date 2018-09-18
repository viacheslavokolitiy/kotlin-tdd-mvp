package io.github.tdd.android.presentation.base

abstract class BasePresenter<V> {

    private var view: V? = null

    protected fun onCreate(v: V) {
        view = v
    }

    protected fun onDestroy() {
        view = null
    }
}