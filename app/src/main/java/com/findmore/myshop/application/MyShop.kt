package com.findmore.myshop.application

import android.app.Application
import com.findmore.myshop.utils.FontsOverride

/**
 * Created by Shivayogi Hiremath on 08,June,2020
 *
 */
class MyShop : Application() {

    override fun onCreate() {
        super.onCreate()
        // font from assets: "assets/fonts/Roboto-Regular.ttf
        FontsOverride.overrideFont(applicationContext, "SERIF", "fonts/montserrat_semi_bold.ttf")
    }
}