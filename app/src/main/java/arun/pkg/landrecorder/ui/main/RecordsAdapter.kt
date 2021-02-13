package arun.pkg.landrecorder.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import arun.pkg.landrecorder.databinding.RowRecordBinding
import arun.pkg.landrecorder.db.models.Record
import kotlin.math.roundToInt

/**
 * Adapter class to map List<Record> with RecyclerView
 */
class RecordsAdapter(
    private var recordList: List<Record> = emptyList()
) : RecyclerView.Adapter<RecordsAdapter.RecordsHolder>() {

    fun updateData(items: List<Record>) {
        recordList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowRecordBinding.inflate(inflater, parent, false)
        return RecordsHolder(binding)
    }

    override fun getItemCount() = recordList.size

    override fun onBindViewHolder(holder: RecordsHolder, position: Int) {
        holder.rowExperienceBinding.txtName.text = recordList[position].name
        holder.rowExperienceBinding.txtKhasra.text = recordList[position].khasra
        holder.rowExperienceBinding.txtArea.text = recordList[position].area.toString()
        holder.rowExperienceBinding.txtPartOwned.text =
            String.format("%.2f", recordList[position].partOwned)
        holder.rowExperienceBinding.txtType.text = recordList[position].allotType
    }

    inner class RecordsHolder(val rowExperienceBinding: RowRecordBinding) :
        RecyclerView.ViewHolder(rowExperienceBinding.root)
}