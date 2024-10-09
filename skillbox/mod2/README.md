Запускается командой: 
docker build -t laba2:latest
docker run -it -e load-users=false laba2:latest

Содержит команды:
list // Выводит список студентов
add --firstName=<firstName> --lastName=<lastName> --age=<age> // Добавляет студента
remove --id=<student-id> // Удаляет студента по ID
remove-all // Удаляет всех студентов