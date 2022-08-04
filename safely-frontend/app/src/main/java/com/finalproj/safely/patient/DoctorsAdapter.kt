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

class DoctorsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {
    private var items: List<DoctorResponse> = ArrayList()
    private var fullList: List<DoctorResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoctorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.nearby_card_view, parent, false)
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

    fun submitList(doctorList: List<DoctorResponse>) {
        items = doctorList
        fullList = doctorList
        return notifyDataSetChanged()
    }

    inner class DoctorViewHolder
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

        fun bind(doctor: DoctorResponse) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(R.drawable.avatar)
                .into(img)

            val dr = "Dr. " + doctor.user.name
            txt.setText(dr)
            sub_txt.setText(doctor.specialty)
            sub_txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_medical_services_24,
                0,
                0,
                0);
            val applicationContext: Context = itemView.context
            val exp =
                doctor.years_of_experience + " " + applicationContext.getString(R.string.years_of_experience)
            sub_txt2.setText(exp)

        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<DoctorResponse> = ArrayList()

                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(fullList)
                } else {
                    val filterPattern =
                        constraint.toString().lowercase(Locale.ROOT).trim { it <= ' ' }
                    for (item in fullList) {
                        if (item.specialty?.lowercase(Locale.ROOT)!!
                                .contains(filterPattern) || item.user.name?.lowercase(Locale.ROOT)!!
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
                items = results?.values as List<DoctorResponse>
                notifyDataSetChanged()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}