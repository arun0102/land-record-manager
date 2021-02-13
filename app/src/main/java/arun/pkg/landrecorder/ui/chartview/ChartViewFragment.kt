package arun.pkg.landrecorder.ui.chartview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import arun.pkg.landrecorder.R
import arun.pkg.landrecorder.databinding.ChartViewFragmentBinding
import arun.pkg.landrecorder.db.models.Record
import arun.pkg.landrecorder.util.BaseFragment
import arun.pkg.landrecorder.util.ViewModelDelegate
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ChartViewFragment : BaseFragment() {

    companion object {
        fun newInstance() = ChartViewFragment()
    }

    private val viewModel: ChartViewViewModel by ViewModelDelegate()
    private lateinit var binding: ChartViewFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChartViewFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                val recordsList = (observable as ObservableField<List<Record>>).get()
                setChartView(recordsList)
            }
        }
        viewModel.recordListObs.addOnPropertyChangedCallback(callback)
        setChartView(viewModel.recordListObs.get())
    }

    private fun setChartView(recordsList: List<Record>?) {
        recordsList?.isNotEmpty()?.let {
            var map: HashMap<String, Float> = HashMap()
            for (record in recordsList) {
                if (map.containsKey(record.name)) {
                    map[record.name]?.plus(record.area)?.let { added ->
                        map[record.name] = added
                    }
                } else {
                    map.put(record.name, record.area.toFloat())
                }
            }
            val entries: MutableList<PieEntry> = ArrayList()
            val iterator = map.iterator()
            while (iterator.hasNext()) {
                val data = iterator.next()
                entries.add(PieEntry(data.value, data.key))
            }

            val set = PieDataSet(entries, "Records")
            set.colors = ColorTemplate.COLORFUL_COLORS.toMutableList();
            val data = PieData(set)

            view?.findViewById<PieChart>(R.id.chart_view)?.setData(data)

        }
    }
}