package com.joeyzh.android.walletdemo.ui.wallet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.QuickAdapterHelper
import com.joeyzh.android.walletdemo.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null

//    lateinit var  mHelper: QuickAdapterHelper
    private val binding get() = _binding!!

    private val adapter by lazy {
        CurrencyItemAdapter()
    }
    val mViewModel by lazy {
        ViewModelProvider(this)[WalletViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        registerListener()
        val root: View = binding.root
        registerListener()
        mViewModel.loadData()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun registerListener() {
        mViewModel.mCurrencyViewDataMLV.observe(viewLifecycleOwner) {
            binding.rvWallet.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            binding.rvWallet.adapter = adapter
            adapter.submitList(it)
        }
        mViewModel.mUSDSum.observe(viewLifecycleOwner) {
            Log.v("sum", "sum = " + it)
            binding.header.tvAmount.text = "$ "+it + " USD"
        }
    }
}