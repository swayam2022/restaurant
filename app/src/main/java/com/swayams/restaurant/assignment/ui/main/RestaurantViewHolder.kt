package com.swayams.restaurant.assignment.ui.main

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.swayams.restaurant.assignment.data.model.RestaurantsList
import kotlinx.android.synthetic.main.item_restaurants.view.*
import java.util.*

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(
        items: RestaurantsList.Restaurants
    ) {
        itemView.tv_restaurant.text = items.name
        val rnd = Random()
        val currentColor: Int =
            Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        itemView.view_restaurant.setBackgroundColor(currentColor)

    }
}