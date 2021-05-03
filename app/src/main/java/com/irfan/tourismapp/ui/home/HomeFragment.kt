package com.irfan.tourismapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.tourismapp.R
import com.irfan.tourismapp.databinding.FragmentHomeBinding
import com.irfan.tourismapp.entity.data.Resource
import com.irfan.tourismapp.entity.di.Injection
import com.irfan.tourismapp.entity.factory.ViewModelFactory
import com.irfan.tourismapp.ui.MainViewModel
import com.irfan.tourismapp.ui.home.adapter.HomeAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mAdapter: HomeAdapter
    private val mainVM by activityViewModels<MainViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory(Injection.provideTourismUseCase(requireContext()))
        homeViewModel =
            ViewModelProvider(this, factory)[HomeViewModel::class.java]
        mAdapter = HomeAdapter { position, tourism ->
            mainVM.setDataFavorites(tourism)
            findNavController().navigate(R.id.action_navigationHome_to_navigationDetail)
        }
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        homeViewModel.tourism.observe(requireActivity(), Observer {
            if (it != null){
                when(it){
                    is Resource.Loading -> {
                        Log.e("datanya", "Loading")
                    }
                    is Resource.Success -> {
                        mAdapter.addDataTourism(it.data!!)
                    }
                    is Resource.Error -> {
                        Log.e("datanya", "Error")
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}