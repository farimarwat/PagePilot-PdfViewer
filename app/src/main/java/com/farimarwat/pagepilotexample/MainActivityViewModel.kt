package com.farimarwat.pagepilotexample

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farimarwat.pagepilot.data.PdfFile
import com.farimarwat.pagepilot.utils.PagePilotUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "pagepilot"
class MainActivityViewModel: ViewModel() {
    val list = mutableStateListOf<PdfFile>()

    fun loadPdfFiles(context: Context) = viewModelScope.launch(Dispatchers.IO){
        val types = arrayOf("application/pdf")
        val files = PagePilotUtils.listFiles(context,types)
        list.addAll(files)
    }
}