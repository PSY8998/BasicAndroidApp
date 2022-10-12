package com.example.basicandroidapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.basicandroidapp.R
import com.example.basicandroidapp.data.Posts
import com.example.basicandroidapp.data.PostsApi
import com.example.basicandroidapp.data.PostsRemoteDataSource
import com.example.basicandroidapp.data.PostsRepository
import com.example.basicandroidapp.databinding.FragmentTitleBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TitleFragment : Fragment() {

    lateinit var titleAdaptor: TitleAdaptor

    private var binding: FragmentTitleBinding? = null

    private lateinit var repository: PostsRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentTitleBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleAdaptor = TitleAdaptor(
            onPostClick = { postId -> PostsFragment()

            }
        )
        binding?.idTitles?.adapter = titleAdaptor

        viewLifecycleOwner.lifecycleScope.launch {
            getPosts()
        }
    }

    private suspend fun getPosts() {

        val postsApi = PostsRemoteDataSource.postsService


        val call: Response<List<Posts>> =  postsApi.getPosts()
        val posts: List<Posts> = call.body() ?: emptyList()
        titleAdaptor.submitList(posts)
    }
}