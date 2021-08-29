package com.swayams.restaurant.assignment.di.component

import com.swayams.restaurant.assignment.di.module.ActivityModule
import com.swayams.restaurant.assignment.ui.main.RestaurantActivity
import com.swayams.restaurant.assignment.ui.splash.SplashActivity
import com.swayams.restaurant.assignment.di.ActivityScope
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)



    fun inject(activity: RestaurantActivity)
}