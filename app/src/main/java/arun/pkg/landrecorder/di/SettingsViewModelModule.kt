package arun.pkg.landrecorder.di

import androidx.lifecycle.ViewModel
import arun.pkg.landrecorder.ui.settings.SettingsFragment
import arun.pkg.landrecorder.ui.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module to provide AddRecordViewModel functionality.
 */
@Module
interface SettingsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}

@Module
interface SettingsModule {
    @ContributesAndroidInjector(modules = [SettingsViewModelModule::class])
    fun settingsFragment(): SettingsFragment
}
