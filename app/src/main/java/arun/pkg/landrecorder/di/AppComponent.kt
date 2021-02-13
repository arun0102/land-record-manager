package arun.pkg.landrecorder.di

import android.content.Context
import arun.pkg.landrecorder.LandRecorderApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

/**
 * Dagger App component declaration
 */
@Component(
    modules = [AndroidSupportInjectionModule::class,
        CoreModule::class,
        DbModule::class,
        LandRecordsModule::class,
        AddRecordFragmentModule::class,
        MainFragmentModule::class,
        ChartFragmentModule::class,
        SettingsModule::class]
)
@Singleton
interface AppComponent : AndroidInjector<LandRecorderApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun setContext(context: Context): Builder
    }
}