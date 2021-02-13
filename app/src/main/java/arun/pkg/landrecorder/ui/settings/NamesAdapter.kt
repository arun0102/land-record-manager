package arun.pkg.landrecorder.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import arun.pkg.landrecorder.databinding.RowDataItemBinding
import arun.pkg.landrecorder.db.models.Owners

/**
 * Adapter class to map List<Owners> with RecyclerView
 */
class NamesAdapter(
    private var namesList: List<Owners> = emptyList()
) : RecyclerView.Adapter<NamesAdapter.NamesHolder>() {

    fun updateData(items: List<Owners>) {
        namesList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowDataItemBinding.inflate(inflater, parent, false)
        return NamesHolder(binding)
    }

    override fun getItemCount() = namesList.size

    override fun onBindViewHolder(holder: NamesHolder, position: Int) {
        holder.rowDataItemBinding.txtName.text = namesList[position].name
    }

    inner class NamesHolder(val rowDataItemBinding: RowDataItemBinding) :
        RecyclerView.ViewHolder(rowDataItemBinding.root)
}