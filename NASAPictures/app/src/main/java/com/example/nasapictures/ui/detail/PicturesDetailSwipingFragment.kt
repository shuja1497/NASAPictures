package com.example.nasapictures.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nasapictures.R
import com.example.nasapictures.databinding.PictureDetailSwipingFragmentBinding
import com.example.nasapictures.model.Failure
import com.example.nasapictures.model.Picture
import com.example.nasapictures.model.Success
import com.example.nasapictures.ui.main.PictureViewModel
import com.example.nasapictures.utils.DepthPageTransformer


class PicturesDetailSwipingFragment : Fragment() {

    private var url: String? = null
    private lateinit var viewModel: PictureViewModel
    private var _binding: PictureDetailSwipingFragmentBinding? = null
    private val binding get() = _binding!!
    private val picturesDetailAdapter = PicturesDetailAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = PictureDetailSwipingFragmentBinding.inflate(inflater, container, false)

        savedInstanceState?.let {
            url = savedInstanceState.getString(ARG_URL)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PictureViewModel::class.java)

        initViews()
        getData()
        observeViewModel()
    }

    private fun initViews() {
        with(binding) {
            picturesViewPager.adapter = picturesDetailAdapter
            picturesViewPager.setPageTransformer(DepthPageTransformer())
        }
    }

    private fun observeViewModel() {
        viewModel.pictures.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Success -> {
                    binding.errorMsgTv.isVisible = false
                    picturesDetailAdapter.updateData(it.value)

                    url?.let { url ->
                        binding.picturesViewPager.setCurrentItem(
                            picturesDetailAdapter.getItemIndex(
                                url
                            ), false
                        )
                    }
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

    override fun onSaveInstanceState(outState: Bundle) {

        picturesDetailAdapter.getItemByPosition(binding.picturesViewPager.currentItem)?.let {
            outState.putString(
                ARG_URL,
                it.url
            )
        }

        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ARG_URL = "picture_url"
        const val TAG = "PicturesDetailSwipingFragment"

        @JvmStatic
        fun newInstance(picture: Picture): PicturesDetailSwipingFragment {
            val fragment = PicturesDetailSwipingFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_URL, picture.url)
            }
            return fragment
        }

    }
}