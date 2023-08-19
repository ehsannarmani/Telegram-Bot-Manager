package ir.ehsan.asmrandroidbotcontroller.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ehsan.asmrandroidbotcontroller.models.BaseModel
import ir.ehsan.asmrandroidbotcontroller.models.Update
import ir.ehsan.asmrandroidbotcontroller.repositories.TelegramRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

class HomeViewModel:ViewModel(),KoinComponent {
    private val repo:TelegramRepo by inject()

    private val _updates:MutableStateFlow<List<Update>> = MutableStateFlow(emptyList())
    val updates = _updates.asStateFlow()

    private val _lastUpdate:MutableStateFlow<Update?> = MutableStateFlow(null)

    fun startPolling(){
        viewModelScope.launch {
            val result = if (_lastUpdate.value != null){
                repo.getUpdates(offset = _lastUpdate.value!!.updateId+1)
            }else{
                repo.getUpdates()
            }
            result.also {
                when(val response = it){
                    is BaseModel.Success->{
                        val updates = response.data.result
                        _lastUpdate.update { updates.last() }
                        _updates.update { it+updates }
                        startPolling()
                    }
                    is BaseModel.Error->{
                        startPolling()
                    }
                    else->{}
                }
            }
        }
    }
    fun sendMessage(chatId:String,text:String){
        viewModelScope.launch {
            repo.sendMessage(chatId, text)
        }
    }

}