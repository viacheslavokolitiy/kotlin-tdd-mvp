package io.github.tdd.android.adapter

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import io.github.tdd.android.presentation.model.ListItem

class ApplicationsAdapter() : ListDelegationAdapter<List<ListItem>>() {

    constructor(items: List<ListItem>, delegates: List<AdapterDelegate<List<ListItem>>>) : this() {
        delegates.forEach { delegatesManager.addDelegate(it) }
        setItems(items)
    }
}