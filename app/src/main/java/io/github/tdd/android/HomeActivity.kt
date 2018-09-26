package io.github.tdd.android

import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import io.github.tdd.android.adapter.ApplicationsAdapter
import io.github.tdd.android.adapter.delegate.DangerousAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.ModerateRiskAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.SafeAppsHeaderDelegate
import io.github.tdd.android.adapter.delegate.ScannedAppDelegate
import io.github.tdd.android.presentation.base.BaseActivity
import io.github.tdd.android.presentation.home.HomeContract
import io.github.tdd.android.presentation.home.HomePresenter
import io.github.tdd.android.presentation.model.*
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeContract.View {
    @Inject
    lateinit var presenter: HomePresenter

    @Inject
    lateinit var dangerousAppsHeaderDelegate: DangerousAppsHeaderDelegate

    @Inject
    lateinit var moderateRiskAppsHeaderDelegate: ModerateRiskAppsHeaderDelegate

    @Inject
    lateinit var safeAppsHeaderDelegate: SafeAppsHeaderDelegate

    @Inject
    lateinit var scannedAppDelegate: ScannedAppDelegate

    private var adapter: ApplicationsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        presenter.onCreate(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.scanApplications()
    }

    @LayoutRes
    public
    override fun getLayoutResId(): Int = R.layout.activity_home

    override fun showProgress() {
        placeholder.visibility = View.GONE
        pbProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbProgress.visibility = View.GONE
    }

    override fun showScanResult(applicationsScanResult: ApplicationsScanResult?) {
        val items = arrayListOf<ListItem>()

        val dangerousPackages = applicationsScanResult?.dangerousPackages
        val moderatePackages = applicationsScanResult?.moderatePackages
        val lowRiskPackages = applicationsScanResult?.safePackages
        if (!dangerousPackages!!.isEmpty()) {
            val dangerousAppsHeaderItem = DangerousAppsHeaderItem(R.string.dangerous_apps)
            items.add(dangerousAppsHeaderItem)
            processPackages(dangerousPackages, items)
        }

        if (!applicationsScanResult.moderatePackages.isEmpty()) {
            val moderateAppsHeaderItem = ModerateAppsRiskHeaderItem(R.string.moderate_risk_apps)
            items.add(moderateAppsHeaderItem)
            processPackages(moderatePackages!!, items)
        }

        if (!applicationsScanResult.safePackages.isEmpty()) {
            val headerItem = SafeAppsHeaderItem(R.string.safe_risk_apps)
            items.add(headerItem)
            processPackages(lowRiskPackages!!, items)
        }

        scannedAppDelegate.setOnItemClickListener(presenter)

        adapter = ApplicationsAdapter(items, listOf(dangerousAppsHeaderDelegate, scannedAppDelegate, moderateRiskAppsHeaderDelegate,
                scannedAppDelegate, safeAppsHeaderDelegate, scannedAppDelegate))
        rcAppList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcAppList.adapter = adapter
        rcAppList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter?.notifyDataSetChanged()
    }

    private fun processPackages(packages: List<PackageInfo>, items: ArrayList<ListItem>) {
        packages.forEach {
            val scannedAppItem = ScannedAppItem(getAppLogo(it),
                    getAppLabel(it),
                    it.permissions?.toMutableList())
            items.add(scannedAppItem)
        }
    }

    private fun getAppLabel(packageInfo: PackageInfo): String {
        val applicationInfo = packageManager.getApplicationInfo(packageInfo.packageName, 0)
        return (if (applicationInfo != null) packageManager.getApplicationLabel(applicationInfo) else "(unknown)") as String
    }

    private fun getAppLogo(packageInfo: PackageInfo): Drawable? {
        val applicationInfo = packageManager.getApplicationInfo(packageInfo.packageName, 0)

        if (applicationInfo != null) {
            return packageManager.getApplicationIcon(applicationInfo)
        } else {
            return null
        }
    }
}
