package com.swayams.restaurant.assignment.di.component

import com.swayam.restaurant.assignment.di.module.FragmentModule
import com.swayams.restaurant.assignment.di.FragmentScope
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

}