package com.taetae98.gachonqr.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("ErrorCode")
    val code: String = ERROR,
    @SerializedName("ErrorMsg")
    val message: String = ERROR_MESSAGE,
    @SerializedName("ds_output")
    val information: Information = Information()
) {
    companion object {
        const val ERROR = "-1"
        const val ERROR_MESSAGE = "Error. Contact developer."
        const val SUCCESS = "0"
        const val NOT_FOUND = "9999"
    }

    val studentId: String
        get() {
            return information.studentId
        }

    data class Information(
        @SerializedName("userId")
        val id: String = "",
        @SerializedName("eml")
        val email: String = "",
        @SerializedName("userNm")
        val name: String = "",
        @SerializedName("telNo")
        val tel: String = "",
        @SerializedName("userUniqNo")
        val studentId: String = ""
    )
}