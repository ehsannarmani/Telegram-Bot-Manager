package ir.ehsan.asmrandroidbotcontroller.repositories

import android.content.Context
import androidx.core.net.toUri
import ir.ehsan.asmrandroidbotcontroller.models.BaseModel
import ir.ehsan.asmrandroidbotcontroller.models.Result
import ir.ehsan.asmrandroidbotcontroller.models.Update
import ir.ehsan.asmrandroidbotcontroller.network.TelegramApi
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File
import java.lang.Exception

class TelegramRepoImpl(private val telegramApi: TelegramApi):TelegramRepo {
    override suspend fun getUpdates(offset: Long): BaseModel<Result<List<Update>>> {
        return telegramRequest {
            telegramApi.getUpdates(offset)
        }
    }

    override suspend fun getUpdates(): BaseModel<Result<List<Update>>> {
        return telegramRequest {
            telegramApi.getUpdates()
        }
    }

    override suspend fun sendMessage(chatId: String, text: String): BaseModel<Result<Any>> {
        return telegramRequest {
            telegramApi.sendMessage(chatId, text)
        }
    }
}

private suspend fun<T> telegramRequest(request:suspend ()->Response<T>):BaseModel<T>{
    try {
        request().also {
            return if (it.isSuccessful){
                BaseModel.Success(data = it.body()!!)
            }else{
                BaseModel.Error(it.errorBody()?.string().toString())
            }
        }
    }catch (e:Exception){
        return BaseModel.Error(e.message.toString())
    }
}