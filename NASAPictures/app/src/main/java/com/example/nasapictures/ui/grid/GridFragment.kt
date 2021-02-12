package com.example.nasapictures.ui.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nasapictures.R
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
    private val picturesAdapter = PicturesAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = GridFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GridViewModel::class.java)
        getData()
        observeViewModel()
    }

    private fun initViews() {
        with(binding) {
            picturesRv.layoutManager = StaggeredGridLayoutManager(3,
                GridLayoutManager.VERTICAL)
            picturesRv.adapter = picturesAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.pictures.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Success -> {
                    binding.errorMsgTv.isVisible = false
                    picturesAdapter.updateData(it.value)
                }

                is Failure -> {
                    binding.errorMsgTv.isVisible = true
                    it.message?.let { errorMsg ->
                        binding.errorMsgTv.text = errorMsg
                    } ?: run {
                        binding.errorMsgTv.text =
                            requireActivity().resources.getString(R.string.image_fetch_error_msg)
                    }
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