package io.github.tdd.android.presentation.model

import android.content.pm.PackageInfo

data class ApplicationsScanResult(val dangerousPackages: List<PackageInfo>,
                             val moderatePackages: List<PackageInfo>,
                             val safePackages: List<PackageInfo>)
