package io.github.tdd.android.di.module

import dagger.Module
import dagger.Provides
import io.github.tdd.android.presentation.home.HomePresenter
import io.github.tdd.android.util.provider.InstalledApplicationsProvider
import javax.inject.Singleton

@Module
class PresentersModule {

    @Provides
    @Singleton
    fun provideHomePresenter(provider: InstalledApplicationsProvider): HomePresenter = HomePresenter(provider)
}