package com.example.moviefinder.ui.movielist

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
import com.example.moviefinder.databinding.ActivityMovielistBinding
import com.example.moviefinder.ui.adapter.CustomAdapterMovies
import com.example.moviefinder.ui.moviedetail.MovieDetailScrollingActivity
import com.example.moviefinder.util.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MovieListActivity : AppCompatActivity(), KodeinAware {
    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }

    override val kodein by kodein()
    private lateinit var dataBind: ActivityMovielistBinding
    private lateinit var viewModel: MovieListViewModel
    private val factory: MovieListViewModelFactory by instance()
    private lateinit var customAdapterMovies: CustomAdapterMovies

    private var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_movielist)

        val intent = getIntent()

        query = intent.getStringExtra("query").toString()

        setupViewModel()
        setupUI()
        initializeObserver()
        handleNetworkChanges()
        setupAPICall()
    }

    private fun setupUI() {
        customAdapterMovies = CustomAdapterMovies()
        dataBind.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = customAdapterMovies
            addOnItemTouchListener(
                RecyclerItemClickListener(
                    applicationContext,
                    object : RecyclerItemClickListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            if (customAdapterMovies.getData().isNotEmpty()) {
                                val searchItem = customAdapterMovies.getData()[position]
                                searchItem?.let {
                                    val intent =
                                        Intent(
                                            applicationContext,
                                            MovieDetailScrollingActivity::class.java
                                        )
                                    intent.putExtra(AppConstant.INTENT_POSTER, it.poster)
                                    intent.putExtra(AppConstant.INTENT_TITLE, it.title)
                                    startActivity(intent)
                                }
                            }
                        }
                    })
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    val visibleItemCount = layoutManager!!.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    viewModel.checkForLoadMoreItems(
                        visibleItemCount,
                        totalItemCount,
                        firstVisibleItemPosition
                    )
                }
            })
        }

        //getWindow().setStatusBarColor(Color.WHITE);

        // search(searchView);
        viewModel.searchMovie(query)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
    }

    private fun initializeObserver() {
        viewModel.movieNameLiveData.observe(this, Observer {
            Log.i("Info", "Movie Name = $it")
        })
        viewModel.loadMoreListLiveData.observe(this, Observer {
            if (it) {
                customAdapterMovies.setData(null)
                Handler().postDelayed({
                    viewModel.loadMore()
                }, 2000)
            }
        })
    }

    private fun setupAPICall() {
        viewModel.moviesLiveData.observe(this, Observer { state ->
            when (state) {
                is State.Loading -> {
                    Toast.makeText(applicationContext, "loading", Toast.LENGTH_LONG)
                    dataBind.recyclerViewMovies.hide()
                    // dataBind.linearLayoutSearch.hide()
                    dataBind.progressBar.show()
                }
                is State.Success -> {
                    Toast.makeText(applicationContext, "sucess", Toast.LENGTH_LONG)
                    dataBind.recyclerViewMovies.show()
                    //  dataBind.linearLayoutSearch.hide()
                    dataBind.progressBar.hide()
                    customAdapterMovies.setData(state.data)
                }
                is State.Error -> {
                    Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG)
                    dataBind.progressBar.hide()
                    showToast(state.message)
                }
            }
        })
    }

    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this, Observer { isConnected ->
            if (!isConnected) {
                dataBind.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
                dataBind.networkStatusLayout.apply {
                    show()
                    setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                }
            } else {
                if (viewModel.moviesLiveData.value is State.Error || customAdapterMovies.itemCount == 0)
                    viewModel.getMovies()

                dataBind.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                dataBind.networkStatusLayout.apply {
                    setBackgroundColor(getColorRes(R.color.colorStatusConnected))

                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        })
    }

}