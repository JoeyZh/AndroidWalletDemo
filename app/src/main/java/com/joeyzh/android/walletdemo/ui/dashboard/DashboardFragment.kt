package com.joeyzh.android.walletdemo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joeyzh.android.walletdemo.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    private val adapter by lazy {
        CurrencyItemAdapter()
    }
    val mViewModel by lazy {
        ViewModelProvider(this)[DashboardViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        registerListener();
        val root: View = binding.root
        registerListener();
        mViewModel.loadData()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun registerListener() {
        mViewModel.mCurrencyViewDataMLV.observe(viewLifecycleOwner){
            binding.rvWallet.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//        这里重绘会不停的加边距，所以去掉了
//        binding.numRecycler.addItemDecoration(QuickGridSpaceDecoration(4, 10.dp))
            binding.rvWallet.adapter = adapter
            adapter.submitList(it)
        }
        mViewModel.mUSDSum.observe(viewLifecycleOwner){

        }
    }
}