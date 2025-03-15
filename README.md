# PagePilot-PdfViewer
A pdfviewer for android jetpack compose with search feature

<img src="logo.png" alt="pdfviewer"/>

## Main Features
1. Single line of code
2. Zooming
3. Search word in page
4. Share either file or page
5. Smooth scrolling (horizontal/vertical)
6. Remember last visited page

## Implementation
Below are steps to implement in your android studio compose project
### Step 1:
Place below in repository{} block of settings.gradle.kts
```kotlin
maven {
     url = uri("https://repo.itextsupport.com/android")
}
```
### step 2 
Place it in dependencies{} block of app level gradle file
```kotlin
implementation("io.github.farimarwat:pagepilot-pdfviewer:1.2")
```

## Usage
There are two ways to use PagePilot Pdf Viewer. 
1. Already provided Templete which needs just single line of code.
2. Customize by your self

### Template
```kotlin
val uri:Uri = ...
 PdfView(fileuri = uri)
```
### Other properties
```kotlin
fun PdfView(
    fileUri: Uri,
    password: String? = null,
    topBarColor: Color = Color.White,
    iconsColor: Color = Color.Black,
    counterBackgroundColor: Color = Color.Gray,
    pageBackgroundColor:Color = Color.White,
    OnError: (error: String) -> Unit = {}
)
```

### Bonus
To list pdf files, I have provided a utility to get all pdf files:
```kotlin
 val types = arrayOf("application/pdf") // type of file to list. here I only want to list pdf files
 val files = PagePilotUtils.listFiles(context,types)
```
This will return a list of PdfFile object data class which is already available in the package:
```kotlin
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
```kotlin
<uses-permission
        android:name="android.permission.MANAGE_EXTERNAL_STORAGE"
        tools:ignore="ScopedStorage" />
```
**Note:Sample project is also available in the repo. just clone it**

### Customizing
To use PagePilot PdfViewer in customize way:

### Get Main Object
First get the main object and then use it methods:
```kotlin
val mPdfTool = PdfViewTool.Builder(context)
            .build()
```

### Open Document
```kotlin
 mPdfTool?.let { tool ->
           if(tool.isProtected(uri)){ //this will check if file is protected
               tool.openDocument(uri, password){ error ->
                       //log error here
                   }
           } else {
               tool.openDocument(uri){ error ->
                    //log error here
               }
           }
            mPageCount = tool.getPageCount()
            Log.e(TAG_PDFTOOL,"PageCount: $mPageCount")
        }
```

### Close Document
It is necessary to close the document onstop of the activity
```kotlin
 mPdfTool?.closeDocument()
```

### Get page as bitmap
```kotlin
 mPdfTool?.getPageBitmap(
     scaleFactor = scaleFactor, //this is to get scaled image. e.g. 1.0 for same size and 1.5 to upscale
     index = index, //get the page e.g. to get the first page = 0
     search = search // if you want to search a word the provide it else provide empty string = ""
)
```

### Get page size in rectangle
```kotlin
mPdfTool?.getPageSize(index)
```

### Get total pages count
```kotlin
mPdfTool?.getPageCount()
```

### Get pdf details
```kotlin
mPdfTool?.getDetails()
```
This will return object of PdfDetails class
```kotlin
data class PdfDetails(
    val author:String?,
    val creator:String?,
    val title:String?,
    val keywords:String?,
    val subject:String?,
    val created:String?
)
```

### Important
I have no time to test it thoroughly. So do not panic if some issue occures. Kindly open an issue and I will try my best to solve it.

### Version History
**1.2**
- Page background customization added
  
**1.1**
- Initial release

### Buy me a cup of Tea
<a href="https://www.patreon.com/farimarwat">Click Here </a>
