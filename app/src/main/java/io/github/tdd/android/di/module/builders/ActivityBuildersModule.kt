package io.github.tdd.android.di.module.builders

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.tdd.android.HomeActivity
import io.github.tdd.android.di.module.home.HomeActivityModule

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun buildHomeActivity(): HomeActivity
}