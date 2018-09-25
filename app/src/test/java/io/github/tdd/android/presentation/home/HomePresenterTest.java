package io.github.tdd.android.presentation.home;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PermissionInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.tdd.android.presentation.model.ApplicationsScanResult;
import io.github.tdd.android.util.provider.InstalledApplicationsProvider;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(InstalledApplicationsProvider.class)
public class HomePresenterTest {

    @Mock
    private HomeContract.View mView;
    private InstalledApplicationsProvider mProvider;

    private HomePresenter mPresenter;

    @Before
    public void setUp() {
        mProvider = mock(InstalledApplicationsProvider.class);
        when(mProvider.getInstalledApplications()).thenReturn(Collections.<PackageInfo>emptyList());
        mPresenter = new HomePresenter(mProvider);
        mPresenter.onCreate(mView);
    }

    @Test
    public void testScanIfNoAppsInstalled() {
        mPresenter.scanApplications();
        verify(mView).hideProgress();
        verify(mView).showScanResult(any(ApplicationsScanResult.class));
    }

    @Test
    public void testScanApps() {
        when(mProvider.getInstalledApplications()).thenReturn(mockInstalledApps());
        mPresenter.scanApplications();
        verify(mView).hideProgress();
        verify(mView).showScanResult(any(ApplicationsScanResult.class));
    }

    private List<PackageInfo> mockInstalledApps() {
        final List<PackageInfo> packageInfos = new ArrayList<>();
        final PackageInfo packageInfo = new PackageInfo();
        final ApplicationInfo applicationInfo = new ApplicationInfo();
        final PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.protectionLevel = PermissionInfo.PROTECTION_DANGEROUS;

        final PermissionInfo safe = new PermissionInfo();
        safe.protectionLevel = PermissionInfo.PROTECTION_NORMAL;

        final PermissionInfo moderate = new PermissionInfo();
        moderate.protectionLevel = 65000;

        packageInfo.packageName = "TEST_APP";
        packageInfo.applicationInfo = applicationInfo;
        packageInfo.permissions = new PermissionInfo[]{permissionInfo, safe, moderate};
        packageInfos.add(packageInfo);

        return packageInfos;
    }
}
