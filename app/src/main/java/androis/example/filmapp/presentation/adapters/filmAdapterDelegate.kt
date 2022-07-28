package androis.example.filmapp.presentation.adapters

import androis.example.filmapp.R
import androis.example.filmapp.databinding.ItemFilmBinding
import androis.example.filmapp.domain.models.Film
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import java.lang.IllegalArgumentException

fun filmAdapterDelegate(itemClickListener: (Film) -> Unit) = adapterDelegateViewBinding<Film, Film, ItemFilmBinding>(
    { layoutInflater, parent -> ItemFilmBinding.inflate(layoutInflater, parent, false) }
) {

    bind {
        binding.filmName.text = item.nameEng
        try {
            Picasso.get().load(item.poster).into(binding.filmPoster)
        } catch (e:IllegalArgumentException){
            e.printStackTrace()
        } finally {
            binding.filmPoster.setImageResource(R.drawable.default_image)
        }
        binding.filmPoster.setOnClickListener {
            itemClickListener(item)
        }
    }
}






