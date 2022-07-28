package androis.example.filmapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val name: String,
    var isSelected: Boolean
) : Parcelable {

    companion object {
        val INITIAL = Genre(
            name = "",
            isSelected = false
        )
    }
}