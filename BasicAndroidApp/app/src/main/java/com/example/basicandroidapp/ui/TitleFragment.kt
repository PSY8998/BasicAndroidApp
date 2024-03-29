package com.example.basicandroidapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.basicandroidapp.R
import com.example.basicandroidapp.data.Posts
import com.example.basicandroidapp.data.Remote.PostsRemoteDataSource
import com.example.basicandroidapp.data.PostsRepository
import com.example.basicandroidapp.data.modelclass.POST
import com.example.basicandroidapp.databinding.FragmentTitleBinding
import kotlinx.coroutines.launch
import retrofit2.Response


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

        customAdaptor = CustomAdaptor
        binding?.idTitles?.adapter = customAdaptor

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
            { permissions ->
                when{
            }

            }


        viewLifecycleOwner.lifecycleScope.launch {
            getPosts()
        }
    }

    private suspend fun getPosts() {

        val postsApi = PostsRemoteDataSource.postsService


        val call: Response<List<retrofit2.http.POST>> = postsApi.getPosts()
        val posts: List<POST> = (call.body() ?: emptyList()) as List<POST>
        titleAdaptor.submitList(posts)
    }
}