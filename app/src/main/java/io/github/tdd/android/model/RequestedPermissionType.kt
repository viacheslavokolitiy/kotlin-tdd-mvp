package io.github.tdd.android.model

import android.Manifest
import kotlin.reflect.KProperty

enum class RequestedPermissionType(val permissions: List<String>) {
    DANGEROUS(listOf<String>(Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_CALENDAR,
            Manifest.permission.READ_CALL_LOG, Manifest.permission.ACCEPT_HANDOVER, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ADD_VOICEMAIL, Manifest.permission.ANSWER_PHONE_CALLS,
            Manifest.permission.BODY_SENSORS, Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA,
            Manifest.permission.GET_ACCOUNTS, Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_MMS, Manifest.permission.RECEIVE_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH, Manifest.permission.RECORD_AUDIO, Manifest.permission.USE_SIP,
            Manifest.permission.WRITE_CALENDAR, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.WRITE_CONTACTS)),
    NORMAL(listOf<String>(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET, Manifest.permission.ACCESS_NOTIFICATION_POLICY, Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BROADCAST_STICKY, Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_MULTICAST_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.DISABLE_KEYGUARD,
            Manifest.permission.EXPAND_STATUS_BAR, Manifest.permission.FOREGROUND_SERVICE, Manifest.permission.GET_PACKAGE_SIZE,
            Manifest.permission.INSTALL_SHORTCUT, Manifest.permission.KILL_BACKGROUND_PROCESSES, Manifest.permission.MANAGE_OWN_CALLS,
            Manifest.permission.MODIFY_AUDIO_SETTINGS, Manifest.permission.NFC, Manifest.permission.NFC_TRANSACTION_EVENT,
            Manifest.permission.READ_SYNC_SETTINGS, Manifest.permission.READ_SYNC_STATS, Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.REORDER_TASKS, Manifest.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND, Manifest.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND,
            Manifest.permission.REQUEST_DELETE_PACKAGES, Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Manifest.permission.SET_ALARM, Manifest.permission.SET_WALLPAPER, Manifest.permission.SET_WALLPAPER_HINTS,
            Manifest.permission.TRANSMIT_IR, Manifest.permission.USE_BIOMETRIC, Manifest.permission.USE_FINGERPRINT,
            Manifest.permission.VIBRATE, Manifest.permission.WAKE_LOCK, Manifest.permission.WRITE_SYNC_SETTINGS));
}