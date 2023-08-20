package com.campodonico.ec_final.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.campodonico.ec_final.R
import com.campodonico.ec_final.databinding.FragmentMichiDetailBinding
import com.campodonico.ec_final.model.Michi


class MichiDetailFragment : Fragment() {
    private lateinit var binding: FragmentMichiDetailBinding
    val args: MichiDetailFragmentArgs by navArgs()
    private lateinit var michi: Michi
    private lateinit var viewModel: MichiDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        michi= args.michi
        viewModel= ViewModelProvider(requireActivity()).get(MichiDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentMichiDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.root.context)
            .load(michi.image) // Carga la imagen desde la URL proporcionada por la API
            .into(binding.imgMichi)
        binding.txtNameStore.text = michi.name
        binding.txtDetail.text = michi.gameSeries
        binding.txtExpiredOn.text = michi.type
        binding.txtMario.text = michi.amiiboSeries
        binding.btnAddFavorite.apply {
            text = if (viewModel.isFavorite(michi)) "Eliminar" else "Agregar a favoritos"
        }

        binding.btnAddFavorite.setOnClickListener {
            if (!viewModel.isFavorite(michi)) {
                michi.isFavorite = true
                viewModel.addFavorite(michi)
                binding.btnAddFavorite.text = "Eliminar"
            } else {
                michi.isFavorite = false
                viewModel.removeFavorite(michi)
                binding.btnAddFavorite.text = "Agregar a favoritos"
            }
        }
    }


}