package com.udacity.sandwichclub.screen

import android.view.View
import com.udacity.sandwichclub.MainActivity
import com.udacity.sandwichclub.R
import org.jetbrains.anko.*

class MainActivityUi : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            listView {
                id = R.id.sandwiches_listview
            }.lparams(width = matchParent)
        }
    }
}