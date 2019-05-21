package ai.klink.examples.dto.flow

import java.io.Serializable
import java.math.BigDecimal

class FlowVariableDto : Serializable {

    var uuidForFrontend: String? = ""

    var flowId: String? = ""

    var field: String? = ""

    var order: Int? = 0

    var automatic: Boolean? = false

    var description: String? = ""

    var enabled: Boolean? = true

    var dataFormatType: String = ""

    var stringDataValue: String? = ""

    var dateDateValue: String? = null

    var bigDecimalDataValue: BigDecimal? = null

    var booleanDataValue: Boolean? = null

    var fileObjectDownload: Boolean = false

}