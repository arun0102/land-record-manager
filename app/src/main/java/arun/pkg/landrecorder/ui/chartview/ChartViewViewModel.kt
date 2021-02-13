package arun.pkg.landrecorder.ui.chartview

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import arun.pkg.landrecorder.db.models.Record
import arun.pkg.landrecorder.usecases.GetAllRecordsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChartViewViewModel @Inject constructor(
    private val getAllRecordsUseCase: GetAllRecordsUseCase
) : ViewModel() {

    var disposable = CompositeDisposable()
    val recordListObs = ObservableField<List<Record>>(emptyList())

    init {
        fetchAllRecords()
    }

    private fun fetchAllRecords() {
        getAllRecordsUseCase.getAllRecords()
        disposable.add(getAllRecordsUseCase.getAllRecords()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { recordsList -> recordListObs.set(recordsList) })
    }
}