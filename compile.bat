@echo off
if not exist out mkdir out
javac -d out .\src\HeapTest.java
pause
