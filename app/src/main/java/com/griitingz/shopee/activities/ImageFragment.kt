package com.griitingz.shopee.activities

import android.R.attr.radius
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.griitingz.shopee.R
import com.griitingz.shopee.models.DataX
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


class ImageFragment(private val items: List<DataX>) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.imageView3)
        val transformation: Transformation = RoundedCornersTransformation(15, 0)
        Picasso.get().load(items[0].photo).transform(transformation).into(imageView)
    }

}