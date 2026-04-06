package com.hoeteam.cgd.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

/**
 * 核心图像加载层：用于背景和立绘
 * 解决了 Cannot infer type for type parameter 'R' 的问题
 */
@Composable
fun GameImageLayer(
    model: Any?, // 支持 URL, Drawable ID, File 等
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier.fillMaxSize(),
        contentScale = contentScale
    )
}