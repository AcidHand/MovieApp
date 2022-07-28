package androis.example.filmapp.presentation.di

import android.content.res.Resources
import androis.example.filmapp.presentation.screen.film.FilmViewModel
import androis.example.filmapp.presentation.screen.main.StartViewModel
import androis.example.filmapp.presentation.utils.provider.ResourceProvider
import androis.example.filmapp.presentation.utils.provider.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single<Resources> { androidContext().resources }
    single<ResourceProvider> { ResourceProviderImpl(resources = get()) }

    viewModel { FilmViewModel(resourceProvider = get()) }
    viewModel { StartViewModel(useCase = get()) }
}