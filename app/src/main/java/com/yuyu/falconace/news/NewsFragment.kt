package com.yuyu.falconace.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuyu.falconace.FalconApplication
import com.yuyu.falconace.databinding.FragmentNewsBinding
import com.yuyu.falconace.factory.ViewModelFactory
import com.yuyu.falconace.util.Logger

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val adapter: NewsAdapter = NewsAdapter()
    private val viewModel by viewModels<NewsViewModel>() {
        ViewModelFactory((context?.applicationContext as FalconApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerHome.adapter = adapter

        viewModel.newsItemResult.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Logger.i(it)
            }
        }

        return binding.root
    }
}