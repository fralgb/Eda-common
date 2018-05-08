@echo off
call mvn versions:set versions:commit -DnewVersion="1.0.8.0-SNAPSHOT"
call mvn clean source:jar install
call mvn deploy
@echo on
pause
