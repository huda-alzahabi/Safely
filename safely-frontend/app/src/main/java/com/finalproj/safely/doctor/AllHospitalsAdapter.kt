package com.finalproj.safely.doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.finalproj.safely.R
import com.finalproj.safely.patient.HospitalResponse
import java.util.*
import kotlin.collections.ArrayList

class AllHospitalsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private var items: List<HospitalResponse> = ArrayList()
    private var fullList: List<HospitalResponse> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HospitalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is HospitalViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(hospitalResponseList: List<HospitalResponse>) {
        items = hospitalResponseList
        fullList = hospitalResponseList
        return notifyDataSetChanged()
    }

    inner class HospitalViewHolder
    constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val txt = itemView.findViewById<View>(R.id.txt) as TextView
        val sub_txt = itemView.findViewById<View>(R.id.sub_txt) as TextView
        val img = itemView.findViewById<View>(R.id.img) as ImageView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(hospitalResponse: HospitalResponse) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.hospital)
                .error(R.drawable.hospital)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(hospitalResponse.user.name)
                .into(img)
            txt.setText(hospitalResponse.user.name)
            sub_txt.setText(hospitalResponse.phone_number)

        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<HospitalResponse> = ArrayList()

                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(fullList)
                } else {
                    val filterPattern =
                        constraint.toString().lowercase(Locale.ROOT).trim { it <= ' ' }
                    for (item in fullList) {
                        if (item.user.name?.lowercase(Locale.ROOT)!!.contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items = results?.values as List<HospitalResponse>
                notifyDataSetChanged()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}