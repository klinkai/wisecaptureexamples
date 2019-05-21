package ai.klink.examples.dto

class FileUploadDto {

    var base64File: String? = null

    var mimeFileType: String? = null

    var fileName: String? = null

    var documentTypeList = mutableListOf<String>()

}