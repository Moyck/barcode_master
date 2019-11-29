package com.moyck.barcodemaster

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.moyck.barcodemaster.base.BaseActivity

class SplashActivity : BaseActivity() {

    val handler = Handler()

    override fun setLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        handler.postDelayed(object :Runnable{
            override fun run() {
                startActivity( Intent(this@SplashActivity, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

        },1000)
    }


    override fun onClick(v: View?) {

    }


}
