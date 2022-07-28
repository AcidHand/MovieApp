package androis.example.filmapp.presentation.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androis.example.filmapp.databinding.FragmentStartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private val viewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getNavController(findNavController())

        return binding.root
    }
}
