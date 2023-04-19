package com.example.paginationexample

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationexample.models.Data
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class PassengerAdapter() : PagingDataAdapter<Data,PassengerAdapter.ViewHolder>(
    COMPARATOR) {

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }

        }
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pessenger_item_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val passenger = getItem(position)

        holder.tvName.text = passenger?.name
        holder.tvTrips.text = passenger?.trips.toString()


    }
    
    // Holds the views for adding it to image and text
    class ViewHolder(passengerView: View) : RecyclerView.ViewHolder(passengerView) {
        val tvName: TextView = passengerView.findViewById(R.id.tvName)
        val tvTrips: TextView = passengerView.findViewById(R.id.tvTrips)

    }
}