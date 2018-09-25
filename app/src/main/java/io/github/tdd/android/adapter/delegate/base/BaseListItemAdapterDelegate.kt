package io.github.tdd.android.adapter.delegate.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import io.github.tdd.android.presentation.model.ListItem

abstract class BaseListItemAdapterDelegate<VH : RecyclerView.ViewHolder, T: ListItem>(val context: Context)
    : AbsListItemAdapterDelegate<T, ListItem, VH>() {

    private var listener: OnItemClickListener<T>? = null
    private var inflater: LayoutInflater? = null

    interface OnItemClickListener<T> {
        fun onItemClick(item: T, position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener<T>) {
        listener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup): VH {
        if (inflater == null) {
            initInflater()
        }
        return createViewHolder(inflater!!.inflate(getLayoutResId(), parent, false))
    }

    override fun onBindViewHolder(item: T, viewHolder: VH, items: MutableList<Any>) {
        if (listener == null) {
            bindViewHolder(viewHolder, item)
        } else {
            bindViewHolder(viewHolder, item, listener!!)
        }
        viewHolder.itemView.setOnClickListener { listener?.onItemClick(item, viewHolder.adapterPosition) }
    }

    protected abstract fun createViewHolder(itemView: View): VH

    protected open fun bindViewHolder(vh: VH, item: T) {
        //no need to implement in parent class
    }

    protected open fun bindViewHolder(vh: VH, item: T, listener: OnItemClickListener<T>) {
        //no need to implement in parent class
    }

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    private fun initInflater(){
        inflater = LayoutInflater.from(context)
    }
}