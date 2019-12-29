package com.example.fwapp.di.component

import android.content.Context
import com.example.fwapp.AppApplication
import com.example.fwapp.di.modules.APIModule
import com.example.fwapp.di.modules.ActivityBuilderModule
import com.example.fwapp.di.modules.OtherModule
import com.example.fwapp.di.modules.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelBuilderModule::class,
        OtherModule::class,
        APIModule::class
    ]
)
interface AppComponent : AndroidInjector<AppApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}
