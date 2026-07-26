# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Keep data models so Gson reflection works
-keep class uk.usedcars.marketplace.dealers.auto.finance.domain.model.** { *; }

# Keep retrofit interfaces
-keep class uk.usedcars.marketplace.dealers.auto.finance.data.api.** { *; }

# Keep annotations and signatures for Gson
-keepattributes Signature
-keepattributes *Annotation*

# Keep AdMob specific
-keep class com.google.android.gms.ads.** { *; }
