package io.github.tdd.android.presentation.home

import android.content.pm.PackageInfo
import android.content.pm.PermissionInfo
import io.github.tdd.android.adapter.delegate.base.BaseListItemAdapterDelegate
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
    }

    override fun scanApplications() {
        val applications = provider.getInstalledApplications()
        val dangerousPackages = arrayListOf<PackageInfo>()
        val moderatePackages = arrayListOf<PackageInfo>()
        val safePackages = arrayListOf<PackageInfo>()
        val appsWithPermissions = applications.filter { it -> it.permissions != null }
                .toList()
        val permissionLessPackages = applications.filter { it -> (it.permissions == null
                || it.permissions.isEmpty()) }
        safePackages.addAll(permissionLessPackages)
        for (appsWithPermission in appsWithPermissions) {
            for (permission in appsWithPermission.permissions) {
                if (permission.protectionLevel == PermissionInfo.PROTECTION_DANGEROUS) {
                    dangerousPermissionsCount.incrementAndGet()
                } else if (permission.protectionLevel == PermissionInfo.PROTECTION_NORMAL
                        || permission.protectionLevel == PermissionInfo.PROTECTION_SIGNATURE){
                    safePermissionsCount.incrementAndGet()
                } else {
                    moderatePermissionsCount.incrementAndGet()
                }
            }

            val dangerousPermissions = dangerousPermissionsCount.get()
            val moderatePermissions = moderatePermissionsCount.get()
            if ((dangerousPermissions + moderatePermissions).toDouble() / appsWithPermission.permissions.size > DANGEROUS_APP_THRESHOLD) {
                dangerousPackages.add(appsWithPermission)
            } else if ((dangerousPermissions + moderatePermissions).toDouble() / appsWithPermission.permissions.size > MODERATE_APP_THRESHOLD &&
                    (dangerousPermissions + moderatePermissions).toDouble() / appsWithPermission.permissions.size < DANGEROUS_APP_THRESHOLD ) {
                moderatePackages.add(appsWithPermission)
            } else {
                safePackages.add(appsWithPermission)
            }

            resetCounters()
        }

        view!!.hideProgress()
        view!!.showScanResult(ApplicationsScanResult(dangerousPackages, moderatePackages, safePackages))
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