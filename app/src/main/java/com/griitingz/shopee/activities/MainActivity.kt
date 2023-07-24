package com.griitingz.shopee.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import com.griitingz.shopee.R
import com.griitingz.shopee.databinding.ActivityMainBinding
import com.griitingz.shopee.models.HomeModel
import com.griitingz.shopee.network.ApiInterface
import com.griitingz.shopee.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }

    private fun getData() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading")
        progressDialog.show()

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.apiInterface.getData()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        println("response successful")
                        val containerLayout = binding.listview
                        if (response.body() != null && response.body()!!.status == 206) {
                            for ((i, item) in response.body()!!.data.withIndex()) {
                                println(i)
                                when (item.session_name) {
                                    "category" -> {
                                        val fragmentContainerView = FragmentContainerView(baseContext)
                                        fragmentContainerView.id = View.generateViewId()
                                        containerLayout.addView(fragmentContainerView)
                                        supportFragmentManager.beginTransaction()
                                            .add(fragmentContainerView.id, CategoriesFragment(response.body()!!.data[i].data) )
                                            .commit()
                                    }
                                    "slider" -> {
                                        val fragmentContainerView = FragmentContainerView(baseContext)
                                        fragmentContainerView.id = View.generateViewId()
                                        containerLayout.addView(fragmentContainerView)
                                        supportFragmentManager.beginTransaction()
                                            .add(fragmentContainerView.id, CarouselFragment(response.body()!!.data[i].data) )
                                            .commit()
                                    }
                                    "product" -> {
                                        val fragmentContainerView = FragmentContainerView(baseContext)
                                        fragmentContainerView.id = View.generateViewId()
                                        containerLayout.addView(fragmentContainerView)
                                        supportFragmentManager.beginTransaction()
                                            .add(fragmentContainerView.id, ProductFragment(response.body()!!.data[i].data, response.body()!!.data[i].title, false))
                                            .commit()
                                    }
                                    "banner" -> {
                                        val fragmentContainerView = FragmentContainerView(baseContext)
                                        fragmentContainerView.id = View.generateViewId()
                                        containerLayout.addView(fragmentContainerView)
                                        supportFragmentManager.beginTransaction()
                                            .add(fragmentContainerView.id, ImageFragment(response.body()!!.data[i].data) )
                                            .commit()
                                    }
                                    else -> {
                                        val fragmentContainerView = FragmentContainerView(baseContext)
                                        fragmentContainerView.id = View.generateViewId()
                                        containerLayout.addView(fragmentContainerView)
                                        supportFragmentManager.beginTransaction()
                                            .add(fragmentContainerView.id, ProductFragment(response.body()!!.data[i].data, response.body()!!.data[i].title, true))
                                            .commit()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(this@MainActivity,response.body()?.message, Toast.LENGTH_LONG).show()
                        }
                        progressDialog.dismiss()
                    } else {
                        Toast.makeText(this@MainActivity,response?.errorBody().toString(), Toast.LENGTH_LONG).show()
                        progressDialog.dismiss()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity,"No internet", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                }

            }



        }
//        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<HomeModel?> {
//            override fun onResponse(call: Call<HomeModel?>, response: Response<HomeModel?>) {
//
//                val containerLayout = binding.listview
//
//
//                if (response.body() != null && response.body()!!.status == 206) {
//
//                    for ((i, item) in response.body()!!.data.withIndex()) {
//                        println(i)
//                        when (item.session_name) {
//                            "category" -> {
//                                val fragmentContainerView = FragmentContainerView(baseContext)
//                                fragmentContainerView.id = View.generateViewId()
//                                containerLayout.addView(fragmentContainerView)
//                                supportFragmentManager.beginTransaction()
//                                    .add(fragmentContainerView.id, CategoriesFragment(response.body()!!.data[i].data) )
//                                    .commit()
//                            }
//                            "slider" -> {
//                                val fragmentContainerView = FragmentContainerView(baseContext)
//                                fragmentContainerView.id = View.generateViewId()
//                                containerLayout.addView(fragmentContainerView)
//                                supportFragmentManager.beginTransaction()
//                                    .add(fragmentContainerView.id, CarouselFragment(response.body()!!.data[i].data) )
//                                    .commit()
//                            }
//                            "product" -> {
//                                val fragmentContainerView = FragmentContainerView(baseContext)
//                                fragmentContainerView.id = View.generateViewId()
//                                containerLayout.addView(fragmentContainerView)
//                                supportFragmentManager.beginTransaction()
//                                    .add(fragmentContainerView.id, ProductFragment(response.body()!!.data[i].data, response.body()!!.data[i].title, false))
//                                    .commit()
//                            }
//                            "banner" -> {
//                                val fragmentContainerView = FragmentContainerView(baseContext)
//                                fragmentContainerView.id = View.generateViewId()
//                                containerLayout.addView(fragmentContainerView)
//                                supportFragmentManager.beginTransaction()
//                                    .add(fragmentContainerView.id, ImageFragment(response.body()!!.data[i].data) )
//                                    .commit()
//                            }
//
//                            else -> {
//                                val fragmentContainerView = FragmentContainerView(baseContext)
//                                fragmentContainerView.id = View.generateViewId()
//                                containerLayout.addView(fragmentContainerView)
//                                supportFragmentManager.beginTransaction()
//                                    .add(fragmentContainerView.id, ProductFragment(response.body()!!.data[i].data, response.body()!!.data[i].title, true))
//                                    .commit()
//                            }
//
//                        }
//                    }
//
//
//
//                } else {
//                    Toast.makeText(this@MainActivity,response.body()?.message, Toast.LENGTH_LONG).show()
//                }
//                progressDialog.dismiss()
//            }
//
//            override fun onFailure(call: Call<HomeModel?>, t: Throwable) {
//                Toast.makeText(this@MainActivity,t.localizedMessage, Toast.LENGTH_LONG).show()
//                progressDialog.dismiss()
//            }
//
//        })
    }


}