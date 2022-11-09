package com.vladan.newsapp.screens.pager.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivankostadinovic.genericadapter.GenericAdapter
import com.vladan.newsapp.R
import com.vladan.newsapp.databinding.FragmentPageDetailBinding
import com.vladan.newsapp.databinding.RvHeadlineItemBinding
import com.vladan.newsapp.dtomodels.ArticleDto
import com.vladan.newsapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagerDetailFragment : Fragment() {

    companion object {
        const val TAG: String = "PagerDetailFragment"
        private const val CATEGORY: String = "category"
        private const val PAGE: String = "page"

        @JvmStatic
        fun newInstance(
            category: String,
            page: Int
        ): PagerDetailFragment {
            val pagerDetailFragment = PagerDetailFragment()
            val args = Bundle()
            args.apply {
                putString(CATEGORY, category)
                putInt(PAGE, page)
            }
            pagerDetailFragment.arguments = args
            return pagerDetailFragment
        }
    }

    private lateinit var binding: FragmentPageDetailBinding
    private val viewModel: PagerDetailViewModel by viewModels()
    private lateinit var headlinesAdapter: GenericAdapter<ArticleDto, RvHeadlineItemBinding>
    var page: Int = -1
    var category: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            page = requireArguments().getInt(PAGE, -1)
            category = requireArguments().getString(CATEGORY, "")
        }
        Log.d("$TAG $page", "Category: $category")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModel.setCategory(category)
        viewModel.getHeadline()
    }

    private fun setUpRecyclerView() {
        headlinesAdapter = object : GenericAdapter<ArticleDto, RvHeadlineItemBinding>(
            emptyList(),
            R.layout.rv_headline_item
        ) {
            override fun onBindData(
                model: ArticleDto?,
                position: Int,
                dataBinding: RvHeadlineItemBinding?
            ) {

            }

            override fun onItemClick(model: ArticleDto?, position: Int) {
                val bundle = bundleOf("url" to model?.url)
                findNavController().navigate(
                    R.id.action_PagerContainerFragment_to_ArticleFragment,
                    bundle
                )
            }

        }
        val layoutManager = LinearLayoutManager(activity)
        binding.rvHeadlines.layoutManager = layoutManager
        binding.rvHeadlines.adapter = headlinesAdapter
        observeHeadlines(viewModel)
    }

    private fun observeHeadlines(viewModel: PagerDetailViewModel) {
        viewModel.headlines.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val data = it.data?.articles
                    headlinesAdapter.clearItems()
                    headlinesAdapter.setItems(data)
                }
                Status.ERROR -> {
                    Log.d(TAG, it.message.toString())
                }
                Status.LOADING -> {}
            }
        }
    }
}