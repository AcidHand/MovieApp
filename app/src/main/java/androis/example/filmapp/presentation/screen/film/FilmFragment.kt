package androis.example.filmapp.presentation.screen.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androis.example.filmapp.R
import androis.example.filmapp.databinding.FragmentFilmBinding
import androis.example.filmapp.domain.models.Film
import androis.example.filmapp.presentation.utils.FILM_KEY
import com.squareup.picasso.Picasso
import java.lang.IllegalArgumentException
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmFragment : Fragment() {

    private val viewModel: FilmViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilmBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        arguments?.getParcelable<Film>(FILM_KEY)?.let {
            viewModel.fetchFilmInfo(it)
            try {
                Picasso.get().load(it.poster).into(binding.poster)
            } catch (e: IllegalArgumentException){
                e.printStackTrace()
            } finally {
                binding.poster.setImageResource(R.drawable.default_image)
            }
        }
        binding.toolbarImageBack.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
}
