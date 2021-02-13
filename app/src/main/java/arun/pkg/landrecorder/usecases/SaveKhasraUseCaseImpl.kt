package arun.pkg.landrecorder.usecases

import arun.pkg.landrecorder.db.KhasraDao
import arun.pkg.landrecorder.db.models.Khasra
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveKhasraUseCaseImpl @Inject constructor(
    private val khasraDao: KhasraDao
) : SaveKhasraUseCase {
    override fun saveKhasra(khasra: String) {
        if (khasraDao.getKhasraWithName(khasra).isEmpty()) {
            khasraDao.insertKhasra(Khasra(khasra))
        }
    }

}