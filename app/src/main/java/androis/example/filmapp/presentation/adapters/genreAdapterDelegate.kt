package androis.example.filmapp.presentation.adapters

import androis.example.filmapp.R
import androis.example.filmapp.databinding.ItemGenreBinding
import androis.example.filmapp.domain.models.Genre
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun genreAdapterDelegate(itemClickListener: (Genre) -> Unit) =
    adapterDelegateViewBinding<Genre, Genre, ItemGenreBinding>(
        { layoutInflater, parent -> ItemGenreBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.genre.text = item.name

            binding.genre.setBackgroundColor(
                if (item.isSelected) {
                    getColor(R.color.blue)
                } else {
                    getColor(R.color.grey_dark_700)
                }
            )
        }
        binding.genre.setOnClickListener {
            itemClickListener(item)
        }
    }
