package com.swayams.restaurant.assignment

import android.app.Application
import com.swayams.restaurant.assignment.di.component.DaggerApplicationComponent
import com.swayams.restaurant.assignment.di.module.ApplicationModule

class RestaurantApplication : Application() {

    lateinit var applicationComponent: com.swayams.restaurant.assignment.di.component.ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: com.swayams.restaurant.assignment.di.component.ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}