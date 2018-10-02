package io.github.tdd.android.presentation.home

import androidx.annotation.StringRes
import io.github.tdd.android.presentation.base.BasePresenter
import io.github.tdd.android.presentation.model.ApplicationsScanResult
import io.github.tdd.android.presentation.model.ScannedAppItem

interface HomeContract {

    interface View {
        /**
         * Showing progress bar while apps being scanned
         */
        fun showProgress()

        /**
         * Hides progress bar
         */
        fun hideProgress()

        /**
         * Shows applications scan result
         */
        fun showScanResult(applicationsScanResult: ApplicationsScanResult)

        /**
         * Shows screen title
         */
        fun showScreenTitle(@StringRes screenTitle: Int)
    }

    interface Presenter : BasePresenter<View>{
        /**
         * Scans applications
         */
        fun scanApplications()

        /**
         * Called when delegate item clicked
         */
        fun onDelegateItemClicked(item: ScannedAppItem, position: Int)
    }
}