@echo off
set YYYYMMDD_HHMMSS=%date:~10,4%%date:~4,2%%date:~7,2%_%time:~0,2%%time:~3,2%%time:~6,2%
mongodump -h 34.70.190.164:27777 -d java3yarisma -u bilgeuser -p bilge!!** -o %YYYYMMDD_HHMMSS%
echo yedek alma işlemi tamamlandı
pause