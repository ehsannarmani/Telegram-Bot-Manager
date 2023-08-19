package ir.ehsan.asmrandroidbotcontroller.models

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("text")
    val text:String
)
