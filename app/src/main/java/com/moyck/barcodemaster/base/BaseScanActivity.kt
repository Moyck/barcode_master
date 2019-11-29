package com.moyck.barcodemaster.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.moyck.barcodemaster.R

/**
 * @package:com.moyck.barcodemaster.base
 * @date on 2019/2/28 18:32
 * @author  Moyuk
 */
abstract class BaseScanActivity : BaseActivity() {

    private var captureManager: CaptureManager? = null
    var decoratedBarcodeView: DecoratedBarcodeView? = null

    override fun initSetting() {
        decoratedBarcodeView = findViewById(R.id.zxing_barcode_scanner)
        initDecode()
    }

    fun initDecode() {
        captureManager = CaptureManager(this, decoratedBarcodeView)
        captureManager!!.initializeFromIntent(getIntent(), null)
    }

    override fun onResume() {
        super.onResume()
        captureManager!!.onResume()
        captureManager!!.setResultCallBack { requestCode: Int, resultCode: Int, intent: Intent ->
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent)
            scanResultCallBack(result.contents)
        }
        captureManager!!.decode()
        decoratedBarcodeView?.refresh()
    }

    fun reScan(){
        captureManager!!.onResume()
        captureManager!!.decode()
    }

    fun playWrongBeef() {
        captureManager?.playWrongBeef()
    }

    fun playBeef(){
        captureManager?.playBeef()
    }

    abstract fun scanResultCallBack(content: String)

}