package com.swayams.restaurant.assignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestaurantsList(
    @Expose
    @SerializedName("restaurants")
    val restaurants: List<Restaurants>
) {
    data class Restaurants(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name", alternate = ["names"])
        val name: String,
        @SerializedName("neighborhood")
        val neighborhood: String,
        @SerializedName("photograph")
        val photograph: String,
        @SerializedName("address")
        val address: String,
        @SerializedName("latlng")
        val latlng: Latlng,
        @SerializedName("cuisine_type")
        val cuisine_type: String,
        @SerializedName("operating_hours")
        val operating_hours: Operating_hours,
        @SerializedName("reviews")
        val reviews: List<Reviews>,
        var menus: Menus
    ) {
        data class Latlng(

            @SerializedName("lat")
            val lat: Double,
            @SerializedName("lng")
            val lng: Double
        )

        data class Operating_hours(

            @SerializedName("Monday")
            val monday: String,
            @SerializedName("Tuesday")
            val tuesday: String,
            @SerializedName("Wednesday")
            val wednesday: String,
            @SerializedName("Thursday")
            val thursday: String,
            @SerializedName("Friday")
            val friday: String,
            @SerializedName("Saturday")
            val saturday: String,
            @SerializedName("Sunday")
            val sunday: String
        )

        data class Reviews(

            @SerializedName("name")
            val name: String,
            @SerializedName("date")
            val date: String,
            @SerializedName("rating")
            val rating: Int,
            @SerializedName("comments")
            val comments: String
        )

        data class Menus(

            val restaurantId: Int,
            val categories: List<Categories>,
            val dishList: List<String>
        ) {
            data class Categories(
                val id: String,
                val name: String,
                val menu_items: List<Menu_items>
            ) {
                data class Menu_items(
                    val id: String,
                    val name: String,
                    val description: String,
                    val price: String,
                    val images: List<String>
                )
            }
        }


    }

}
