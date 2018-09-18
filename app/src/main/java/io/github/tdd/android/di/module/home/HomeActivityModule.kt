package io.github.tdd.android.di.module.home

import dagger.Binds
import dagger.Module
import io.github.tdd.android.HomeActivity

@Module
abstract class HomeActivityModule {

    @Binds
    abstract fun provideHomeActivity(activity: HomeActivity): HomeActivity
}