package com.swayams.restaurant.assignment.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.swayams.restaurant.assignment.di.ApplicationContext
import com.swayams.restaurant.assignment.utils.rx.RxSchedulerProvider
import com.swayams.restaurant.assignment.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: com.swayams.restaurant.assignment.RestaurantApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application


    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences(
            "com-swayam-restaurant-assignments-prefs",
            Context.MODE_PRIVATE
        )

    /**
     * We need to write @Singleton on the provide method if we are create the instance inside this method
     * to make it singleton. Even if we have written @Singleton on the instance's class
     */

}