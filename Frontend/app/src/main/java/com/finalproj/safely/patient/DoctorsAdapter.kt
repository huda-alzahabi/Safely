package com.finalproj.safely.patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.finalproj.safely.R
import kotlin.collections.ArrayList

class DoctorsAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Doctor> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoctorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.doctors_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is DoctorViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(doctorList: List<Doctor>) {
        items = doctorList
        return notifyDataSetChanged()
    }

   inner class DoctorViewHolder
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
            val position:Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }

        }

        fun bind(doctor: Doctor) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(R.drawable.avatar)
                .into(img)
            
            txt.setText(doctor.user.name)
            sub_txt.setText(doctor.specialty)

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position:Int)
    }
}