package com.farimarwat.pagepilotexample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farimarwat.pagepilot.data.PdfFile

@Composable
@Preview
fun PdfItemPreview(){
    PdfItem(
        PdfFile(1234,"","","resume.pdf",1234,1234,"pdf")
    )
}
@Composable
fun PdfItem(pdfFile:PdfFile,onClick:(pdfFile:PdfFile)->Unit={}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 8.dp)
            .clickable {
                onClick(pdfFile)
            },
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = pdfFile.name, fontSize = 16.sp)
    }
}