package arun.pkg.landrecorder.usecases

import arun.pkg.landrecorder.db.KhasraDao
import arun.pkg.landrecorder.db.OwnersDao
import arun.pkg.landrecorder.db.models.Khasra
import arun.pkg.landrecorder.db.models.Owners
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDataUseCaseImpl @Inject constructor(
    private val ownersDao: OwnersDao,
    private val khasraDao: KhasraDao
) : GetDataUseCase {
    override fun getAllOwners(): Observable<List<Owners>> {
        return ownersDao.getAllOwners()
    }

    override fun getAllKhasras(): Observable<List<Khasra>> {
        return khasraDao.getAllKhasras()
    }
}