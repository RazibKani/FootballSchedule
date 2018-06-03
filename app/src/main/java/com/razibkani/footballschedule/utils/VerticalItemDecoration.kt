package com.razibkani.footballschedule.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class VerticalItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.apply {
            if (parent.getChildAdapterPosition(view) == 0)
                top = verticalSpaceHeight

            left = verticalSpaceHeight
            right = verticalSpaceHeight
            bottom = verticalSpaceHeight
        }
    }
}