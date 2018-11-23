package com.pdg.androidbasics.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class PermissionsManager {

    public boolean isPermissionGranted(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED) {
            return false;
        } return true;
    }

    public void getPermission(){
        return;
    }
}
