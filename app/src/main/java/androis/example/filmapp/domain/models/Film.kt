package androis.example.filmapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: String,
    val nameLocal: String,
    val nameEng: String,
    val year: String,
    val rate: String,
    val poster: String,
    val description: String,
    val genre: List<Genre>
) : Parcelable {

    companion object {
        val INITIAL = Film(
            id = "",
            nameLocal = "",
            nameEng = "",
            year = "",
            rate = "",
            poster = "",
            description = "",
            genre = emptyList()
        )
    }
}

