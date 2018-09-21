package io.github.tdd.android.util.provider

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import javax.inject.Inject

open class InstalledApplicationsProvider @Inject constructor(val context: Application) {

    fun getInstalledApplications(): List<PackageInfo> {
        val packageManager = context.packageManager
        val installedPackages = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS)

        return installedPackages.filter { it -> it.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0}
    }
}