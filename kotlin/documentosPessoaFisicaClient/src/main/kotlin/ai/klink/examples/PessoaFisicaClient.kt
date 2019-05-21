package ai.klink.examples

import ai.klink.examples.dto.FileUploadDto
import ai.klink.examples.dto.LoginRequestDto
import ai.klink.examples.dto.LoginResponseDto
import ai.klink.examples.dto.flow.FlowDto
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mashape.unirest.http.Unirest
import org.apache.commons.io.FileUtils
import java.io.File
import java.util.*


class PessoaFisicaClient(val company: String, val username: String, val password: String) {


    /**
     * Faz o login e retorna o token para autenticação no padrão JWT
     */
    fun login(): String {
        val request = Gson().toJson(LoginRequestDto(company = company, username = username, password = password))
        val response = Unirest.post("https://wise.klink.ai/api/login")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(request)
                .asString()

        if(response.status != 200) {
            throw Exception(response.statusText)
        }

        val loginResponse = Gson().fromJson(response.body, LoginResponseDto::class.java)
        return loginResponse.token

    }


    /**
     * Envia o arquivo para o serviço de upload e retorna o flowId
     */
    fun sendFile(filePath: String): String? {
        val file = File(filePath)
        val encodedFile = Base64.getEncoder().encode(FileUtils.readFileToByteArray(file)).toString(Charsets.UTF_8)
        val token = login()
        val fileUploadDto = FileUploadDto()
        fileUploadDto.apply {
            base64File = encodedFile
            mimeFileType = "image/jpeg"
            fileName = file.name
            documentTypeList = mutableListOf("pessoafisica")
        }
        val request = Gson().toJson(fileUploadDto)
        val response = Unirest.post("https://wise.klink.ai/api/flow/uploadfile")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer ${token}")
                .body(request)
                .asString()

        if(response.status != 200) {
            throw Exception(response.statusText)
        }

        val flowDto = Gson().fromJson(response.body, FlowDto::class.java)

        /**
         * Apenas para mostrar o response
         */
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        println(gsonPretty.toJson(flowDto))

        return flowDto.id

    }


    fun getDetails(flowId: String) {
        val token = login()
        val response = Unirest.get("https://wise.klink.ai/api/flow/${flowId}/details")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer ${token}")
                .asString()

        if(response.status != 200) {
            throw Exception(response.statusText)
        }


        val flowDto = Gson().fromJson(response.body, FlowDto::class.java)

        /**
         * Apenas para mostrar o response
         */
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        println(gsonPretty.toJson(flowDto))
    }

}


fun main() {

    val pessoaFisicaClient = PessoaFisicaClient("", "", "")
    // pessoaFisicaClient.sendFile("/home/fabiomazzo/Desktop/herval/testerg.jpg")
    // pessoaFisicaClient.getDetails("NckW_OxuxH")
    // pessoaFisicaClient.sendFile("/home/fabiomazzo/Desktop/herval/rgjuliana.jpg")
    // pessoaFisicaClient.sendFile("/home/fabiomazzo/Desktop/herval/cnhok.jpg")
    // pessoaFisicaClient.sendFile("/home/fabiomazzo/Desktop/herval/cnhrs.jpg")

}