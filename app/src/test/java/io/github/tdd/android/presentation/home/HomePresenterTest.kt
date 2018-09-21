package io.github.tdd.android.presentation.home

import android.os.Build
import io.github.tdd.android.BuildConfig
import io.github.tdd.android.util.provider.InstalledApplicationsProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class HomePresenterTest {

    @Mock
    private lateinit var view: HomeContract.View
    private var presenter: HomePresenter? = null
    private var provider: InstalledApplicationsProvider? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        provider = InstalledApplicationsProvider(RuntimeEnvironment.application)
        presenter = HomePresenter(provider!!)
        presenter!!.onCreate(view)
    }

    @Test
    fun testScanApplications() {
        presenter!!.scanApplications()
    }
}