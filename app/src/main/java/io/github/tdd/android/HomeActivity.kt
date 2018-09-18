package io.github.tdd.android

import android.os.Bundle
import androidx.annotation.LayoutRes
import io.github.tdd.android.presentation.base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    @LayoutRes
    public
    override fun getLayoutResId(): Int = R.layout.activity_home
}
