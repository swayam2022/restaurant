package com.swayams.restaurant.assignment.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.swayams.restaurant.assignment.ui.base.BaseActivity
import com.swayams.restaurant.assignment.ui.main.RestaurantViewModel
import com.swayams.restaurant.assignment.ui.splash.SplashViewModel
import com.swayams.restaurant.assignment.di.ActivityContext
import com.swayams.restaurant.assignment.utils.ViewModelProviderFactory
import com.swayams.restaurant.assignment.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(schedulerProvider, compositeDisposable)
            //this lambda creates and return SplashViewModel
        }).get(SplashViewModel::class.java)





    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): RestaurantViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(RestaurantViewModel::class) {
            RestaurantViewModel(schedulerProvider, compositeDisposable,ArrayList())
        }).get(RestaurantViewModel::class.java)


}