package com.vladan.newsapp.screens.pager.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vladan.newsapp.R
import com.vladan.newsapp.databinding.FragmentPagerContainerBinding
import com.vladan.newsapp.screens.pager.details.PagerDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagerContainerFragment : Fragment() {

    private lateinit var binding: FragmentPagerContainerBinding
    private lateinit var labels: Array<String>
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        labels = requireActivity().resources.getStringArray(R.array.labels)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagerContainerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tablayout = binding.tabLayout
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager = binding.pager
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tablayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = labels[0]
                }
                1 -> {
                    tab.text = labels[1]
                }
                2 -> {
                    tab.text = labels[2]
                }
                3 -> {
                    tab.text = labels[3]
                }
                4 -> {
                    tab.text = labels[4]
                }
                5 -> {
                    tab.text = labels[5]
                }
                6 -> {
                    tab.text = labels[6]
                }
                7 -> {
                    tab.text = labels[7]
                }
            }
        }.attach()

    }

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }

    private inner class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val itemsCount = 7

        override fun createFragment(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = PagerDetailFragment.newInstance(labels[0], 0)
                1 -> fragment = PagerDetailFragment.newInstance(labels[1], 1)
                2 -> fragment = PagerDetailFragment.newInstance(labels[2], 2)
                3 -> fragment = PagerDetailFragment.newInstance(labels[3], 3)
                4 -> fragment = PagerDetailFragment.newInstance(labels[4], 4)
                5 -> fragment = PagerDetailFragment.newInstance(labels[5], 5)
                6 -> fragment = PagerDetailFragment.newInstance(labels[6], 6)
                else -> {}
            }
            return fragment!!
        }

        override fun getItemCount(): Int {
            return itemsCount
        }
    }
}