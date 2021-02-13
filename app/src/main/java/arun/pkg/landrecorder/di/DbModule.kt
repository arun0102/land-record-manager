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

import android.content.Context
import arun.pkg.landrecorder.db.KhasraDao
import arun.pkg.landrecorder.db.LandRecordDatabase
import arun.pkg.landrecorder.db.OwnersDao
import arun.pkg.landrecorder.db.RecordsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module to provide injections for DB/DAO classes
 */
@Module
class DbModule {
    @Provides
    @Singleton
    fun provideLandRecordDatabase(context: Context): LandRecordDatabase {
        return LandRecordDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideRecordsDao(db: LandRecordDatabase): RecordsDao {
        return db.recordsDao()
    }

    @Provides
    @Singleton
    fun provideOwnersDao(db: LandRecordDatabase): OwnersDao {
        return db.ownerDao()
    }

    @Provides
    @Singleton
    fun provideKhasraDao(db: LandRecordDatabase): KhasraDao {
        return db.khasraDao()
    }
}