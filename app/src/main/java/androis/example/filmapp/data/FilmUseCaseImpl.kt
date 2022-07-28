package androis.example.filmapp.data

import android.content.Context
import androis.example.filmapp.domain.models.Film
import androis.example.filmapp.domain.models.Genre
import androis.example.filmapp.domain.usecase.IFilmUseCase
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONException

class FilmUseCaseImpl(val context: Context) : IFilmUseCase {

    override val responseGenre = MutableStateFlow<List<Genre>>(emptyList())
    override val responseFilm = MutableStateFlow<List<Film>>(emptyList())

    override fun fetchFilms() {
        val filmListResponse = arrayListOf<Film>()
        val genreListResponse = mutableSetOf<Genre>()
        val requestQueue = Volley.newRequestQueue(context)
        val url = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/films.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("films")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val id = jsonObject.getInt("id").toString()
                        val nameLocal = jsonObject.getString("localized_name")
                        val nameEng = jsonObject.getString("name")
                        val year = jsonObject.getInt("year").toString()

                        val rate = if (jsonObject.getDouble("rating").toString()
                                .isNullOrBlank()
                        ) "0.0" else jsonObject.getDouble("rating").toString()
                        val poster = if (jsonObject.getString("image_url")
                                .isNullOrBlank()
                        ) "" else jsonObject.getString("image_url")
                        val description = if (jsonObject.getString("description")
                                .isNullOrBlank()
                        ) "" else jsonObject.getString("description")

                        val genreJsonArray = jsonObject.getJSONArray("genres")
                        val genreArray: ArrayList<Genre> = arrayListOf()
                        for (i in 0 until genreJsonArray.length()) {
                            val genre = Genre(
                                name = genreJsonArray[i].toString(),
                                isSelected = false
                            )
                            genreArray.add(genre)
                        }
                        val film = Film(
                            id = id,
                            nameLocal = nameLocal,
                            nameEng = nameEng,
                            year = year,
                            rate = rate,
                            poster = poster,
                            description = description,
                            genre = genreArray
                        )
                        filmListResponse.add(film)
                        responseFilm.value = filmListResponse

                        for (i in 0 until genreArray.size) {
                            genreListResponse.add(genreArray[i])
                            responseGenre.value = genreListResponse.toList()
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        requestQueue.add(request)
    }

}