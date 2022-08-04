package com.finalproj.safely.patient

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finalproj.safely.R
import kotlin.collections.ArrayList

class DaysAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<DrAvailabilityResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.date_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DayViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(dayList: List<DrAvailabilityResponse>) {
        items = dayList
        return notifyDataSetChanged()
    }

    inner class DayViewHolder
    constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val day = itemView.findViewById<View>(R.id.day) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(availabilityResponse: DrAvailabilityResponse) {
            Log.d("DayViewHolder", "bind: " + availabilityResponse.day)
            day.setText(availabilityResponse.day)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}