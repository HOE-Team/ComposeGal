package com.hoeteam.cgd.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hoeteam.cgd.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

/**
 * 游戏逻辑中枢
 * 负责解析 JSON 剧本并驱动 UI 状态
 */
class GameViewModel(application: Application) : AndroidViewModel(application) {

    // 全局剧本数据
    private var gameScript: GameScript? = null

    // 当前 UI 渲染状态（响应式，UI 会自动监听它）
    var uiState by mutableStateOf(CurrentGameState())
        private set

    // JSON 解析器配置
    private val json = Json {
        ignoreUnknownKeys = true // 允许 JSON 里有暂时没定义的冗余字段
        coerceInputValues = true
    }

    /**
     * 初始化：从 Assets 加载剧本
     */
    fun loadScript(fileName: String) {
        viewModelScope.launch {
            val script = withContext(Dispatchers.IO) {
                try {
                    val content = getApplication<Application>()
                        .assets.open(fileName)
                        .bufferedReader()
                        .use { it.readText() }
                    json.decodeFromString<GameScript>(content)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }

            gameScript = script
            // 加载完成后，跳转到剧本定义的起始节点
            script?.let { jumpToNode(it.startNode) }
        }
    }

    /**
     * 跳转到指定节点
     */
    fun jumpToNode(nodeId: String) {
        val node = gameScript?.nodes?.get(nodeId)
        uiState = uiState.copy(
            scene = node,
            dialogueIndex = 0,
            isEndOfNode = false
        )
    }

    /**
     * 处理玩家的点击：前进到下一句对话
     */
    fun onNext() {
        val currentNode = uiState.scene ?: return

        if (uiState.dialogueIndex < currentNode.dialogues.size - 1) {
            // 还有下一句对话，自增索引
            uiState = uiState.copy(dialogueIndex = uiState.dialogueIndex + 1)
        } else {
            // 对话读完了
            if (currentNode.choices != null && currentNode.choices.isNotEmpty()) {
                // 如果有选项，标记已到结尾，触发 ChoiceGroup 显示
                uiState = uiState.copy(isEndOfNode = true)
            } else if (currentNode.nextNode != null) {
                // 如果没选项但有下一跳，自动跳转
                jumpToNode(currentNode.nextNode)
            }
        }
    }
}