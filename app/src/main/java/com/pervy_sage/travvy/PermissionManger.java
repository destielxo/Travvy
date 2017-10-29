package com.pervy_sage.travvy;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by the-limit-breaker on 1/10/17.
 */

public class PermissionManger {
    public static final String TAG="PermissionManager";
    interface OnPermissionResultListener{
        void onGranted(String permission);
        void onDenied(String permission);
    }
    static ArrayList<OnPermissionResultListener> listenerList= new ArrayList<>();
    static void askForPermission(Activity activity,
                                 String[] permissions,
                                 OnPermissionResultListener permissionResultListener ){
        int reqCode=listenerList.size();
        listenerList.add(reqCode,permissionResultListener);
        ActivityCompat.requestPermissions(activity,permissions,reqCode);

    }
    static void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        try{
            OnPermissionResultListener thisPermissionResultListener
                    =listenerList.get(requestCode);
            for(int i=0;i<permissions.length;i++){
                if(grantResults[i]== PackageManager.PERMISSION_GRANTED){
                    thisPermissionResultListener.onGranted(permissions[i]);
                }else{
                    thisPermissionResultListener.onDenied(permissions[i]);
                }
            }
        }catch (IndexOutOfBoundsException e){
            Log.d(TAG, "onRequestPermissionsResult: permission listener not registered");
        }
    }



    }
