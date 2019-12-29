package com.example.fwapp.di.modules

import com.example.fwapp.ui.main.MainActivity
import com.example.fwapp.di.factories.ViewModelBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    abstract fun bindBaseActivity(): MainActivity
}