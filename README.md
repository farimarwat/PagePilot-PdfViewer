# PagePilot-PdfViewer
A pdfviewer for android jetpack compose with search feature

## Main Features
1. Single line of code
2. Zooming
3. Search word in page
4. Share either file or page
5. Smooth scroller (horizontal/vertical)
6. Remember last visited page

## Implementation
Below are steps to implement in your android studio compose project
### Step 1:
Place below in repository{} block of settings.gradle.kts
```
maven {
     url = uri("https://repo.itextsupport.com/android")
}
```
### step 2 
Place it in dependencies{} block of app level gradle file
```
implementation("io.github.farimarwat:pagepilot-pdfviewer:1.1")
```

## Usage
```
val uri:Uri = ...
 PdfView(fileuri = uri)
```
### Other properties
```
fun PdfView(
    fileuri: Uri,
    password: String? = null, //password if any
    topbarcolor: Color = Color.White, //container color of top bar
    iconscolor: Color = Color.Black, // icons color used in the view e.g. icons in top bar
    counterbackgroundcolor: Color = Color.Gray, //page counter background color
    OnError: (error: String) -> Unit = {}
)
```

### Bonus
To list pdf files, I have provided a utility to get all pdf files:
```
 val types = arrayOf("application/pdf") // type of file to list. here I only want to list pdf files
 val files = PagePilotUtils.listFiles(context,types)
```
This will return a list of PdfFile object data class which is already available in the package:
```
data class PdfFile(
    val id:Long,
    val path:String,
    val uri:String,
    val name:String,
    val size:Long,
    val datetime:Long,
    val type:String
)
```
### Important
To  list pdf files, you have to use:
```
<uses-permission
        android:name="android.permission.MANAGE_EXTERNAL_STORAGE"
        tools:ignore="ScopedStorage" />
```
**Note:Sample project is also available in the repo. just clone it**
