package com.calleridname.calldetailcallhistory.activity;

/* renamed from: e.h.a.a.i.b */
public class C6179b {

    /* renamed from: a */
    public String f27675a;

    /* renamed from: b */
    public String f27676b;

    /* renamed from: a */
    public C6179b mo16773a(String str) {
        if (str.length() <= 10) {
            return null;
        }
        C6179b bVar = new C6179b();
        boolean z = false;
        String[] split = str.substring(0, str.indexOf(",1")).split(",");
        if (split == null || split.length <= 0) {
            bVar.f27676b = "Unknown";
        } else {
            bVar.f27676b = split[0];
        }
        boolean z2 = split != null;
        if (split.length > 1) {
            z = true;
        }
        if (!z || !z2) {
            bVar.f27675a = "Unknown";
            return bVar;
        }
        bVar.f27675a = split[1];
        return bVar;
    }
}
