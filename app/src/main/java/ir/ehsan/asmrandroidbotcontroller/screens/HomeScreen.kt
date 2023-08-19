package ir.ehsan.asmrandroidbotcontroller.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.lifecycle.viewmodel.compose.viewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    LaunchedEffect(Unit) {
        viewModel.startPolling()
    }

    val updates by viewModel.updates.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Updates:")
        AnimatedVisibility(visible = updates.isNotEmpty()) {
            LazyColumn {
                items(updates) { update ->
                    Text(text = "New update received: ${update.message.text}")
                }
            }
        }
        AnimatedVisibility(visible = updates.isEmpty()) {
            Text(text = "No Updates Yet!")
        }
        val (chatId, setChatId) = remember {
            mutableStateOf("")
        }
        val (text, setText) = remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier=Modifier.fillMaxWidth(),
            value = chatId, onValueChange = {
                setChatId(it)
            },
            label = {
                Text(text = "Chat Id")
            }
        )
        OutlinedTextField(
            modifier=Modifier.fillMaxWidth(),
            value = text, onValueChange = {
                setText(it)
            },
            label = {
                Text(text = "Text")
            }
        )
        Button(onClick = {
            viewModel.sendMessage(
                chatId, text
            )
        },shape = RoundedCornerShape(4.dp), modifier = Modifier.fillMaxWidth()) {
            Text(text = "Send Message")
        }
    }


}