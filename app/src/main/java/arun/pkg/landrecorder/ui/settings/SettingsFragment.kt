package arun.pkg.landrecorder.ui.settings

import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import arun.pkg.landrecorder.R
import arun.pkg.landrecorder.databinding.SettingsFragmentBinding
import arun.pkg.landrecorder.util.BaseFragment
import arun.pkg.landrecorder.util.ViewModelDelegate

class SettingsFragment : BaseFragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private val viewModel: SettingsViewModel by ViewModelDelegate()
    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsFragmentBinding.inflate(
            inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgAddName.setOnClickListener {
            showAddNameDialog()
        }
        binding.imgAddKhasra.setOnClickListener {
            showAddKhasraDialog()
        }

        setAdapters()
    }

    private fun setAdapters() {
        viewModel.namesList.observe(viewLifecycleOwner, Observer { names ->
            (binding.recyclerNames.adapter as NamesAdapter).updateData(names)
        })

        viewModel.khasraList.observe(viewLifecycleOwner, Observer { khasras ->
            (binding.recyclerKhasras.adapter as KhasrasAdapter).updateData(khasras)
        })
        binding.recyclerNames.adapter = NamesAdapter(emptyList())
        binding.recyclerKhasras.adapter = KhasrasAdapter(emptyList())
    }

    private fun showAddNameDialog() {
        activity?.let {
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_add_entry)
            dialog.findViewById<TextView>(R.id.txt_dialog_header).text = "Add Name"
            val value = dialog.findViewById(R.id.et_value) as EditText
            value.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            val addBtn = dialog.findViewById(R.id.btn_add) as Button
            addBtn.setOnClickListener {
                if (value.text.isNotEmpty()) {
                    dialog.dismiss()
                    viewModel.saveName(value.text.toString())
                }
            }
            dialog.show()
        }
    }

    private fun showAddKhasraDialog() {
        activity?.let {
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_add_entry)
            dialog.findViewById<TextView>(R.id.txt_dialog_header).text = "Add Khasra"
            val value = dialog.findViewById(R.id.et_value) as EditText
            val addBtn = dialog.findViewById(R.id.btn_add) as Button
            addBtn.setOnClickListener {
                if (value.text.isNotEmpty()) {
                    dialog.dismiss()
                    viewModel.saveKhasra(value.text.toString())
                }
            }
            dialog.show()
        }
    }
}