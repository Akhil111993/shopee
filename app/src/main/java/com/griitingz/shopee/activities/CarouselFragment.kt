package com.griitingz.shopee.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.griitingz.shopee.R
import com.griitingz.shopee.models.DataX
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


class CarouselFragment(private val items: List<DataX>) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_carousel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
        val transformation: Transformation = RoundedCornersTransformation(15, 0)
        for (item in items) {
            val imageView = ImageView(context)

            Picasso.get().load(item.photo).transform(transformation).into(imageView)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            imageView.layoutParams = layoutParams

            viewFlipper.addView(imageView)
        }
        viewFlipper.flipInterval = 3000
        viewFlipper.startFlipping()

    }
}