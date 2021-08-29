package com.swayams.restaurant.assignment.data.model

import com.google.gson.annotations.SerializedName

data class Menu(

    @SerializedName("menus")
    val menusList: List<Menus>
) {
    data class Menus(

        @SerializedName("restaurantId")
        val restaurantId: Int,
        @SerializedName("categories")
        val categories: List<Categories>
    ) {
        data class Categories(
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("menu_items")
            val menu_items: List<Menu_items>
        ) {
            data class Menu_items(
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("description")
                val description: String,
                @SerializedName("price")
                val price: String,
                @SerializedName("images")
                val images: List<String>
            )
        }
    }
}
