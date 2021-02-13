/*
 *
 *  * © 2020 Infosys Limited, Bangalore, India. All Rights Reserved.
 *  * Version:1.0.0.0
 *  *
 *  * Except for any free or open source software components embedded in this Infosys proprietary software program (“Program”),
 *  * this Program is protected by copyright laws, international treaties and other pending or existing intellectual property
 *  * rights in India, the United States and other countries. Except as expressly permitted, any unauthorized reproduction,
 *  * storage, transmission in any form or by any means (including without limitation electronic, mechanical, printing, photocopying, recording or otherwise),
 *  * or any distribution of this Program, or any portion of it, may result in severe civil and criminal penalties, and will be prosecuted to the maximum
 *  * extent possible under the law.
 *  *
 *
 */

package arun.pkg.landrecorder.di

import androidx.lifecycle.ViewModel
import arun.pkg.landrecorder.ui.main.MainFragment
import arun.pkg.landrecorder.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module to provide AddRecordViewModel functionality.
 */
@Module
interface MainFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}

@Module
interface MainFragmentModule {
    @ContributesAndroidInjector(modules = [MainFragmentViewModelModule::class])
    fun mainFragment(): MainFragment
}
