package androis.example.filmapp.domain.di

import androis.example.filmapp.data.FilmUseCaseImpl
import androis.example.filmapp.domain.usecase.IFilmUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<IFilmUseCase> { FilmUseCaseImpl(context = get()) }
}