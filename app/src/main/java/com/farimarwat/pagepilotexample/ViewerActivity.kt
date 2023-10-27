package com.farimarwat.pagepilotexample

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.farimarwat.pagepilot.PdfView
import com.farimarwat.pagepilotexample.ui.theme.PagePilotExampleTheme

class ViewerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val uri = intent.getStringExtra("URI")

        super.onCreate(savedInstanceState)
        setContent {
            PagePilotExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   PdfView(fileuri = Uri.parse(uri))
                }
            }
        }
    }
}
