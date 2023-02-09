package com.azizbek.testproject.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.azizbek.testproject.adapters.OffersRvAdapter
import com.azizbek.testproject.api.NetworkResult
import com.azizbek.testproject.databinding.ActivityMainBinding
import com.azizbek.testproject.hide
import com.azizbek.testproject.models.PhoneOffer
import com.azizbek.testproject.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeOffers()
    }

    private fun observeOffers() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { result ->
                    when (result) {
                        is NetworkResult.Loading -> {
                            with(binding) {
                                progressBar.show()
                                rvPhones.hide()
                                errorTxt.hide()
                            }
                        }
                        is NetworkResult.Success -> {
                            with(binding) {
                                progressBar.hide()
                                errorTxt.hide()
                                rvPhones.show()
                            }
                            setUpRecyclerView(result.data ?: emptyList())
                        }
                        is NetworkResult.Error -> {
                            with(binding) {
                                progressBar.hide()
                                rvPhones.hide()
                                errorTxt.show()
                            }
                            binding.errorTxt.text = result.message
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(phones: List<PhoneOffer>) {
        binding.rvPhones.apply {
            adapter = OffersRvAdapter(phones)
            setHasFixedSize(true)
        }
    }

}