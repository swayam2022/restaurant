package com.swayams.restaurant.assignment.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.swayam.restaurant.assignment.ui.main.RestaurantAdapter
import com.swayams.restaurant.assignment.R
import com.swayams.restaurant.assignment.data.model.RestaurantsList
import com.swayams.restaurant.assignment.di.component.ActivityComponent
import com.swayams.restaurant.assignment.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_restaurant.*
import javax.inject.Inject


class RestaurantActivity : BaseActivity<RestaurantViewModel>() {

    companion object {
        const val TAG = "RestaurantActivity"
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private var userSearched: ArrayList<RestaurantsList.Restaurants> = ArrayList()
    private var restaurantListAdapter: RestaurantAdapter = RestaurantAdapter(userSearched)


    override fun provideLayoutId(): Int = R.layout.activity_restaurant

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


    override fun setupView(savedInstanceState: Bundle?) {
        setUpToolBar()
        viewModel.sendContext(applicationContext)
        recyclerViewSetUp()
    }

    private fun recyclerViewSetUp() {
        rv_restaurant.apply {
            adapter = restaurantListAdapter
            layoutManager = linearLayoutManager
        }
    }


    private fun setUpToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = getString(R.string.title_restaurant)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.actionView as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }

        })
        return true
    }

    private fun filter(newText: String) {
        val filteredlist: ArrayList<RestaurantsList.Restaurants> = ArrayList()

        // running a for loop to compare elements.
        for (item in userSearched) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name.toLowerCase().contains(newText.toLowerCase())
                || item.cuisine_type.toLowerCase().contains(newText.toLowerCase())
                || item.menus.dishList.toString().toLowerCase().contains(newText.toLowerCase())
            ) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }






        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            //logging to check
            Log.i(TAG, "No Data Found")
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            restaurantListAdapter.filterList(filteredlist)
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.restaurantNamesField.observe(this, Observer {
            it.run {
                restaurantListAdapter.appendData(this)
                restaurantListAdapter.updateList(this)
                userSearched = this as ArrayList<RestaurantsList.Restaurants>

            }
        })


    }


}