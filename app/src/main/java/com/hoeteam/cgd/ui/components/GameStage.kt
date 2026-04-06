package com.hoeteam.cgd.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * 游戏的顶层舞台容器
 * 采用 Box 布局以实现层级叠加（Z-Index）
 */
@Composable
fun GameStage(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black) // 默认底色，防止资源切换闪烁
    ) {
        content()
    }
}