package com.campodonico.ec_final.data.adapter
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.campodonico.ec_final.R
import com.campodonico.ec_final.databinding.ItemDataBinding

class DataAdapter(private val dataList: List<DataItem>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DataViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.bind(dataItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemDataBinding.bind(itemView.rootView)

        fun bind(dataItem: DataItem) {
            binding.txtName.text = dataItem.name
            binding.txtCharacter.text = dataItem.character
            binding.txtGameSeries.text = dataItem.gameSeries
            binding.txtAmiiboSeries.text = dataItem.amiiboSeries

            // Cargar la imagen usando Glide u otra librería de carga de imágenes
            Glide.with(itemView)
                .load(Uri.parse(dataItem.imageUri))
                .apply(RequestOptions.centerCropTransform())
                .into(binding.imgPhoto)
        }
    }
}
