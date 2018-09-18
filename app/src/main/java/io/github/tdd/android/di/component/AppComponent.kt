package io.github.tdd.android.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.tdd.android.App
import io.github.tdd.android.di.module.AppModule
import io.github.tdd.android.di.module.builders.ActivityBuildersModule
import javax.inject.Singleton

@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityBuildersModule::class])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}