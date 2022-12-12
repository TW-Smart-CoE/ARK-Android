package com.thoughtworks.ark.webview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView

class WebViewTitleBar(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs), View.OnClickListener {
    private var backOnClickListener: OnClickListener? = null
    private var closeOnClickListener: OnClickListener? = null
    private val btnBack: Button
    private val btnClose: Button
    private val tittleView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_header_view, this)
        btnBack = findViewById(R.id.btn_back)
        btnBack.setOnClickListener(this)
        btnClose = findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        tittleView = findViewById(R.id.title)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> backOnClickListener?.onClick(view)
            R.id.btn_close -> closeOnClickListener?.onClick(view)
        }
    }

    fun setBackOnClickListener(clickListener: () -> Unit) {
        backOnClickListener = OnClickListener { clickListener() }
    }

    fun setCloseOnClickListener(closeListener: () -> Unit) {
        closeOnClickListener = OnClickListener { closeListener() }
    }

    fun setTittle(title: String?) {
        tittleView.text = title
    }
}