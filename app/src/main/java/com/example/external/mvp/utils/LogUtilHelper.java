package com.example.external.mvp.utils;

import android.text.TextUtils;
import android.util.Log;

public class LogUtilHelper {

    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || "\n".equals(line) || "\t".equals(line) || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public static void printDivider(String tag) {
        Log.d(tag, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

}
