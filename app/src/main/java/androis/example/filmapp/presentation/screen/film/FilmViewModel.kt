package androis.example.filmapp.presentation.screen.film

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androis.example.filmapp.R
import androis.example.filmapp.domain.models.Film
import androis.example.filmapp.presentation.utils.provider.ResourceProvider

class FilmViewModel(private val resourceProvider: ResourceProvider) : ViewModel() {
    val nameEng = MutableLiveData("")
    val nameLocal = MutableLiveData("")
    val year = MutableLiveData("")
    val rate = MutableLiveData("")
    val description = MutableLiveData("")

    fun fetchFilmInfo(film: Film) {
        nameEng.value = film.nameEng
        nameLocal.value = film.nameLocal
        year.value = resourceProvider.getString(R.string.year, film.year)
        rate.value = resourceProvider.getString(R.string.rate, film.rate)
        description.value = film.description
    }
}