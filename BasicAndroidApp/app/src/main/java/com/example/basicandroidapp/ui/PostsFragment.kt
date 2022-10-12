package com.example.basicandroidapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.basicandroidapp.data.Posts
import com.example.basicandroidapp.data.PostsRemoteDataSource
import com.example.basicandroidapp.databinding.FragmentPostsBinding
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsFragment : Fragment() {

    private var binding: FragmentPostsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPostsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}


