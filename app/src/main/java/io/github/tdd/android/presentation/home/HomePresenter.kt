package io.github.tdd.android.presentation.home

import android.content.pm.PackageInfo
import io.github.tdd.android.R
import io.github.tdd.android.adapter.delegate.base.BaseListItemAdapterDelegate
import io.github.tdd.android.model.RequestedPermissionType
import io.github.tdd.android.presentation.base.BasePresenterImpl
import io.github.tdd.android.presentation.model.ApplicationsScanResult
import io.github.tdd.android.presentation.model.ScannedAppItem
import io.github.tdd.android.util.provider.InstalledApplicationsProvider
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class HomePresenter @Inject constructor(val provider: InstalledApplicationsProvider) : BasePresenterImpl<HomeContract.View>(),
        HomeContract.Presenter, BaseListItemAdapterDelegate.OnItemClickListener<ScannedAppItem> {

    private val dangerousPermissionsCount: AtomicInteger = AtomicInteger(0)
    private val safePermissionsCount: AtomicInteger = AtomicInteger(0)
    private val moderatePermissionsCount: AtomicInteger = AtomicInteger(0)

    override fun onCreate(view: HomeContract.View) {
        super.onCreate(view)
        view.showProgress()
        view.showScreenTitle(R.string.installed_applications)
    }

    override fun scanApplications() {
        val appsWithRequestedPermissions = provider.getInstalledApplications()
                .filter { it -> it.requestedPermissions != null }
        scanAppsWithRequestedPermissions(appsWithRequestedPermissions)
    }

    private fun scanAppsWithRequestedPermissions(packages: List<PackageInfo>) {
        val dangerousPackages = arrayListOf<PackageInfo>()
        val moderateRiskPackages = arrayListOf<PackageInfo>()
        val safePackages = arrayListOf<PackageInfo>()
        packages.filter { it -> !it.requestedPermissions.isEmpty() }
                .forEach {
                    val requestedPermissions = it.requestedPermissions.asIterable()
                    requestedPermissions.forEach {
                        if (RequestedPermissionType.DANGEROUS.permissions.contains(it)) {
                            dangerousPermissionsCount.incrementAndGet()
                        } else if (RequestedPermissionType.NORMAL.permissions.contains(it)) {
                            moderatePermissionsCount.incrementAndGet()
                        } else {
                            safePermissionsCount.incrementAndGet()
                        }
                    }

                    val highRiskPermissions = dangerousPermissionsCount.get()
                    val moderateRiskPermissions = moderatePermissionsCount.get()

                    if ((highRiskPermissions + moderateRiskPermissions).toDouble() / it.requestedPermissions.size > DANGEROUS_APP_THRESHOLD) {
                        dangerousPackages.add(it)
                    } else if ((highRiskPermissions + moderateRiskPermissions).toDouble() / it.requestedPermissions.size > MODERATE_APP_THRESHOLD &&
                            (highRiskPermissions + moderateRiskPermissions).toDouble() / it.requestedPermissions.size < DANGEROUS_APP_THRESHOLD ) {
                        moderateRiskPackages.add(it)
                    } else {
                        safePackages.add(it)
                    }

                    resetCounters()
                }
        view!!.hideProgress()
        view!!.showScanResult(ApplicationsScanResult(dangerousPackages, moderateRiskPackages, safePackages))
    }

    override fun onItemClick(item: ScannedAppItem, position: Int) {
        //implement later
    }

    override fun onDelegateItemClicked(item: ScannedAppItem, position: Int) {
        //implement or remove later
    }

    private fun resetCounters() {
        dangerousPermissionsCount.set(0)
        safePermissionsCount.set(0)
        moderatePermissionsCount.set(0)
    }

    companion object {
        private const val DANGEROUS_APP_THRESHOLD = 0.7
        private const val MODERATE_APP_THRESHOLD = 0.5
    }
}