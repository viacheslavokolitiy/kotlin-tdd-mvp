package io.github.tdd.android.di.module

import dagger.Module
import dagger.Provides
import io.github.tdd.android.App
import io.github.tdd.android.adapter.delegate.DangerousAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.ModerateRiskAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.SafeAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.ScannedAppDelegate

@Module
class AdapterDelegatesModule {

    @Provides
    fun provideDangerousAppsHeaderDelegate(app: App): DangerousAppsHeaderDelegate = DangerousAppsHeaderDelegate(app)

    @Provides
    fun provideModerateRiskAppsHeaderDelegate(app: App): ModerateRiskAppsHeaderDelegate = ModerateRiskAppsHeaderDelegate(app)

    @Provides
    fun provideSafeRiskAppsHeaderDelegate(app: App): SafeAppsHeaderDelegate = SafeAppsHeaderDelegate(app)

    @Provides
    fun provideScannedAppDelegate(app: App): ScannedAppDelegate = ScannedAppDelegate(app)
}