package com.moyck.barcodemaster

import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.AdapterView
import android.widget.Toast
import com.moyck.barcodemaster.base.BaseScanActivity
import com.moyck.barcodemaster.utils.CopyUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_layout.*

class MainActivity : BaseScanActivity(),AdapterView.OnItemClickListener{



    lateinit var adapter: BarcodeAdapter
    private val datas = ArrayList<String>()

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        img_menu.setOnClickListener(this)
        adapter = BarcodeAdapter(this,datas)
        ls_barcode.adapter = adapter
        ls_barcode.onItemClickListener = this
        lin_barcode.setOnClickListener(this)
        lin_qr_code.setOnClickListener(this)
        lin_about.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.img_menu -> drawerlayout.openDrawer(Gravity.LEFT)
            R.id.lin_barcode -> startActivity(Intent(this,BarCodeActivity::class.java))
            R.id.lin_qr_code -> startActivity(Intent(this,QRCodeActivity::class.java))
            R.id.lin_about -> startActivity(Intent(this,AboutActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        reScan()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        CopyUtil.copy(this,datas[position])
        Toast.makeText(this,"已复制到剪贴板",Toast.LENGTH_LONG).show()
    }

    override fun scanResultCallBack(content: String) {
        datas.add(content)
        adapter.notifyDataSetChanged()
        if (datas.size == 1){
            Toast.makeText(this,"点击内容可复制到剪贴板",Toast.LENGTH_LONG).show()
            re_l.visibility = View.GONE
            ls_barcode.visibility = View.VISIBLE
        }

        ls_barcode.setSelection(adapter.count -1)
        playBeef()
        reScan()
    }

}
