package arun.pkg.landrecorder.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import arun.pkg.landrecorder.databinding.RowDataItemBinding
import arun.pkg.landrecorder.db.models.Khasra

/**
 * Adapter class to map List<Khasra> with RecyclerView
 */
class KhasrasAdapter(
    private var khasrasList: List<Khasra> = emptyList()
) : RecyclerView.Adapter<KhasrasAdapter.KhasraHolder>() {

    fun updateData(items: List<Khasra>) {
        khasrasList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KhasraHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowDataItemBinding.inflate(inflater, parent, false)
        return KhasraHolder(binding)
    }

    override fun getItemCount() = khasrasList.size

    override fun onBindViewHolder(holder: KhasraHolder, position: Int) {
        holder.rowDataItemBinding.txtName.text = khasrasList[position].khasra
    }

    inner class KhasraHolder(val rowDataItemBinding: RowDataItemBinding) :
        RecyclerView.ViewHolder(rowDataItemBinding.root)
}