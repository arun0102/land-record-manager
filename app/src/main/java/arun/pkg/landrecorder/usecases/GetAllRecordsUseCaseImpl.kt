package arun.pkg.landrecorder.usecases

import arun.pkg.landrecorder.db.RecordsDao
import arun.pkg.landrecorder.db.models.Record
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllRecordsUseCaseImpl @Inject constructor(
    private val recordsDao: RecordsDao
) : GetAllRecordsUseCase {
    override fun getAllRecords(): Observable<List<Record>> {
        return recordsDao.getAllRecords()
    }
}