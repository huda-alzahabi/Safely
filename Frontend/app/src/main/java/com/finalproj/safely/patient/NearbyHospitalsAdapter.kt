package com.finalproj.safely.patient

import android.content.Context
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
import java.util.*
import kotlin.collections.ArrayList

class NearbyHospitalsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private var items: List<HospitalResponse> = ArrayList()
    private var fullList: List<HospitalResponse> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HospitalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.nearby_card_view, parent, false)
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
        val sub_txt2 = itemView.findViewById<View>(R.id.sub_txt2) as TextView
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
            val applicationContext: Context = itemView.context
            val hospital_name =
                hospitalResponse.user.name + " " + applicationContext.resources.getString(R.string.hospital)

            txt.setText(hospital_name)

            val distance =
                hospitalResponse.distance + " " + applicationContext.resources.getString(R.string.away)
            sub_txt.setText(distance)
            sub_txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_hospital_location_on_24,
                0,
                0,
                0);

            sub_txt2.setText(hospitalResponse.phone_number)
            sub_txt2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_phone_24,
                0,
                0,
                0);


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
                        if (item.user.name?.lowercase(Locale.ROOT)!!
                                .contains(filterPattern) || item.distance?.lowercase(Locale.ROOT)!!
                                .contains(filterPattern)
                        ) {
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