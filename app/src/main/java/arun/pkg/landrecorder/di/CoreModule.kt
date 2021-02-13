package arun.pkg.landrecorder.di

import androidx.lifecycle.ViewModelProvider
import arun.pkg.landrecorder.util.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class CoreModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}