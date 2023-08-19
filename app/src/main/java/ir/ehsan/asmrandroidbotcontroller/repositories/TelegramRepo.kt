package ir.ehsan.asmrandroidbotcontroller.repositories

import android.content.Context
import ir.ehsan.asmrandroidbotcontroller.models.BaseModel
import ir.ehsan.asmrandroidbotcontroller.models.Result
import ir.ehsan.asmrandroidbotcontroller.models.Update
import java.io.File

interface TelegramRepo {
    suspend fun getUpdates(offset:Long):BaseModel<Result<List<Update>>>
    suspend fun getUpdates():BaseModel<Result<List<Update>>>
    suspend fun sendMessage(chatId:String,text:String):BaseModel<Result<Any>>
}