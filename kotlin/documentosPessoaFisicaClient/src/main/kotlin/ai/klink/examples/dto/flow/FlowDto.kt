package ai.klink.examples.dto.flow

import java.io.Serializable

import java.util.*

class FlowDto : Serializable {

    var id: String? = ""

    var createdDate: Date? = null

    var businessDocType: String? = ""

    var description: String? = ""

    var status: String? = ""

    var keyVariableValue: Map<String?, String>? = null

    var qtyTasksDone: Int = 0

    var qtyTasksError: Int = 0

    var qtyTasks: Int = 0

    var extractedText = ""

    var variableList = listOf<FlowVariableDto>()

    var commentList = mutableListOf<CommentDto>()

    var taskList = listOf<TaskDto>()

}