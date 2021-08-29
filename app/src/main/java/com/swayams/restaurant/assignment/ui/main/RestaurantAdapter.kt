package com.swayam.restaurant.assignment.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swayams.restaurant.assignment.R
import com.swayams.restaurant.assignment.data.model.RestaurantsList
import com.swayams.restaurant.assignment.ui.main.RestaurantViewHolder
import kotlinx.android.synthetic.main.item_restaurants.view.*
import java.util.*
import kotlin.collections.ArrayList

class RestaurantAdapter(
    private var dataList: ArrayList<RestaurantsList.Restaurants>
) : RecyclerView.Adapter<RestaurantViewHolder>(
) {


    fun filterList(filterllist: ArrayList<RestaurantsList.Restaurants>) {
        // below line is to add our filtered
        // list in our course array list.
        dataList = filterllist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurants, parent, false)
        )

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bindData(dataList[position])

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun appendData(dataList: List<RestaurantsList.Restaurants>) {
        val oldCount = itemCount
        this.dataList.addAll(dataList)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount > 0 && currentCount > oldCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    fun updateList(list: List<RestaurantsList.Restaurants>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }


}