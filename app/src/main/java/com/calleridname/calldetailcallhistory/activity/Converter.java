package com.calleridname.calldetailcallhistory.activity;

import java.util.Locale;

public class Converter {
    public static String humanReadableByteCountOld(long j, boolean z) {
        int i = z ? 1000 : 1024;
        if (j < ((long) i)) {
            return j + " B";
        }
        double d = (double) j;
        double d2 = (double) i;
        int log = (int) (Math.log(d) / Math.log(d2));
        StringBuilder sb = new StringBuilder();
        sb.append((z ? "kMGTPE" : "KMGTPE").charAt(log - 1));
        sb.append(z ? "" : "i");
        return String.format(Locale.ENGLISH, "%.1f %sB", new Object[]{Double.valueOf(d / Math.pow(d2, (double) log)), sb.toString()});
    }

    public static String megabyteCount(long j) {
        return String.format(Locale.getDefault(), "%.0f", new Object[]{Double.valueOf((((double) j) / 1024.0d) / 1024.0d)});
    }
}