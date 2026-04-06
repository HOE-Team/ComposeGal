package com.hoeteam.cgd.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 标准对话框
 * @param speaker 说话者名称（null 则不显示名字框）
 * @param text 完整的对话内容
 * @param onNext 点击对话框触发的回调
 */
@Composable
fun DialogueBox(
    speaker: String?,
    text: String,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 打字机动画控制：根据文字长度计算动画时间
    var trigger by remember(text) { mutableStateOf(0) }
    val animatedTextIndex by animateIntAsState(
        targetValue = text.length,
        animationSpec = tween(durationMillis = text.length * 30, easing = LinearEasing),
        label = "Typewriter"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                if (animatedTextIndex < text.length) {
                    // 如果还没跳完，点击直接显示全文 (逻辑可根据需求定制)
                } else {
                    onNext()
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.75f)
        ),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            // 姓名显示区域
            if (!speaker.isNullOrBlank()) {
                Text(
                    text = "【$speaker】",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // 对话正文：截取动画进度对应的字符串
            Text(
                text = text.take(animatedTextIndex),
                color = Color.White,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier.weight(1f)
            )

            // 下一步提示图标
            if (animatedTextIndex == text.length) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Next",
                    tint = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.End)
                )
            }
        }
    }
}