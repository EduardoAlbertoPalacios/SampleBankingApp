package com.example.storidemoapp.ui.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.shared.commonResult.UIState
import com.example.storidemoapp.R
import com.example.storidemoapp.databinding.ActivityHomeBinding
import com.example.storidemoapp.ui.home.adapter.RecyclerViewMovementsAdapter
import com.example.storidemoapp.ui.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: RecyclerViewMovementsAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setUpObservers()
    }

    private fun init() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { finishAffinity() }
        })
        adapter = RecyclerViewMovementsAdapter(this)
        binding.cardDetailBalanceTxt.text = getString(R.string.balance_20_000)
        binding.cardDetailRv.adapter = adapter
        binding.cardDetailImg.setImageResource(R.drawable.user_card)
        viewModel.fetchMovements()
    }

    private fun setUpObservers() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.state.collect {
                when (it) {
                    is UIState.Loading -> {
                        showLoader(true)
                    }

                    is UIState.Success -> {
                        showLoader(false)
                        binding.cardDetailRv.isVisible = true
                        adapter.submitList(it.items)
                    }

                    is UIState.Error -> showLoader(false)
                }
            }
        }
    }

    private fun showLoader(showLoader: Boolean) = binding.apply {
        cardDetailPrgLoader.isVisible = showLoader
    }.also {
        if (showLoader) {
            binding.cardDetailPrgLoader.playAnimation()
        } else {
            binding.cardDetailPrgLoader.cancelAnimation()
        }
    }
}