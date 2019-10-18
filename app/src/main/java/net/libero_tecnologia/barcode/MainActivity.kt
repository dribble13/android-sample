package net.libero_tecnologia.barcode

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {

    lateinit var targetTextView: EditText
    lateinit var qrCodeButton: Button
    lateinit var qrCodeView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 画面上の要素を取得
        targetTextView = findViewById(R.id.target_text_input)
        qrCodeButton = findViewById(R.id.qr_code_button)
        qrCodeView = findViewById(R.id.qr_code_image)

        // QRコード作成ボタンがクリックされた時のイベントリスナー
        qrCodeButton.setOnClickListener {
            // 入力されたテキストを取得
            val target = targetTextView.text.toString()

            // QRコード生成
            val barcodeEncoder = BarcodeEncoder()
            val encodeHints = HashMap<EncodeHintType, Any>()
            encodeHints[EncodeHintType.CHARACTER_SET] = "UTF-8"
            val qrCodeBitmap = barcodeEncoder.encodeBitmap(target, BarcodeFormat.QR_CODE, 500, 500, encodeHints)

            // QRコード表示
            qrCodeView.setImageBitmap(qrCodeBitmap)
            qrCodeView.visibility = View.VISIBLE

            // キーボードを隠す
            val manager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(window.decorView.rootView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}
