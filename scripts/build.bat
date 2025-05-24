@echo off
REM Change to the parent directory (project root)
cd ..

REM Create bin directory if it doesn't exist
if not exist bin (
    mkdir bin
)

REM Compile Java files from src to bin
javac -d bin src\*.java

echo Build successful.
