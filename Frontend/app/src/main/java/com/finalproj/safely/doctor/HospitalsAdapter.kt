package com.finalproj.safely.doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.finalproj.safely.R
import com.finalproj.safely.patient.Hospital

class HospitalsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Hospital> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HospitalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.doctors_card_view, parent, false)
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

    fun submitList(hospitalList: List<Hospital>) {
        items = hospitalList
        return notifyDataSetChanged()
    }

    class HospitalViewHolder
    constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        val txt = itemView.findViewById<View>(R.id.txt) as TextView
        val sub_txt = itemView.findViewById<View>(R.id.sub_txt) as TextView
        val img = itemView.findViewById<View>(R.id.img) as ImageView

        fun bind(hospital: Hospital) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.hospital)
                .error(R.drawable.hospital)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(hospital.user.name)
                .into(img)
            txt.setText(hospital.user.name)
            sub_txt.setText(hospital.phone_number)

        }

    }
}