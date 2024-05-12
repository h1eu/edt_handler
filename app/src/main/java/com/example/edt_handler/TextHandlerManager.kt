package com.example.edt_handler

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.EditText

fun EditText.setTextType(type: String){
    val spanString = SpannableStringBuilder(text)
    when(type){
        Constant.TYPE_UNDERLINE -> {
            spanString.setSpan(UnderlineSpan(),selectionStart,selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        Constant.TYPE_BOLD -> {
            spanString.setSpan(StyleSpan(Typeface.BOLD),selectionStart,selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            this.text = spanString
        }
        Constant.TYPE_ITALIC -> {
            spanString.setSpan(StyleSpan(Typeface.ITALIC),selectionStart,selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        else -> {
            spanString.setSpan(null,selectionStart,selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
    this.text = spanString
    clearFocus()
}