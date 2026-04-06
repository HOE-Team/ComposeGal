package com.hoeteam.cgd.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoeteam.cgd.data.GameChoice

/**
 * 剧情分支选项组
 * @param choices 从 JSON 解析出的选项列表
 * @param onChoiceSelected 当用户点击某个选项时，通知 ViewModel 跳转到对应的 targetNode
 */
@Composable
fun ChoiceGroup(
    choices: List<GameChoice>,
    onChoiceSelected: (targetNode: String) -> Unit,
    modifier: Modifier = Modifier
) {
    // 选项通常显示在屏幕中心，引导玩家思考
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f) // 占据屏幕宽度的 80%
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp), // 选项之间的间距
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            choices.forEach { choice ->
                Button(
                    onClick = { onChoiceSelected(choice.targetNode) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black.copy(alpha = 0.8f),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.4f)
                    )
                ) {
                    Text(
                        text = choice.text,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}