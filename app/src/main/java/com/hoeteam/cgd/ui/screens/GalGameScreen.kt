package com.hoeteam.cgd.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoeteam.cgd.R
import com.hoeteam.cgd.ui.components.*
import com.hoeteam.cgd.viewmodel.GameViewModel
import kotlinx.coroutines.delay

@Composable
fun GalGameScreen(
    viewModel: GameViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    val currentScene = uiState.scene

    // 状态机控制：Logo -> 警告 -> 主菜单 -> 游戏
    var showLogo by remember { mutableStateOf(true) }
    var showWarning by remember { mutableStateOf(false) }
    var isInMenu by remember { mutableStateOf(false) }

    // 启动序列逻辑
    LaunchedEffect(Unit) {
        // 1. 展示制作商 Logo
        delay(2000)
        showLogo = false

        // 等待 Logo 淡出动画结束后再开始警告淡入
        delay(1200)

        // 2. 展示光敏性癫痫警告
        showWarning = true
        delay(3500) // 警告停留时间
        showWarning = false

        // 等待警告淡出后再进入主菜单
        delay(1000)

        // 3. 进入主菜单并预加载剧本
        isInMenu = true
        viewModel.loadScript("story.json")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // --- 层级 1：游戏主体/背景层 (底层) ---
        GameStage {
            // 背景切换逻辑：菜单态显示本地 bg.png，游戏态显示剧本配置
            if (isInMenu) {
                GameImageLayer(model = R.drawable.bg)
            } else {
                currentScene?.background?.let { GameImageLayer(model = it) }
            }

            // 仅在非菜单状态（正式游戏中）显示立绘、对话框和分支
            if (!isInMenu) {
                // 立绘层
                currentScene?.characters?.let { characters ->
                    CharacterLayer(characters = characters)
                }

                // 对话框层
                currentScene?.let { scene ->
                    if (uiState.dialogueIndex < scene.dialogues.size) {
                        val line = scene.dialogues[uiState.dialogueIndex]
                        DialogueBox(
                            speaker = line.speaker,
                            text = line.text,
                            onNext = { viewModel.onNext() },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }

                // 分支选项层
                if (uiState.isEndOfNode && currentScene?.choices != null) {
                    ChoiceGroup(
                        choices = currentScene.choices,
                        onChoiceSelected = { targetNode ->
                            viewModel.jumpToNode(targetNode)
                        }
                    )
                }
            }
        }

        // --- 层级 2：主菜单 UI 层 ---
        AnimatedVisibility(
            visible = isInMenu,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(800))
        ) {
            MainMenu(
                onStartGame = { isInMenu = false }, // 点击开始，进入剧情
                onLoadGame = { /* 存档逻辑待实现 */ },
                onSettings = { /* 设置逻辑待实现 */ }
            )
        }

        // --- 层级 3：警告层 (带淡入淡出) ---
        AnimatedVisibility(
            visible = showWarning,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            EpilepsyWarning()
        }

        // --- 层级 4：Logo 层 (最顶层) ---
        AnimatedVisibility(
            visible = showLogo,
            enter = EnterTransition.None,
            exit = fadeOut(animationSpec = tween(1200))
        ) {
            LogoSplash()
        }
    }
}