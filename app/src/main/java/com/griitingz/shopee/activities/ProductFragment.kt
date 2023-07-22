package com.griitingz.shopee.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.griitingz.shopee.R
import com.griitingz.shopee.adapters.CategoriesAdapter
import com.griitingz.shopee.adapters.ProductAdapter
import com.griitingz.shopee.models.DataX


class ProductFragment(private val items: List<DataX>, private val title: String, private val isShop: Boolean) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.product_recyclerview)
        val titleTextView = view.findViewById<TextView>(R.id.product_title)
        titleTextView.text = title

        adapter = ProductAdapter(items, isShop)
        recyclerView.adapter = adapter

    }
}