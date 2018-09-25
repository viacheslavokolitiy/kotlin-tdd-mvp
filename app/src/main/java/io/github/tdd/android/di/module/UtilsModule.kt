package io.github.tdd.android.di.module

import dagger.Module
import dagger.Provides
import io.github.tdd.android.App
import io.github.tdd.android.util.provider.InstalledApplicationsProvider
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideInstalledAppsProvider(app: App): InstalledApplicationsProvider
            = InstalledApplicationsProvider(app)
}