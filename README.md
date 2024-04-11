# Кофе-сайт

### Сайт для публикации ваших анекдотов и общения между пользователями

## Установка:
1. Склонируйте репозиторий
2. Запустите docker-compose в репозитории
   ``` bash
   docker-compose up -d
   ```
3. Скомпилируйте приложение
   ``` bash
   mvn compile
   ```
   Если у вас не установлен Maven или JDK-17, это нужно будет сделать
   
5. Запустите приложение
   ``` bash
   mvn exec:java -Dexec.mainClass="gg.springtry.dripper_web.DripperWebApplication"
   ```
