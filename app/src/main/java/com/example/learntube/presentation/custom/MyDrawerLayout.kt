package com.example.learntube.presentation.custom

import android.content.Context
import android.util.AttributeSet
import androidx.drawerlayout.widget.DrawerLayout

class MyDrawerLayout(context: Context, attrs: AttributeSet) : DrawerLayout(context, attrs) {

    override fun performClick(): Boolean {
        // Ваша обработка "клика" здесь (если необходимо)
        // Если у вас нет необходимости обрабатывать клик, просто оставьте этот метод пустым
        return super.performClick()
    }
}