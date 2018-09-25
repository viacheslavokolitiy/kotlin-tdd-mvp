package io.github.tdd.android.presentation.model

import android.content.pm.PermissionInfo
import android.graphics.drawable.Drawable

data class ScannedAppItem(var icon: Drawable?, var title: String,
                          var permissions: MutableList<PermissionInfo>?) : ListItem