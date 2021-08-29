package com.swayams.restaurant.assignment.ui.splash

import androidx.lifecycle.MutableLiveData
import com.swayams.restaurant.assignment.ui.base.BaseViewModel
import com.swayam.restaurant.assignment.utils.common.Event
import com.swayams.restaurant.assignment.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        // Empty map of key and serialized value is passed to Activity in Event that is needed by the other Activity
            launchMain.postValue(Event(emptyMap()))
    }
}