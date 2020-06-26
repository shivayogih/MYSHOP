package com.findmore.myshop.ui.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.PackageInfoCompat
import com.findmore.myshop.R


/**
 * Created by Shivayogi Hiremath on 26,June,2020
 *
 *
 */

class SplashActivity : AppCompatActivity() {
    var versionName: String? = null
    var versionCode = 0
    var tvVersionName: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        tvVersionName = findViewById(R.id.tvVersion)
        versionInfo
        Handler().postDelayed({ // This method will be executed once the timer is over
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
            overridePendingTransition(R.anim.right_enter, R.anim.left_out)
        }, 2000)
    }

    private val versionInfo: Unit
        get() {
            val context = applicationContext
            val manager = context.packageManager
            try {
                val info = manager.getPackageInfo(context.packageName, 0)
                versionName = info.versionName
                versionCode = PackageInfoCompat.getLongVersionCode(info).toInt()
                tvVersionName!!.text = "Version: $versionName"
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                versionName = "Unknown-01"
            }
        }
}