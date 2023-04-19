package com.example.paginationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationexample.API.ApiService
import com.example.paginationexample.API.RetrofitInstance
import com.example.paginationexample.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPessengers.layoutManager = LinearLayoutManager(this)
        val adapter =  PassengerAdapter()
        binding.rvPessengers.adapter = adapter

        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val myDataSouce = PassengersPagingSource(retrofitInstance)
        val myPager = Pager(PagingConfig(pageSize = 20, maxSize = 100)){myDataSouce}
        val  myPagingData = myPager.flow.cachedIn(lifecycleScope)

        GlobalScope.launch {
            myPagingData.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }


    }
}