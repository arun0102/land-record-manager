package arun.pkg.landrecorder.usecases

import arun.pkg.landrecorder.db.models.Khasra
import arun.pkg.landrecorder.db.models.Owners
import arun.pkg.landrecorder.db.models.Record
import io.reactivex.Observable

interface SaveRecordUseCase {
    fun saveRecord(record: Record)
}

interface GetAllRecordsUseCase {
    fun getAllRecords(): Observable<List<Record>>
}

interface GetDataUseCase {

    fun getAllOwners(): Observable<List<Owners>>

    fun getAllKhasras(): Observable<List<Khasra>>
}

interface SaveNameUseCase {
    fun saveName(name: String)
}

interface SaveKhasraUseCase {
    fun saveKhasra(khasra: String)
}