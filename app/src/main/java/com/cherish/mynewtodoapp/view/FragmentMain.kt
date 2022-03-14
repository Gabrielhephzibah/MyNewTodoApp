package com.cherish.mynewtodoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.cherish.mynewtodoapp.data.model.myData.NewsListData
import com.cherish.mynewtodoapp.data.remote.ResultManager
import com.cherish.mynewtodoapp.databinding.FragmentMainBinding
import com.cherish.mynewtodoapp.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentMain: Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
   private val viewModel: AppViewModel by viewModels()
    private lateinit var mAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
       // getNew()
        getAllNew()
    }

    private fun setUpRecyclerView(){
        mAdapter = NewsAdapter()
        binding.recyclerView.adapter = mAdapter
    }


    private fun getNew(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getNewByHeadlines().collect {
                    when(it){
                        is ResultManager.Failure -> {
                            print("FAILED")
                        }
                        is ResultManager.Loading -> {
                            if (it.status){
                               print("loading")
                            }else{
                               print("stop loading")
                            }
                        }
                        is ResultManager.Success -> {
                          val news = it.data.articles?.map { article -> NewsListData(article.publishedAt,article.title, article.content, article.urlToImage) }
                            mAdapter.submitList(news)
                        }
                    }
                }
            }
        }
    }



    private fun getAllNew(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getDataFromDb().collect {
                    when(it){
                        is ResultManager.Failure -> {
                            print("FAILED")
                        }
                        is ResultManager.Loading -> {
                            if (it.status){
                                print("loading")
                            }else{
                                print("stop loading")
                            }
                        }
                        is ResultManager.Success -> {
                            if (it.data.isNotEmpty()){
                              val news = it.data.map { articles -> NewsListData(articles.publishedAt, articles.title, articles.content, articles.urlToImage) }
                              mAdapter.submitList(news)
                            }else{
                                getNew()
                            }

                            print("LIST::::${it.data}")
//                            val news = it.data.?.map { article -> NewsListData(article.publishedAt,article.title, article.content, article.urlToImage) }
//                            mAdapter.submitList(news)
                        }
                    }
                }
            }
        }
    }

}

