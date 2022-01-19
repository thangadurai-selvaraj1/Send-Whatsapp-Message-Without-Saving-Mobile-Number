package com.alvin.whatsappwithoutsavingnumber.extendtions

import android.view.View

fun View.OnClickListener.registerListeners(vararg views: View) {
    views.forEach {
        it.setOnClickListener(this)
    }
}