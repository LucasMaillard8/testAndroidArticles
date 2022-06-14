package com.cubifan.testandroidarticles.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cubifan.testandroidarticles.databinding.FragmentFavoritesBinding
import com.cubifan.testandroidarticles.ui.ArticlesRecyclerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)
        lifecycleScope.launch {
            favoritesViewModel.articles.collect {
                val adapter = ArticlesRecyclerAdapter(it, requireContext(), false) { position ->
                    favoritesViewModel.removeArticle(it[position])
                }
                binding.favoriteArticlesRecycler.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}