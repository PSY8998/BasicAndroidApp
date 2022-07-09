package com.example.moviefinder.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.R
import com.example.moviefinder.databinding.ActivityHomeBinding
import com.example.moviefinder.ui.movielist.MovieListActivity
import com.example.moviefinder.util.dismissKeyboard
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {
    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }

    override val kodein by kodein()
    private lateinit var dataBind: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val factory: HomeViewModelFactory by instance()

    // private lateinit var customAdapterMovies: CustomAdapterMovies
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_home)
        searchView = findViewById(R.id.search_view)
        setupViewModel()
        // setupUI()


        search(searchView);


    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }


    private fun search(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                dismissKeyboard(searchView)
                searchView.clearFocus()
                // viewModel.searchMovie(query)

                new_activity(query);

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    fun new_activity(query: String) {
        val intent = Intent(this, MovieListActivity::class.java)

        intent.putExtra("query", query)
        startActivity(intent)
    }
}