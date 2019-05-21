package ai.klink.examples.dto.flow

import java.time.LocalDateTime

class TaskDto {

    var id: String? = ""

    var flowId: String? = null

    var taskOrder: Int? = null

    var taskType: String? = null

    var name: String? = null

    var description: String? = null

    var formDefinition: String? = null

    var formSuccessMessage: String? = null

    var keyVariableValue: Map<String?, String>? = null

    var status: String? = null

    var retriesNumber: Int? = null

    var automatic: Boolean? = null

    var mandatory: Boolean? = null

    var delaySeconds: Int? = null

    //var lastStatusUpdate: LocalDateTime? = null

    var commentList: MutableList<CommentDto>? = null

}