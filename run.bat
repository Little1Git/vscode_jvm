@echo off
chcp 65001
java -Xms10m -Xmx100m -verbose:gc -cp out HeapTest
pause
