# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line findlocation information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line findlocation information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep interface android.support.v7.** { *; }
-keep class android.support.v7.** { *; }

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** w(...);
    public static *** wtf(...);
    public static *** d(...);
    public static *** v(...);
}



-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application

-keep class androidx.appcompat.widget.** { *; }


#### -- Picasso --
 -dontwarn com.squareup.picasso.**

 #### -- OkHttp --
 -dontwarn com.squareup.okhttp.internal.**

 #### -- Apache Commons --
 -dontwarn org.apache.commons.logging.**

-keep class cz.msebera.android.httpclient.** { *; }
-keep class com.loopj.android.http.** { *; }
#startapp rule
### --Facebook
-dontwarn com.facebook.**


# Retrofit
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod

-keepclasseswithmembers class * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}


-dontwarn okio.**
    -keepattributes InnerClasses
    -dontwarn sun.misc.**
    -dontwarn java.lang.invoke.**
    -dontwarn okhttp3.**
    -dontwarn com.anchorfree.sdk.**
    -dontwarn okio.**
    -dontwarn javax.annotation.**
    -dontwarn org.conscrypt.**
    -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
    #DNSJava
    -keep class org.xbill.DNS.** {*;}
    -dontnote org.xbill.DNS.spi.DNSJavaNameServiceDescriptor
    -dontwarn org.xbill.DNS.spi.DNSJavaNameServiceDescriptor
    -keep class * implements com.google.gson.TypeAdapterFactory
    -keep class * implements com.google.gson.JsonSerializer
    -keep class * implements com.google.gson.JsonDeserializer
    -keep class com.anchorfree.sdk.SessionConfig { *; }
    -keep class com.anchorfree.sdk.fireshield.** { *; }
    -keep class com.anchorfree.sdk.dns.** { *; }
    -keep class com.anchorfree.sdk.HydraSDKConfig { *; }
    -keep class com.anchorfree.partner.api.ClientInfo { *; }
    -keep class com.anchorfree.sdk.NotificationConfig { *; }
    -keep class com.anchorfree.sdk.NotificationConfig$Builder { *; }
    -keep class com.anchorfree.sdk.NotificationConfig$StateNotification { *; }
    -keepclassmembers public class com.anchorfree.ucr.transport.DefaultTrackerTransport {
       public <init>(...);
     }
     -keepclassmembers class com.anchorfree.ucr.SharedPrefsStorageProvider{
        public <init>(...);
     }
     -keepclassmembers class com.anchorfree.sdk.InternalReporting$InternalTrackingTransport{
     public <init>(...);
     }
     -keep class com.anchorfree.sdk.exceptions.* {
        *;
     }

    -keepclassmembers class * implements javax.net.ssl.SSLSocketFactory {
        final javax.net.ssl.SSLSocketFactory delegate;
    }

    # https://stackoverflow.com/questions/56142150/fatal-exception-java-lang-nullpointerexception-in-release-build
    -keepclassmembers,allowobfuscation class * {
      @com.google.gson.annotations.SerializedName <fields>;
    }

-dontwarn org.xmlpull.v1.**
-dontnote org.xmlpull.v1.**
-keep class org.xmlpull.** { *; }


-keep class com.android.**
-keep class com.google.android.**
-keep class com.google.android.gms.**
-keep class com.google.android.gms.location.**
-keep class com.google.api.client.**
-keep class com.google.maps.android.**

-keep class com.hsalf.smileyrating.** { *; }

-ignorewarnings