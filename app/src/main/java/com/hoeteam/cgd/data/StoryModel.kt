package com.hoeteam.cgd.data

import kotlinx.serialization.Serializable

/**
 * 整个剧本的根对象
 * @param title 游戏标题
 * @param startNode 初始剧情节点的 ID
 * @param nodes 所有剧情节点的集合，Key 为节点 ID
 */
@Serializable
data class GameScript(
    val title: String,
    val startNode: String,
    val nodes: Map<String, StoryNode>
)

/**
 * 单个剧情节点
 * @param background 背景图资源名 (例如: "bg_school_evening")
 * @param music 背景音乐资源名
 * @param characters 当前画面中存在的角色立绘列表
 * @param dialogues 场景内的对话行
 * @param nextNode 自动跳转的下一个节点 ID（如果没有选项）
 * @param choices 分支选项列表（如果存在，则忽略 nextNode）
 */
@Serializable
data class StoryNode(
    val background: String? = null,
    val music: String? = null,
    val characters: List<CharacterState> = emptyList(),
    val dialogues: List<DialogueLine> = emptyList(),
    val nextNode: String? = null,
    val choices: List<GameChoice>? = null
)

/**
 * 角色立绘状态
 * @param name 角色内部标识名
 * @param image 立绘资源名 (例如: "char_senior_happy")
 * @param position 屏幕位置: "left", "center", "right"
 */
@Serializable
data class CharacterState(
    val name: String,
    val image: String,
    val position: String = "center"
)

/**
 * 单行对话
 * @param speaker 说话者的名字（为 null 时表示旁白）
 * @param text 对话文本内容
 */
@Serializable
data class DialogueLine(
    val speaker: String? = null,
    val text: String
)

/**
 * 剧情分支选项
 * @param text 选项显示的文字
 * @param targetNode 点击后跳转的目标节点 ID
 */
@Serializable
data class GameChoice(
    val text: String,
    val targetNode: String
)

/**
 * UI 层使用的实时状态包装类（非持久化）
 * 用于在 Compose 中追踪当前播放进度
 */
data class CurrentGameState(
    val scene: StoryNode? = null,
    val dialogueIndex: Int = 0,
    val isEndOfNode: Boolean = false
)