package ru.pervukhin.pizzashop.presentation.menu

import android.content.Context
import android.graphics.PointF
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.google.android.gms.tasks.Task
import java.util.*


class LinearLayoutManagerWithSmoothScroller(context: Context): LinearLayoutManager(context) {


    override fun smoothScrollToPosition(
        recyclerView: RecyclerView, state: RecyclerView.State?,
        position: Int
    ) {
        val smoothScroller = TopSnappedSmoothScroller(recyclerView.context)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    private class TopSnappedSmoothScroller(context: Context?) :
        LinearSmoothScroller(context) {

        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }

}