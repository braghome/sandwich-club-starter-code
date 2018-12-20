package com.udacity.sandwichclub.screen

import android.widget.FrameLayout
import android.widget.ImageView.ScaleType.CENTER_CROP
import android.widget.RelativeLayout
import android.widget.ScrollView
import com.squareup.picasso.Picasso.with
import com.udacity.sandwichclub.DetailActivity
import com.udacity.sandwichclub.R
import com.udacity.sandwichclub.R.style.DetailLabels
import com.udacity.sandwichclub.R.style.DetailLItems
import org.jetbrains.anko.*

class DetailActivityUi : AnkoComponent<DetailActivity> {
    override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
        scrollView {
            layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent)
            relativeLayout {
                imageView {
                    id = R.id.image_iv
                    scaleType = CENTER_CROP
                    contentDescription = ctx.getString(R.string.sandwich_picture_content_description)
                    adjustViewBounds = true
                }.lparams(width = matchParent, height = dip(200))
                themedTextView(ctx.getString(R.string.detail_place_of_origin_label), DetailLabels) {
                    id = R.id.origin_label
                }.lparams { below(R.id.image_iv) }
                themedTextView(DetailLItems) {
                    id = R.id.origin_tv
                }.lparams { below(R.id.origin_label) }
                themedTextView(ctx.getString(R.string.detail_also_known_as_label), DetailLabels) {
                    id = R.id.also_known_label
                }.lparams { below(R.id.origin_tv) }
                themedTextView(DetailLItems) {
                    id = R.id.also_known_tv
                }.lparams { below(R.id.also_known_label) }
                themedTextView(ctx.getString(R.string.detail_ingredients_label), DetailLabels) {
                    id = R.id.ingredients_label
                }.lparams { below(R.id.also_known_tv) }
                themedTextView(DetailLItems) {
                    id = R.id.ingredients_tv
                }.lparams { below(R.id.ingredients_label) }
                themedTextView(ctx.getString(R.string.detail_description_label), DetailLabels) {
                    id = R.id.description_label
                }.lparams { below(R.id.ingredients_tv) }
                themedTextView(DetailLItems) {
                    id = R.id.description_tv
                }.lparams { below(R.id.description_label) }
            }.lparams(width = matchParent)
        }
    }
}