package com.study.android.team_project;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;

public class PermissionUtils {
    // 아래는 주요퍼미션들
    public static final String CALENDAR[] = {"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
    public static final String CAMERA[] = {"android.permission.CAMERA"};
    public static final String CONTACTS[] = {"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS",
            "android.permission.GET_ACCOUNTS"};
    public static final String LOCATION[] = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    public static final String MICROPHONE[] = {"android.permission.RECORD_AUDIO"};
    public static final String PHONE[] = {"android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE",
            "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG",
            "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP",
            "android.permission.PROCESS_OUTGOING_CALLS"};
    public static final String SENSORS[] = {"android.permission.BODY_SENSORS"};
    public static final String SMS[] = {"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS", "android.permission.READ_SMS",
            "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS"};
    public static final String STORAGE[] = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    static PermissionUtils mPermissionUtils;
    private Context mContext;

    public static PermissionUtils get(Context context) {
        if (mPermissionUtils == null) {
            mPermissionUtils = new PermissionUtils(context);
            return mPermissionUtils;
        } else {
            return mPermissionUtils;
        }
    }

    private PermissionUtils(Context context) {
        mContext = context;
    }

    public static void requestPermission(PetSitterDiaryActivity petSitterDiaryActivity, int locationPermissionRequestCode, String accessFineLocation, boolean b) {
    }

    /**
     * 현재 앱에서 사용하는 필수 퍼미션들을 확인하여 사용되는 퍼미션리스트 리턴
     */
    public ArrayList<String> checkUsePermissionAll() {
        ArrayList<String> result = new ArrayList<>();
        PackageManager packageManager = mContext.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;
            if (requestedPermissions != null) {
                for (int i = 0; i < requestedPermissions.length; i++) {
                    try {
                        String permissionName = packageManager.getPermissionInfo(requestedPermissions[i], PackageManager.GET_META_DATA).name;
                        if (isDangerousPermission(permissionName)) {
                            result.add(permissionName);
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @param permission
     * @return 주요 퍼미션여부
     */
    public boolean isDangerousPermission(String permission) {
        for (int i = 0; i < CALENDAR.length; i++) {
            if (CALENDAR[i].equals(permission))
                return true;
        }
        for (int i = 0; i < CAMERA.length; i++) {
            if (CAMERA[i].equals(permission))
                return true;
        }
        for (int i = 0; i < LOCATION.length; i++) {
            if (LOCATION[i].equals(permission))
                return true;
        }
        for (int i = 0; i < MICROPHONE.length; i++) {
            if (MICROPHONE[i].equals(permission))
                return true;
        }
        for (int i = 0; i < PHONE.length; i++) {
            if (PHONE[i].equals(permission))
                return true;
        }
        for (int i = 0; i < SENSORS.length; i++) {
            if (SENSORS[i].equals(permission))
                return true;
        }
        for (int i = 0; i < SMS.length; i++) {
            if (SMS[i].equals(permission))
                return true;
        }
        for (int i = 0; i < STORAGE.length; i++) {
            if (STORAGE[i].equals(permission))
                return true;
        }
        for (int i = 0; i < CONTACTS.length; i++) {
            if (CONTACTS[i].equals(permission))
                return true;
        }
        return false;
    }

    /**
     * @param permissionName
     * @return 앱에서 해당 퍼미션 선언여부 확인
     */
    public boolean isPermissionDeclaration(String permissionName) {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;
            for (int i = 0; i < requestedPermissions.length; i++) {
                if (requestedPermissions[i].equals(permissionName)) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
