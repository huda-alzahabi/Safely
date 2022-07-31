package com.finalproj.safely.patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finalproj.safely.R
import kotlin.collections.ArrayList

class TimesAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TimeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.date_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TimeViewHolder -> {
                holder.t.text = items[position]
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(timeList: ArrayList<String>) {
        items = timeList
        return notifyDataSetChanged()
    }

    inner class TimeViewHolder
    constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val t = itemView.findViewById<View>(R.id.day) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
