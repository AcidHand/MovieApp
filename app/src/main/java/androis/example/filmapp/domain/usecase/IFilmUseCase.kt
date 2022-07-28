package androis.example.filmapp.domain.usecase

import androis.example.filmapp.domain.models.Film
import androis.example.filmapp.domain.models.Genre
import kotlinx.coroutines.flow.MutableStateFlow

interface IFilmUseCase {

    val responseGenre: MutableStateFlow<List<Genre>>
    val responseFilm: MutableStateFlow<List<Film>>

    fun fetchFilms()
}