package io.github.tdd.android.adapter.delegate

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.tdd.android.R
import io.github.tdd.android.adapter.delegate.base.BaseListItemAdapterDelegate
import io.github.tdd.android.presentation.model.ListItem
import io.github.tdd.android.presentation.model.ScannedAppItem

class ScannedAppDelegate(context: Context) : BaseListItemAdapterDelegate<ScannedAppDelegate.ViewHolder,
        ScannedAppItem>(context) {

    public override fun createViewHolder(itemView: View): ScannedAppDelegate.ViewHolder = ViewHolder(itemView)

    public override fun bindViewHolder(vh: ViewHolder, item: ScannedAppItem, listener: OnItemClickListener<ScannedAppItem>) {
        vh.bind(item, listener)
    }

    public override fun getLayoutResId(): Int = R.layout.scanned_app_item

    public override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int): Boolean
            = item is ScannedAppItem

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val appIcon = itemView.findViewById<ImageView>(R.id.ivAppLogo)
        val appLabel = itemView.findViewById<TextView>(R.id.tvAppLabel)

        fun bind(item: ScannedAppItem, listener: OnItemClickListener<ScannedAppItem>) {
            appIcon.setImageDrawable(item.icon)
            appLabel.text = item.title
            itemView.setOnClickListener { listener.onItemClick(item, adapterPosition)}
        }
    }
}