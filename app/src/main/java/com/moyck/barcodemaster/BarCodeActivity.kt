package com.moyck.barcodemaster

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.moyck.barcodemaster.base.BaseActivity
import com.moyck.barcodemaster.utils.FileUtil
import com.moyck.barcodemaster.utils.ZxingUtils
import kotlinx.android.synthetic.main.activity_bar_code.*
import kotlinx.android.synthetic.main.activity_bar_code.btn_save
import kotlinx.android.synthetic.main.activity_bar_code.et_input
import kotlinx.android.synthetic.main.activity_bar_code.img_close
import kotlinx.android.synthetic.main.activity_bar_code.img_qrcode
import java.lang.Exception

class BarCodeActivity : BaseActivity() {

    var bitmap: Bitmap? = null

    override fun setLayout(): Int {
        return R.layout.activity_bar_code
    }

    override fun initView() {
        btn_save.setOnClickListener(this)
        img_close.setOnClickListener(this)
        btn_barcode.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_create -> createBarCode()
            R.id.img_close -> finish()
            R.id.btn_save -> saveBarCode()
            R.id.btn_barcode -> createBarCode()
        }
    }

    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show()
            }
            //申请权限
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                10
            )
            return false
        }
        return true
    }

    fun saveBarCode() {
        if (!checkPermission()) {
            return
        }
        if (bitmap == null){
            Toast.makeText(this, "条形码为空", Toast.LENGTH_LONG).show()
            return
        }
        try {
            FileUtil.save2Album(bitmap)
            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            Toast.makeText(this, "保存到${path.absolutePath}成功", Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
            Toast.makeText(this, "保存失败", Toast.LENGTH_LONG).show()
        }
    }


    fun createBarCode(){
        bitmap = ZxingUtils.creatBarcode(this,et_input.text.toString(), 10, 50,false)
        img_qrcode.setImageBitmap(bitmap)
    }


}
