package com.cubifan.testandroidarticles.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cubifan.testandroidarticles.databinding.FragmentHomeBinding
import com.cubifan.testandroidarticles.ui.ArticlesRecyclerAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            homeViewModel.articles.collect {
                val adapter = ArticlesRecyclerAdapter(it, requireContext(), true) { position ->
                    homeViewModel.addToFavorites(it[position])
                }
                binding.articlesRecycler.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}