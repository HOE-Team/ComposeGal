package com.hoeteam.cgd.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hoeteam.cgd.data.CharacterState

/**
 * 立绘显示层
 * 负责根据剧情数据渲染当前场上的所有角色
 * @param characters 当前节点定义的角色列表
 */
@Composable
fun CharacterLayer(
    characters: List<CharacterState>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter // 立绘通常底部对齐屏幕
    ) {
        characters.forEach { char ->
            // 为每个角色提供独立的进入/退出动画
            AnimatedVisibility(
                visible = true, // 由外部逻辑控制显示隐藏
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = when (char.position.lowercase()) {
                        "left" -> Alignment.BottomStart
                        "right" -> Alignment.BottomEnd
                        else -> Alignment.BottomCenter
                    }
                ) {
                    AsyncImage(
                        model = char.image, // 传入图片资源名或路径
                        contentDescription = char.name,
                        modifier = Modifier
                            .fillMaxHeight(0.8f) // 立绘通常占据屏幕高度的 80%
                            .padding(horizontal = 32.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}