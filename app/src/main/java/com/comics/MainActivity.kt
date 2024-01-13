package com.comics

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.comics.databinding.ActivityMainBinding
import com.comics.di.ComicModule
import com.comics.presentation.ComicAdapter
import com.comics.presentation.ComicState
import com.comics.presentation.ComicViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivity : AppCompatActivity() {
    private val viewModel: ComicViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(ComicModule.module)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycle.addObserver(viewModel)
        setupObserverViewModel()
        swipeList()
    }

    private fun setupObserverViewModel() {
        viewModel.state.observe(this) {state ->
            when(state) {
                is ComicState.ContentScreen -> {
                    with(binding) {
                        this.errorTV.visibility = View.GONE
                        this.listItem.visibility = View.VISIBLE
                        this.listItem.adapter = ComicAdapter(state.items)
                        this.listItem.layoutManager = LinearLayoutManager(this@MainActivity)
                        this.swipeRefresh.isRefreshing = state.processors.visibility
                    }
                }
                is ComicState.ShowLoading -> {
                    binding.swipeRefresh.isRefreshing = state.visibility
                }
                is ComicState.Error -> {
                    showError(state.processors.visibility)
                }
            }

        }
    }

    private fun swipeList() = with(binding.swipeRefresh) {
        this.setOnRefreshListener {
            refresh()
        }
    }

    private fun refresh(visibility: Boolean = true) {
        with(binding) {
            this.swipeRefresh.isRefreshing = visibility
            lifecycle.coroutineScope.launch {
                viewModel.getAllComics()
            }
        }
    }

    private fun showError(visibility: Boolean) = with(binding) {
        this.listItem.visibility = View.GONE
        this.errorTV.visibility = View.VISIBLE
        this.swipeRefresh.isRefreshing = visibility
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(ComicModule.module)
    }
}