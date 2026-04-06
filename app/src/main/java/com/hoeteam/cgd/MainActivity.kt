package com.hoeteam.cgd

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hoeteam.cgd.ui.screens.GalGameScreen
import com.hoeteam.cgd.ui.theme.ComposeGalDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 核心改动：在 onCreate 中强制指定屏幕方向为感应横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        // 开启全屏沉浸式体验 [cite: 4]
        enableEdgeToEdge()

        setContent {
            ComposeGalDemoTheme {
                // 加载游戏主屏幕 [cite: 4]
                GalGameScreen()
            }
        }
    }
}