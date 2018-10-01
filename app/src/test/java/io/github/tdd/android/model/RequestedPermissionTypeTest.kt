package io.github.tdd.android.model

import android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
import android.Manifest.permission.READ_SMS
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RequestedPermissionTypeTest {

    private var normalPermissions: RequestedPermissionType? = null
    private var dangerousPermissions: RequestedPermissionType? = null

    @Before
    fun setUp() {
        normalPermissions = RequestedPermissionType.NORMAL
        dangerousPermissions = RequestedPermissionType.DANGEROUS
    }

    @Test
    fun testNormalPermissionContainsPermission() {
        val permissions = normalPermissions?.permissions
        assertTrue(permissions!!.contains(ACCESS_LOCATION_EXTRA_COMMANDS))
    }

    @Test
    fun testNormalPermissionDoesNotContainPermission() {
        val permissions = normalPermissions?.permissions
        assertTrue(!permissions!!.contains(READ_SMS))
    }

    @Test
    fun testDangerousPermissionContainsPermission() {
        val permissions = dangerousPermissions?.permissions
        assertTrue(permissions!!.contains(READ_SMS))
    }

    @Test
    fun testDangerousPermissionDoesNotContainPermission() {
        val permissions = dangerousPermissions?.permissions
        assertTrue(!permissions!!.contains(ACCESS_LOCATION_EXTRA_COMMANDS))
    }
}