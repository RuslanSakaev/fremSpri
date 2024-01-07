# Фреймворк Spring (семинары)
## Урок 1. Системы сборки Maven и Gradle для разработки Java приложений
### Установка:
1. Скачиваем архив для Win (https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip) или Unix (https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz)
2. Распаковываем в нужную директорию (в моём случае C:\Program Files)
3. В Свойствах системы -> Дополнительные параметры системы -> Переменные -> Системные переменные выбираем __Path__ и добавляем путь до папки из архива __bin__ (C:\Program Files\apache-maven-3.9.6\bin) или (C:\Program Files\gradle-8.5\bin)
4. Для работы с Maven и Gradle нужна Java, проверяем их установку в командной строке:
~~~
java -version
mvn -version
gradle -version
~~~
5. Переходим в директорию с проектом: __PS C:\Users\hardi> d:__, далее с помощью команды CD выбираем __PS D:\> CD myProdgectGradle__.
6. Создаём проект с помощью следующей команды:
~~~
mvn archetype:generate -DgroupId=com.example -DartifactId="myapp" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
~~~
В данной команде меняем имя проекта (com.example) на нужный нам: __ru.geekbrain__, и название (myapp) на: __myprojectmaven__.
Получается следующее:
~~~
mvn archetype:generate -DgroupId=ru.geekbrain -DartifactId="myprojectmaven" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
~~~