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

package arun.pkg.landrecorder.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import arun.pkg.landrecorder.db.models.Record
import io.reactivex.Observable


/**
 * This Data Access Object handles Room database operations for the [Record] class.
 */
@Dao
abstract class RecordsDao {
    @Query("SELECT * FROM record GROUP BY khasra ORDER BY khasra ASC")
    abstract fun getAllRecords(): Observable<List<Record>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRecord(record: Record) : Long
}
