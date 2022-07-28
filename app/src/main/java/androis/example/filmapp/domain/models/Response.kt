package androis.example.filmapp.domain.models

data class Response(
    val filmArrayList: ArrayList<Film>,
    val genreSet: Set<Genre>
)
