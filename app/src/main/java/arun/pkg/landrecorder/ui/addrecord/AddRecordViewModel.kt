package arun.pkg.landrecorder.ui.addrecord

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arun.pkg.landrecorder.db.models.Khasra
import arun.pkg.landrecorder.db.models.Owners
import arun.pkg.landrecorder.db.models.Record
import arun.pkg.landrecorder.usecases.GetDataUseCase
import arun.pkg.landrecorder.usecases.SaveRecordUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

public class AddRecordViewModel @Inject constructor(
    private val saveRecordUseCase: SaveRecordUseCase,
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {
    var ownersList = MutableLiveData<List<Owners>>()
    var khasrasList = MutableLiveData<List<Khasra>>()

    init {
        getDataUseCase.getAllOwners().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { owners -> ownersList.value = owners }

        getDataUseCase.getAllKhasras().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { khasras -> khasrasList.value = khasras }
    }

    fun saveRecord(record: Record) {
        saveRecordUseCase.saveRecord(record)
    }
}