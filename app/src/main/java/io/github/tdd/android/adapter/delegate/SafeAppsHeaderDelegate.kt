package io.github.tdd.android.adapter.delegate

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import io.github.tdd.android.R
import io.github.tdd.android.adapter.delegate.base.BaseListItemAdapterDelegate
import io.github.tdd.android.presentation.model.ListItem
import io.github.tdd.android.presentation.model.SafeAppsHeaderItem

open class SafeAppsHeaderDelegate(context: Context) : BaseListItemAdapterDelegate<SafeAppsHeaderDelegate.ViewHolder,
        SafeAppsHeaderItem>(context) {

    public override fun createViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    public override fun bindViewHolder(vh: ViewHolder, item: SafeAppsHeaderItem){
        vh.bind(item)
    }

    @LayoutRes
    public override fun getLayoutResId(): Int = R.layout.safe_apps_header

    public override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int): Boolean
            = item is SafeAppsHeaderItem

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val header: TextView = itemView.findViewById(R.id.tvSafeAppsRiskLabel)

        fun bind(item: SafeAppsHeaderItem) {
            header.setText(item.title)
        }
    }
}