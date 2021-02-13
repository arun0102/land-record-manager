package arun.pkg.landrecorder.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import arun.pkg.landrecorder.db.models.Khasra
import arun.pkg.landrecorder.db.models.Owners
import arun.pkg.landrecorder.db.models.Record

/**
 * The Room database for this app
 */
@Database(
    entities = [
        Owners::class,
        Record::class,
        Khasra::class],
    version = 1, exportSchema = false
)
abstract class LandRecordDatabase : RoomDatabase() {

    abstract fun ownerDao(): OwnersDao

    abstract fun recordsDao(): RecordsDao

    abstract fun khasraDao(): KhasraDao

    companion object {
        private const val DATABASE_NAME = "land_records_db"

        // For Singleton instantiation
        @Volatile
        private var instance: LandRecordDatabase? = null

        fun getInstance(context: Context): LandRecordDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LandRecordDatabase {
            return Room.databaseBuilder(
                context, LandRecordDatabase::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries().build()
        }
    }
}
