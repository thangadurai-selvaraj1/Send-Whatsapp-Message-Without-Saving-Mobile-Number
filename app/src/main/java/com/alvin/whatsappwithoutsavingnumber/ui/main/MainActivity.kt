package com.alvin.whatsappwithoutsavingnumber.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.alvin.whatsappwithoutsavingnumber.R
import com.alvin.whatsappwithoutsavingnumber.databinding.ActivityMainBinding
import com.alvin.whatsappwithoutsavingnumber.extendtions.registerListeners
import com.alvin.whatsappwithoutsavingnumber.ui.base.BaseActivity
import java.net.URLEncoder

class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initUi()

    }

    private fun initUi() {
        supportActionBar?.hide()
        window?.navigationBarColor = ContextCompat.getColor(this, R.color.navBarColor)

        registerListeners(mainBinding.btnSend)
    }

    override fun onClick(v: View) {
        mainBinding.apply {
            when (v) {
                btnSend -> {
                    when {
                        etMobileNumber.text.isNullOrEmpty() -> {
                            showMessage(getString(R.string.please_enter_mobile_number))
                        }
                        etMobileNumber.text?.length != 10 -> {
                            showMessage(getString(R.string.please_enter_valid_mobile_number))
                        }
                        else -> {
                            val url =
                                "https://api.whatsapp.com/send?phone=+91" + etMobileNumber.text.toString() + "&text=" + URLEncoder.encode(
                                    etMessage.text.toString(),
                                    "UTF-8"
                                )
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                setPackage("com.whatsapp")
                                data = Uri.parse(url)
                            }
                            intent.resolveActivity(packageManager)?.let {
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}