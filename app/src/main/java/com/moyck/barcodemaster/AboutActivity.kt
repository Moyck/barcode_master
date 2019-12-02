package com.moyck.barcodemaster

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.moyck.barcodemaster.base.BaseActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseActivity() {

    val url = "http://www.moyck.com:8080/articles/2019/11/29/1575013596580.html"

    override fun setLayout(): Int {
        return R.layout.activity_about
    }

    override fun initView() {
        webview.loadUrl(url)
        img_close.setOnClickListener(this)
        val settings = webview.settings
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.loadsImagesAutomatically = true
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                webview.loadUrl(url)
                return true
            }
        }
    }

    override fun onClick(v: View?) {
        finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
         if (webview.canGoBack()){
             webview.goBack()
             return true
         }
        return super.onKeyDown(keyCode, event)
    }
}
