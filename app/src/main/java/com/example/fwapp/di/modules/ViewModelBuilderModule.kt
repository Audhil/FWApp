package com.example.fwapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.fwapp.di.factories.ViewModelKey
import com.example.fwapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}