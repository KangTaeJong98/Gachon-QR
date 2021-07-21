package com.taetae98.gachonqr.dto.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("USER_ID")
    val id: String = "",
    @SerializedName("PWD")
    val password: String = "",
) {
    private val fsp_cmd = "login"
    private val DVIC_ID = "dwFraM1pVhl6mMn4npgL2dtZw7pZxw2lo2uqpm1yuMs="
    private val fsp_action = "UserAction"
    private val APPS_ID = "com.sz.Atwee.gachon"
}