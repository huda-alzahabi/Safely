package com.finalproj.safely

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.collections.ArrayList

class DoctorsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"

    private var items: List<Doctor> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.doctors_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is BlogViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<Doctor>) {
        items = blogList
        return notifyDataSetChanged()
        Log.d("Submit",items.toString())
    }

    class BlogViewHolder
    constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        val txt = itemView.findViewById<View>(R.id.txt) as TextView
        val sub_txt = itemView.findViewById<View>(R.id.sub_txt) as TextView
        val img = itemView.findViewById<View>(R.id.img) as ImageView
        val sub_txt2 = itemView.findViewById<View>(R.id.sub_txt2) as TextView


        fun bind(doctor: Doctor) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(doctor.image)
                .into(img)
            txt.setText(doctor.name)
            sub_txt.setText(doctor.specialty)
            sub_txt2.setText(doctor._id)


        }

    }

}