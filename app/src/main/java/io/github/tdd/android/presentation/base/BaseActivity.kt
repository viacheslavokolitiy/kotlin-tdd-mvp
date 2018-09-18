package io.github.tdd.android.presentation.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    protected abstract fun getLayoutResId(): Int
}