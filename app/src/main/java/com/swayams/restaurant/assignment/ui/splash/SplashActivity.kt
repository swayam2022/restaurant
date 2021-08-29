package com.swayams.restaurant.assignment.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import com.swayams.restaurant.assignment.di.component.ActivityComponent
import com.swayams.restaurant.assignment.ui.base.BaseActivity
import com.swayams.restaurant.assignment.ui.main.RestaurantActivity
import com.swayams.restaurant.assignment.utils.common.Event
import com.swayams.restaurant.assignment.R

class SplashActivity : BaseActivity<SplashViewModel>() {

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupObservers() {
        super.setupObservers()
        // Event is used by the view model to tell the activity to launch another activity
        // view model also provided the Bundle in the event that is needed for the Activity


        viewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {


                Handler(Looper.getMainLooper()).postDelayed({
                    /* Create an Intent that will start the Menu-Activity. */
                    startActivity(Intent(this@SplashActivity, RestaurantActivity::class.java))
                    finish()
                }, 1500)
            }
        })
    }
}