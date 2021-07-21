package com.taetae98.gachonqr.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("qr")
    fun setQR(view: ImageView, qr: String?) {
        if (qr == null || qr.isEmpty()) {
            view.setImageBitmap(null)
            return
        }

        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(qr.toByteArray().toString(Charsets.ISO_8859_1), BarcodeFormat.QR_CODE, 400, 400)

        view.setImageBitmap(bitmap)
    }
}