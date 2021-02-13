package arun.pkg.landrecorder.usecases

import arun.pkg.landrecorder.db.OwnersDao
import arun.pkg.landrecorder.db.models.Owners
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveNameUseCaseImpl @Inject constructor(
    private val ownersDao: OwnersDao
) : SaveNameUseCase {
    override fun saveName(name: String) {
        if (ownersDao.getOwnerWithName(name).isEmpty()) {
            ownersDao.insertOwner(Owners(name))
        }
    }

}