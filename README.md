# StudyQuizApp
StudyQuizApp

# Setup on windows with IntelliJ IDEA
1) Clone project
2) Download JDK v17.0.1
    - File > Project Structure > Project > Add SDK > Download JDK
    - Chose version 17 and download it
    - Set that JDK as default SDK
3) Download openjfxv17.0.1 
    - https://download2.gluonhq.com/openjfx/17.0.1/openjfx-17.0.1_windows-x64_bin-sdk.zip
4) Add openjfx as library to project
    - File > Project Structure > Libraries > + > Java > "path\to\javafxsdk\lib"
    - Add it & apply changes.
5) Add VM options
    - Run > Edit Configurations...
    - Add **--module-path "\path\to\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml** to vm options.
    
Now you can run the application.
