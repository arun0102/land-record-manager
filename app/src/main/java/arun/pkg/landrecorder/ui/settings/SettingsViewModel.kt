package arun.pkg.landrecorder.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arun.pkg.landrecorder.db.models.Khasra
import arun.pkg.landrecorder.db.models.Owners
import arun.pkg.landrecorder.usecases.GetDataUseCase
import arun.pkg.landrecorder.usecases.SaveKhasraUseCase
import arun.pkg.landrecorder.usecases.SaveNameUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val saveNameUseCase: SaveNameUseCase,
    private val saveKhasraUseCase: SaveKhasraUseCase,
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {
    val namesList = MutableLiveData<List<Owners>>(emptyList())
    val khasraList = MutableLiveData<List<Khasra>>(emptyList())

    init {
        getDataUseCase.getAllOwners().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                namesList.value = list
            }

        getDataUseCase.getAllKhasras().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                khasraList.value = list
            }
    }

    fun saveName(name: String) {
        saveNameUseCase.saveName(name)
    }

    fun saveKhasra(khasra: String) {
        saveKhasraUseCase.saveKhasra(khasra)
    }
}