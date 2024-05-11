@echo off

REM Agregar todos los archivos modificados
git add .

REM Pausar para permitir al usuario revisar los cambios antes de confirmarlos
pause

REM Solicitar al usuario que ingrese el mensaje de commit
set /p commit_Message="Ingrese el mensaje de commit: "
git commit -m "%commit_Message%"

REM Subir los cambios al repositorio remoto
git push

pause