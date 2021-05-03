package com.irfan.tourismapp.ui.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.irfan.tourismapp.R
import com.irfan.tourismapp.databinding.FragmentDetailTourismBinding
import com.irfan.tourismapp.entity.data.model.Tourism
import com.irfan.tourismapp.entity.factory.ViewModelFactory
import com.irfan.tourismapp.ui.MainViewModel


class DetailTourismFragment : Fragment() {

    private lateinit var viewModel: DetailTourismViewModel
    private val activityVM by activityViewModels<MainViewModel>()
    private var _binding: FragmentDetailTourismBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTourismBinding.inflate(inflater, container, false)
//        val contextThemeWrapper=
//            ContextThemeWrapper(activity, R.style.Theme_TourismApp_NoActionBar)
//        val localinflater = inflater.cloneInContext(contextThemeWrapper)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(activity as AppCompatActivity){
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[DetailTourismViewModel::class.java]
        val tourism = activityVM.dataFavorite.value!!

        showDetailTourism(tourism)
    }

    private fun showDetailTourism(detailTourism: Tourism) {
        binding.toolbar.title = detailTourism.name
        binding.content.tvDetailDescription.text = detailTourism.description
        setStatusFavorite(detailTourism.isFavorite)
        Glide.with(requireActivity())
            .load(detailTourism.image)
            .into(binding.ivDetailImage)
        binding.fab.setOnClickListener {
            Log.e("dataBefore", detailTourism.isFavorite.toString())
            viewModel.setFavoriteTourism(detailTourism, !detailTourism.isFavorite)
            setStatusFavorite(!detailTourism.isFavorite)
            val data = detailTourism.copy(isFavorite = !detailTourism.isFavorite)
            activityVM.setDataFavorites(data)
            Log.e("dataAfter", data.isFavorite.toString())
        }
    }

    private fun setStatusFavorite(status: Boolean) {
        if (status){
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
        }
        else{
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_not_favorite))
        }
    }



}