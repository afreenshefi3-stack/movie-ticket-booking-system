@echo off
cd /d "%~dp0src"
javac --module-path "C:\Users\afree\Downloads\openjfx-26_windows-x64_bin-sdk\javafx-sdk-26\lib" --add-modules javafx.controls,javafx.fxml *.java
java --module-path "C:\Users\afree\Downloads\openjfx-26_windows-x64_bin-sdk\javafx-sdk-26\lib" --add-modules javafx.controls,javafx.fxml Main
pause