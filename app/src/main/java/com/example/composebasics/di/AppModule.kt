package com.example.composebasics.di



import com.example.composebasics.data.MovieApi
import com.example.composebasics.homescreen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin


val app_module = module {
   viewModel { HomeViewModel(get()) }
}
val single_module = module {
    //single { MagicNotesInteractor(MagicNotesDatabase.getDatabase(get())) }
    single { MovieApi() }
}

