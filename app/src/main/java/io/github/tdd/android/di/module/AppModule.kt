package io.github.tdd.android.di.module

import dagger.Module

@Module(includes = [PresentersModule::class, UtilsModule::class,
    AdapterDelegatesModule::class])
class AppModule