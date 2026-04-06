package com.hoeteam.cgd.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hoeteam.cgd.R

@Composable
fun LogoSplash(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(40.dp) // 两个 Logo 之间的间距
        ) {
            // 第一个 Logo: Hoe Team
            Image(
                painter = painterResource(id = R.drawable.hoe_team),
                contentDescription = "Hoe Team Logo",
                modifier = Modifier.size(120.dp)
            )

            // 第二个 Logo: Stack Lab
            Image(
                painter = painterResource(id = R.drawable.stack_lab),
                contentDescription = "Stack Lab Logo",
                modifier = Modifier.size(120.dp)
            )
        }
    }
}