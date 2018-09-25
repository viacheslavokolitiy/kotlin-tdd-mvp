package io.github.tdd.android.adapter.delegate

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import io.github.tdd.android.R
import io.github.tdd.android.adapter.delegate.base.BaseListItemAdapterDelegate
import io.github.tdd.android.presentation.model.DangerousAppsHeaderItem
import io.github.tdd.android.presentation.model.ListItem

class DangerousAppsHeaderDelegate(context: Context) : BaseListItemAdapterDelegate<DangerousAppsHeaderDelegate.ViewHolder,
        DangerousAppsHeaderItem>(context) {

    public override fun createViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    public override fun bindViewHolder(vh: ViewHolder, item: DangerousAppsHeaderItem){
        vh.bind(item)
    }

    @LayoutRes
    public override fun getLayoutResId(): Int = R.layout.dangerous_apps_header

    public override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int): Boolean
            = item is DangerousAppsHeaderItem

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val header: TextView = itemView.findViewById(R.id.tvDangerousAppsLabel)

        fun bind(item: DangerousAppsHeaderItem) {
            header.setText(item.title)
        }
    }
}