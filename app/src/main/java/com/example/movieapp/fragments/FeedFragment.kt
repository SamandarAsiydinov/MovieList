package com.example.movieapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.activity.MainActivity
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.adapter.MovieHrAdapter
import com.example.movieapp.databinding.FragmentFeedBinding
import com.example.movieapp.viewmodel.MainViewModel
import kotlin.math.abs

class FeedFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieHrAdapter: MovieHrAdapter
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initViews()
    }

    private fun initViews() {
        movieAdapter = MovieAdapter()
        movieHrAdapter = MovieHrAdapter()
        setupVp()
        setupRv()
        initViewModel()
    }

    private fun setupVp() {
        val compositeTrans = CompositePageTransformer()
       //compositeTrans.addTransformer(MarginPageTransformer(0))
        compositeTrans.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.27f
        }
        binding.viewPager.apply {
            adapter = movieAdapter
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            setPageTransformer(compositeTrans)
        }
    }

    private fun setupRv() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = movieHrAdapter
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.recyclerView)
    }

    private fun initViewModel() {
        viewModel.response.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                movieAdapter.submitList(list)
            } else {
                Log.d("@@@@", "Error")
            }
        }
        viewModel.responsePage.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                movieHrAdapter.submitList(list)
            } else {
                Log.d("@@@@", "Error")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}