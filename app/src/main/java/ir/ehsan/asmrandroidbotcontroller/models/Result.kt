package ir.ehsan.asmrandroidbotcontroller.models

import com.google.gson.annotations.SerializedName

data class Result<T>(
    @SerializedName("ok")
    val ok:Boolean,
    @SerializedName("result")
    val result:T
)
