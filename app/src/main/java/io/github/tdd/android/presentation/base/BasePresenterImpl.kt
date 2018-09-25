package io.github.tdd.android.presentation.base

abstract class BasePresenterImpl<V> : BasePresenter<V> {

    private var _view: V? = null

    val view: V?
        get() = _view

    override fun onCreate(view: V) {
        _view = view
    }

    override fun onDestroy() {
        _view = null
    }
}