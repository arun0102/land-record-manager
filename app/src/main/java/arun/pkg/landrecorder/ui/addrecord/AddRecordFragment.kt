package arun.pkg.landrecorder.ui.addrecord

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import arun.pkg.landrecorder.databinding.AddRecordFragmentBinding
import arun.pkg.landrecorder.db.models.AllotType
import arun.pkg.landrecorder.db.models.Record
import arun.pkg.landrecorder.util.BaseFragment
import arun.pkg.landrecorder.util.ViewModelDelegate

class AddRecordFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            AddRecordFragment()
    }

    private val viewModel: AddRecordViewModel by ViewModelDelegate()
    private lateinit var binding: AddRecordFragmentBinding
    private var nameList = listOf("Select name")
    private var khasraList = listOf("Select khasra")
    private val allotTypeList =
        listOf("Select allot type", AllotType.VASIYAT, AllotType.PURCHASED)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddRecordFragmentBinding.inflate(
            inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddRecord.setOnClickListener {
            checkAndAddRecord()
        }

        setSpinnersData()
        viewModel.ownersList.observe(viewLifecycleOwner, Observer {
            nameList = listOf("Select name")
            nameList += it.map { owner -> owner.name }.toList()
            setSpinnersData()
        })

        viewModel.khasrasList.observe(viewLifecycleOwner, Observer {
            khasraList = listOf("Select khasra")
            khasraList += it.map { khasra -> khasra.khasra }.toList()
            setSpinnersData()
        })
    }

    private fun setSpinnersData() {
        context?.let {
            val nameArrayAdapter = ArrayAdapter<String>(
                it, android.R.layout.simple_spinner_dropdown_item,
                nameList
            )
            val khasraArrayAdapter = ArrayAdapter<String>(
                it, android.R.layout.simple_spinner_dropdown_item,
                khasraList
            )

            val allotTypeArrayAdapter = ArrayAdapter<String>(
                it, android.R.layout.simple_spinner_dropdown_item,
                allotTypeList
            )

            binding.spinnerName.adapter = nameArrayAdapter
            binding.spinnerKhasra.adapter = khasraArrayAdapter
            binding.spinnerAllotType.adapter = allotTypeArrayAdapter
        }
    }

    private fun checkAndAddRecord() {
        if (binding.spinnerName.selectedItemPosition != 0
            && binding.spinnerKhasra.selectedItemPosition != 0
            && binding.spinnerAllotType.selectedItemPosition != 0
            && binding.etPartOwned.text.isValid()
            && binding.etArea.text.isNotEmpty()
        ) {
            val name = nameList[binding.spinnerName.selectedItemPosition]
            val khasra = khasraList[binding.spinnerKhasra.selectedItemPosition]
            val allotType = allotTypeList[binding.spinnerAllotType.selectedItemPosition]
            val partOwned: Double = binding.etPartOwned.text.toString().toDoubleValue()
            val area: Int = binding.etArea.text.toString().toInt()
            viewModel.saveRecord(
                Record(
                    name = name,
                    khasra = khasra,
                    partOwned = partOwned,
                    area = area,
                    allotType = allotType
                )
            )
            activity?.supportFragmentManager?.popBackStack()
        } else {
            Toast.makeText(activity, "Invalid data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun String.isFraction(): Boolean {
        return this.contains("/")
    }

    private fun String.toDoubleValue(): Double {
        return if (isFraction()) {
            substringBefore("/").toDouble().div(substringAfter("/").toDouble())
        } else {
            this.toDouble()
        }
    }

    private fun Editable.isValid(): Boolean {
        return isNotEmpty() && toString().toDoubleValue() <= 1
    }
}