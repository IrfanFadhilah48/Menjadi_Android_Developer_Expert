package com.irfan.tourismapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.tourismapp.R
import com.irfan.tourismapp.databinding.FragmentFavoriteBinding
import com.irfan.tourismapp.entity.factory.ViewModelFactory
import com.irfan.tourismapp.ui.home.adapter.HomeAdapter

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapters: HomeAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        favoriteViewModel =
            ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        adapters = HomeAdapter{ position, data ->

        }
        favoriteViewModel.favoriteTourism.observe(viewLifecycleOwner, Observer {
            adapters.addDataTourism(it)
        })

        binding.rvTourism.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapters
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}