package com.griitingz.shopee.activities

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.griitingz.shopee.R
import com.griitingz.shopee.adapters.CategoriesAdapter
import com.griitingz.shopee.models.DataX

class CategoriesFragment(private val items: List<DataX>) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.categories_recyclerview)

        adapter = CategoriesAdapter(items)
        recyclerView.adapter = adapter

    }


}