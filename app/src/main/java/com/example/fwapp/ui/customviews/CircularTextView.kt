package com.example.fwapp.ui.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView

class CircularTextView : TextView {

    private var _solidColor: Int = 0
    private var showSolidColor = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //  set solid color
    fun setSolidColor(color: Int) {
        showSolidColor = true
        _solidColor = color
    }

    override fun draw(canvas: Canvas) {
        val circlePaint = Paint()
        circlePaint.color = _solidColor
        circlePaint.flags = Paint.ANTI_ALIAS_FLAG

        val h = this.height
        val w = this.width

        val diameter = if (h > w)
            h
        else
            w
        val radius = diameter / 2f

        this.height = diameter
        this.width = diameter

        if (showSolidColor) {
            canvas.drawCircle(diameter / 2f, diameter / 2f, radius, circlePaint)
        }
        super.draw(canvas)
    }
}