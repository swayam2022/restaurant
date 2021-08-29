package com.swayams.restaurant.assignment.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swayams.restaurant.assignment.data.model.Menu
import com.swayams.restaurant.assignment.data.model.RestaurantsList
import com.swayams.restaurant.assignment.ui.base.BaseViewModel
import com.swayams.restaurant.assignment.utils.common.Utils
import com.swayams.restaurant.assignment.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.reflect.Type


class RestaurantViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val allRestaurants: ArrayList<RestaurantsList.Restaurants>

) : BaseViewModel(schedulerProvider, compositeDisposable) {
    companion object {
        const val TAG = "RestaurantViewModel"
    }

    lateinit var context: Context

    val restaurantNamesField: MutableLiveData<List<RestaurantsList.Restaurants>> = MutableLiveData()

    override fun onCreate() {

        val jsonFileStringRestaurant: String? =
            Utils.getJsonFromAssets(context, "Restaurant.json")

        val jsonFileStringMenu: String? =
            Utils.getJsonFromAssets(context, "Menus.json")

        val gson = Gson()
        val restaurantType: Type = object : TypeToken<RestaurantsList?>() {}.type
        val menuType: Type = object : TypeToken<Menu?>() {}.type

        val restaurantList =
            gson.fromJson<RestaurantsList>(jsonFileStringRestaurant, restaurantType)
        val completeMenuList =
            gson.fromJson<Menu>(jsonFileStringMenu, menuType)


        for (restaurant in restaurantList.restaurants) {
            var menuListForRestaurant: RestaurantsList.Restaurants.Menus? = null;
            var menuList =
                ArrayList<RestaurantsList.Restaurants.Menus.Categories.Menu_items>()
            var categoriesList = ArrayList<RestaurantsList.Restaurants.Menus.Categories>()
            var dishList = ArrayList<String>()
            for (menu in completeMenuList.menusList) {
                if (restaurant.id == menu.restaurantId) {

                    for (categories in menu.categories) {
                        for (menuItems in categories.menu_items) {
                            var id = menuItems.id;
                            var name = menuItems.name;
                            var description = menuItems.description;
                            var price = menuItems.price;
                            var image = menuItems.images;
                            dishList.add(name.toLowerCase())
                            menuList.add(
                                RestaurantsList.Restaurants.Menus.Categories.Menu_items(
                                    id,
                                    name,
                                    description,
                                    price,
                                    image
                                )
                            )
                        }

                        categoriesList.add(
                            RestaurantsList.Restaurants.Menus.Categories(
                                categories.id,
                                categories.name, menuList
                            )
                        )

                    }

                }
                menuListForRestaurant =
                    RestaurantsList.Restaurants.Menus(
                        menu.restaurantId,
                        categoriesList, dishList

                    )

            }
            if (menuListForRestaurant != null) {
                restaurant.menus = menuListForRestaurant
            };
        }

        allRestaurants.addAll(restaurantList.restaurants)
        Log.d(TAG, "The item name is " + allRestaurants[0].menus.categories[0].menu_items[0].name);
    }


    init {
        restaurantNamesField.postValue(
            allRestaurants
        )

    }

    fun sendContext(applicationContext: Context) {
        context = applicationContext
    }


}