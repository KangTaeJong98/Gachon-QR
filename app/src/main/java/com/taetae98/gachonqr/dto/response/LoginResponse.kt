package com.taetae98.gachonqr.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("ErrorCode")
    val code: String,
    @SerializedName("ErrorMsg")
    val message: String,
    @SerializedName("userNm")
    val name: String,
    @SerializedName("userUniqNo")
    val studentId: String,
    @SerializedName("telNo")
    val tel: String
) {

    companion object {
        const val SUCCESS = "0"
        const val NOT_FOUND = "9999"
    }
}