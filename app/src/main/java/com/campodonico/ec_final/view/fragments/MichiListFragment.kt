package com.campodonico.ec_final.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campodonico.ec_final.R
import com.campodonico.ec_final.RVMichiListAdapter
import com.campodonico.ec_final.databinding.FragmentMichiListBinding


class MichiListFragment : Fragment() {
    private lateinit var binding: FragmentMichiListBinding
    private lateinit var viewModel: MichiListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MichiListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMichiListBinding.inflate(inflater,container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVMichiListAdapter(listOf()){michi->
            //navegar al detalle
            val michiDetailDirection = MichiListFragmentDirections.actionMichiListFragmentToMichiDetailFragment(michi)
            findNavController().navigate(michiDetailDirection)
        }
        binding.rvMichi.adapter = adapter
        binding.rvMichi.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        viewModel.cuponList.observe(requireActivity()){
            adapter.michis = it
            adapter.notifyDataSetChanged()
        }
        //viewModel.getCuponList()
        viewModel.getCuponsFromService()
    }


}


