package com.farimarwat.pagepilotexample

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.farimarwat.pagepilot.data.PdfFile
import com.farimarwat.pagepilot.utils.PagePilotUtils
import com.farimarwat.pagepilotexample.ui.theme.PagePilotExampleTheme

class MainActivity : ComponentActivity() {

    lateinit var mContext:Context
    val viewModel:MainActivityViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.R)
    private val storagePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allpermissions = permissions.all { it.value }
        Log.e(TAG,"${allpermissions}")
        if(allpermissions){
            viewModel.loadPdfFiles(mContext)
        }
    }

    //Activity Launcher

    val activityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){

        if(SDK_INT >= Build.VERSION_CODES.R){
            if(!Environment.isExternalStorageManager()){
                requestStoragePermission(mContext)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        super.onCreate(savedInstanceState)
        requestStoragePermission(mContext)
        setContent {
            val context = LocalContext.current
            PagePilotExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val list = remember{
                       viewModel.list
                   }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ){
                        items(list){file->
                            PdfItem(pdfFile = file){f ->
                                val intent = Intent(mContext,ViewerActivity::class.java)
                                intent.putExtra("URI",f.uri)
                                mContext.startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun requestStoragePermission(context:Context){
        if(SDK_INT >= Build.VERSION_CODES.R){
            try{
                val intent = Intent()
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                val uri = Uri.fromParts("package", context.getPackageName(), null)
                intent.setData(uri)
                activityLauncher.launch(intent)
            }catch (ex:Exception){
                val intent = Intent()
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                activityLauncher.launch(intent)
            }
        } else {
            storagePermissionLauncher.launch(arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ))
        }
    }
}