package com.contactsapp

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter

class ContactItemTouchHelperCallback(private val adapter: ModifyCompositeDelegateAdapter) :
    ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.swapData(adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition))
        adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        adapter.notifyItemChanged(viewHolder.adapterPosition)
        adapter.notifyItemChanged(target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }
}