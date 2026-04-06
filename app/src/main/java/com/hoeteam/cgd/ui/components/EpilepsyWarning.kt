package com.hoeteam.cgd.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EpilepsyWarning(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 关键改动：使用 Material Icons 的 Warning 图标，颜色设为红色
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "警告",
            tint = Color.Red, // 更改为红色，提高视觉警示等级
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "光敏性癫痫警告",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "本应用包含闪烁图像、快速画面切换等视觉效果，可能诱发光敏性癫痫发作。即使此前没有癫痫史的用户，也可能存在未被察觉的隐患。\n\n如果您在游玩过程中感到头晕、视力变化、眼睛或肌肉抽搐、意识丧失或任何不自主运动，请立即停止游玩并咨询医生。建议在光线充足的环境下游玩，并保持适当距离。",
            color = Color.LightGray,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Center
        )
    }
}