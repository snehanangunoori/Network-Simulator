@echo off
REM Change to the parent directory (project root)
cd ..

REM Check if an argument (input file) is provided
if "%~1"=="" (
    echo Usage: run.bat input_file
    exit /b 1
)

REM Run the compiled Java program with the input file
java -cp bin Main %1
