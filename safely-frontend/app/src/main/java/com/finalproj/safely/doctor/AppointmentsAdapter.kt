package com.finalproj.safely.doctor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.finalproj.safely.R

class AppointmentsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<AppointmentResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppointmentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.appointments_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is AppointmentViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(doctorList: List<AppointmentResponse>) {
        items = doctorList
        return notifyDataSetChanged()
    }

    inner class AppointmentViewHolder
    constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val txt = itemView.findViewById<View>(R.id.p_name) as TextView
        val sub_txt = itemView.findViewById<View>(R.id.appt_day) as TextView
        val sub_txt2 = itemView.findViewById<View>(R.id.appt_time) as TextView

        val img = itemView.findViewById<View>(R.id.img) as ImageView

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(appointmentResponse: AppointmentResponse) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.calendar)
                .error(R.drawable.calendar)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(R.drawable.calendar)
                .into(img)
            txt.setText(appointmentResponse.patient_name)
            sub_txt.setText(appointmentResponse.day)
            sub_txt2.setText(appointmentResponse.time)

            sub_txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_today_24,
                0,
                0,
                0);
            sub_txt2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_access_time_24,
                0,
                0,
                0);


        }

        override fun onClick(p0: View?) {

        }
    }
}