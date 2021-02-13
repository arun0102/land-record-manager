package arun.pkg.landrecorder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import arun.pkg.landrecorder.R
import arun.pkg.landrecorder.databinding.MainFragmentBinding
import arun.pkg.landrecorder.db.models.Record
import arun.pkg.landrecorder.ui.addrecord.AddRecordFragment
import arun.pkg.landrecorder.ui.chartview.ChartViewFragment
import arun.pkg.landrecorder.util.BaseFragment
import arun.pkg.landrecorder.util.ViewModelDelegate

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by ViewModelDelegate()
    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddRecord.setOnClickListener {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.container, AddRecordFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
        binding.fabChartView.setOnClickListener {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.container, ChartViewFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }

        val callback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                val recordsList = (observable as ObservableField<List<Record>>).get()
                setRecordsListAdapter(recordsList)
            }
        }
        viewModel.recordListObs.addOnPropertyChangedCallback(callback)
        setRecordsListAdapter(viewModel.recordListObs.get())
    }

    private fun setRecordsListAdapter(recordsList: List<Record>?) {
        recordsList?.let {
            if (null != binding.recyclerRecords.adapter) {
                (binding.recyclerRecords.adapter as RecordsAdapter).updateData(recordsList)
            } else {
                val recordsAdapter = RecordsAdapter(recordsList)
                binding.recyclerRecords.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.recyclerRecords.adapter = recordsAdapter
            }
        }
    }
}