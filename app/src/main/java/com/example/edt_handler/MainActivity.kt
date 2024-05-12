package com.example.edt_handler

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.edt_handler.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ln

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        onListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onListener() {
        binding.apply {
            edt.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    lifecycleScope.launch {
                        delay(100)
                        val start = edt.selectionStart
                        val end = edt.selectionEnd
                        if (start != end){
                            lnController.visibility = View.VISIBLE
                        } else {
                            lnController.visibility = View.GONE
                        }
                    }
                }
                false
            }

            btnBold.setOnClickListener {
                edt.setTextType(Constant.TYPE_BOLD)
            }

            btnItalic.setOnClickListener{
                edt.setTextType(Constant.TYPE_ITALIC)
            }

            btnUnderline.setOnClickListener {
                edt.setTextType(Constant.TYPE_NORMAL)
            }
        }
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}