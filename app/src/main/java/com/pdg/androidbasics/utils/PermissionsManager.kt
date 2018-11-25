package com.pdg.androidbasics.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat

class PermissionsManager {

    fun isPermissionGranted(context: Context, permission: String): Boolean {
        return if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            false
        } else true
    }

    fun getPermission() {
        return
    }
}
