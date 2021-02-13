package arun.pkg.landrecorder.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import arun.pkg.landrecorder.db.models.Khasra
import io.reactivex.Observable

/**
 * This Data Access Object handles Room database operations for the [Khasra] class.
 */
@Dao
abstract class KhasraDao {
    @Query("SELECT * FROM khasras")
    abstract fun getAllKhasras(): Observable<List<Khasra>>

    @Query("SELECT * FROM khasras where khasra = :khasra")
    abstract fun getKhasraWithName(khasra: String): List<Khasra>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertKhasra(khasra: Khasra)
}
