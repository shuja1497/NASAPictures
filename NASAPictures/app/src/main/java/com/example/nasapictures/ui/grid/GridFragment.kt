package com.example.nasapictures.ui.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nasapictures.databinding.GridFragmentBinding
import com.example.nasapictures.model.Failure
import com.example.nasapictures.model.Success

class GridFragment : Fragment() {

    companion object {
        fun newInstance() = GridFragment()
    }

    private lateinit var viewModel: GridViewModel
    private var _binding: GridFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = GridFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GridViewModel::class.java)
        getData()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.pictures.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Success -> {

                }
                is Failure -> {

                }
            }

        })
    }

    private fun getData() {
        viewModel.getAllPictures()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}