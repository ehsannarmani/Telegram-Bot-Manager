package ir.ehsan.asmrandroidbotcontroller.models

import com.google.gson.annotations.SerializedName

data class Update(
    @SerializedName("update_id")
    val updateId:Long,
    @SerializedName("message")
    val message: Message,
)
