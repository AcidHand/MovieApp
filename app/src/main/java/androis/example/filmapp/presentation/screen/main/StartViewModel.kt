package androis.example.filmapp.presentation.screen.main

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androis.example.filmapp.R
import androis.example.filmapp.presentation.adapters.filmAdapterDelegate
import androis.example.filmapp.presentation.adapters.genreAdapterDelegate
import androis.example.filmapp.domain.models.Film
import androis.example.filmapp.domain.models.Genre
import androis.example.filmapp.domain.usecase.IFilmUseCase
import androis.example.filmapp.presentation.utils.DelegationAdapter
import androis.example.filmapp.presentation.utils.FILM_KEY
import kotlinx.coroutines.flow.*

class StartViewModel(val useCase: IFilmUseCase) : ViewModel() {

    private val genreList = MutableStateFlow(emptyList<Genre>())
    private val genreListCash = MutableStateFlow(emptyList<Genre>())
    private val filmsList = MutableStateFlow(listOf(Film.INITIAL))
    private val filmsListCash = MutableStateFlow(listOf(Film.INITIAL))

    var navController: NavController? = null

    init {
        useCase.fetchFilms()

        useCase.responseGenre.onEach {
            genreList.value = it
            genreListCash.value = it
        }.launchIn(viewModelScope)

        useCase.responseFilm.onEach {
            filmsList.value = it
            filmsListCash.value = it
        }.launchIn(viewModelScope)
    }

    fun getNavController(navController: NavController) {
        this.navController = navController
    }

    fun getGenreAdapter() = DelegationAdapter(
        observable = genreList.asStateFlow() to viewModelScope,
        delegates = arrayOf(genreAdapterDelegate(::onGenreClick))
    )

    fun getFilmAdapter() = DelegationAdapter(
        observable = filmsList.asStateFlow() to viewModelScope,
        delegates = arrayOf(filmAdapterDelegate(::onFilmClick))
    )

    private fun onFilmClick(film: Film) {
        val bundle = bundleOf(FILM_KEY to film)
        navController?.navigate(R.id.to_film_fragment, bundle)
    }

    private fun onGenreClick(genre: Genre) {
        genreList.value = genreListCash.value

        genreList.value = genreList.value.map {
            if (it.name == genre.name) {
                Genre(name = genre.name, true)
            } else Genre(name = it.name, false)
        }
        filmsList.value = filmsListCash.value
        val filmsListFiltered = filmsList.value.filter { film ->
            film.genre.contains(genre)
        }
        filmsList.value = filmsListFiltered
    }
}
