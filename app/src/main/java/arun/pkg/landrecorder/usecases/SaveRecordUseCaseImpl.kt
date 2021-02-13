package arun.pkg.landrecorder.usecases

import android.util.Log
import arun.pkg.landrecorder.db.RecordsDao
import arun.pkg.landrecorder.db.models.Record
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveRecordUseCaseImpl @Inject constructor(
    private val recordsDao: RecordsDao
) : SaveRecordUseCase {
    override fun saveRecord(record: Record) {
        val count = recordsDao.insertRecord(record)
        Log.d(SaveNameUseCaseImpl::class.java.simpleName, "Insert count - $count")
    }
}