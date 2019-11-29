package com.moyck.barcodemaster.base

import android.app.Activity
import android.os.Bundle
import android.view.View

/**
 * @package:com.moyck.barcodemaster.base
 * @date on 2019/2/21 11:57
 * @author  Moyuk
 */
abstract class BaseActivity : Activity(),View.OnClickListener,IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())  //布局
        initView() //界面显示相关，点击事件
        initSetting()
    }

    open fun initSetting(){

    }

}