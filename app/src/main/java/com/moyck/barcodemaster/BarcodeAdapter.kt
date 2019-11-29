package com.moyck.barcodemaster

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.TextView


/**
 * @package:com.moyck.barcodemaster
 * @date on 2019/11/28 10:08
 * @author  Moyuk
 */
class BarcodeAdapter(val context: Context, val datas: List<String>) : BaseAdapter() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView
        if (myConvertView == null) {
            myConvertView = mInflater.inflate(R.layout.barcode_item, null)
        }
        val tv = myConvertView?.findViewById<TextView>(R.id.tv_barcode)
        tv?.text = datas[position]
        return myConvertView!!
    }

    override fun getItem(position: Int): Any {
        return datas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datas.size
    }


}