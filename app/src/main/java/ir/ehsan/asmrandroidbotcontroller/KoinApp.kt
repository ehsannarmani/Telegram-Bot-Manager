package ir.ehsan.asmrandroidbotcontroller

import android.app.Application
import ir.ehsan.asmrandroidbotcontroller.network.TelegramApi
import ir.ehsan.asmrandroidbotcontroller.repositories.TelegramRepo
import ir.ehsan.asmrandroidbotcontroller.repositories.TelegramRepoImpl
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BOT_TOKEN = "6352790119:AAHwhAgznaasJ_0DLBWAIXxs8dYvTApSoY8"

class KoinApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single {
                    Retrofit
                        .Builder()
                        .baseUrl("https://api.telegram.org/bot$BOT_TOKEN/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                single {
                    val retrofit:Retrofit = get()
                    retrofit.create(TelegramApi::class.java)
                }
                single {
                    TelegramRepoImpl(telegramApi = get())
                } bind TelegramRepo::class
            })
        }
    }
}