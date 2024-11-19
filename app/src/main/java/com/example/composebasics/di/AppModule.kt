package com.example.composebasics.di



import com.example.composebasics.data.MovieApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val app_module = module {
   // viewModel { MagicNotesViewModel(get()) }
}
val single_module = module {
    //single { MagicNotesInteractor(MagicNotesDatabase.getDatabase(get())) }
    factory { MovieApi() }

}

