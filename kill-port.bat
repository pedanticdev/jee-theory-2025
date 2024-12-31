@echo off
set PORT=%1
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%PORT%') do taskkill /F /PID %%a