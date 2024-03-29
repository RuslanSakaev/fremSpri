# Фреймворк Spring (лекции)
## Урок 1. Системы сборки Maven и Gradle для разработки Java приложений
### Описание
План урока:
- Введение в системы сборки: Maven и Gradle
- Maven: структура проекта и работа с pom.xml
- Управление зависимостями в Maven
- Жизненный цикл сборки в Maven
- Переход от Maven к Gradle: основные отличия и преимущества
- Создание и настройка задач в Gradle
- Управление зависимостями в Gradle
- Работа с многомодульными проектами в Gradle
- Автоматическая сборка и деплой проектов
- Лучшие практики работы с Maven и Gradle

### Основные фазы жизненного цикла default:
1. validate: В этой фазе проверяется корректность настроек проекта и
   отсутствие проблем с конфигурацией.
2. compile: В этой фазе исходный код проекта компилируется в байт-код Java.
3. test: В этой фазе выполняются тесты вашего приложения. Обратите внимание,
   что этот этап не вызывает остановку сборки в случае неудачных тестов.
4. package: В этой фазе создается артефакт вашего проекта (например,
   JAR-файл).
5. verify: В этой фазе выполняются проверки, чтобы убедиться, что пакет
      соответствует качеству и критериям проекта.
6. install: В этой фазе артефакт вашего проекта устанавливается в локальный
   репозиторий Maven, чтобы быть доступным для других проектов на вашем
   компьютере.
7. deploy: В этой фазе артефакт вашего проекта развертывается в удаленном
   репозитории, чтобы быть доступным для других разработчиков и команд.

Вы можете запускать отдельные фазы или несколько фаз последовательно,
вызывая их через командную строку. Например, чтобы запустить фазы compile, test
и package, вы можете использовать следующую команду:
~~~
mvn package
~~~
### Настройка проекта:
POM-файл является основой для настройки проекта в Maven. В POM-файле вы
можете указать информацию о проекте, такую как группа, идентификатор
артефакта, версия, а также настроить зависимости, плагины, репозитории и другие
элементы.
Вот некоторые из основных элементов, которые вы можете настроить в POM-файле:
1. Основная информация: Включает элементы <groupId>, <artifactId>, <version>
   и <packaging>. Они определяют уникальный идентификатор вашего проекта,
   версию и тип пакета (например, JAR или WAR).
2. Зависимости: Как мы уже обсудили ранее, элемент <dependencies>
   используется для указания зависимостей вашего проекта.
3. Репозитории: Элемент <repositories> позволяет добавить удаленные
   репозитории для поиска артефактов, которые недоступны в центральном
   репозитории.
4. Плагины: Внутри элемента <build> находится элемент <plugins>, который
   содержит объявления плагинов, используемых в вашем проекте.
5. Свойства: Элемент <properties> позволяет задавать переменные, которые
   могут быть использованы для настройки плагинов и других элементов
   POM-файла. Например, вы можете задать версию Java, используемую для
   компиляции вашего проекта:
~~~
<properties>
<maven.compiler.source>11</maven.compiler.source>
<maven.compiler.target>11</maven.compiler.target>
</properties>
~~~
6. Профили: Элемент <profiles> позволяет определить различные конфигурации
   для разных сред разработки или сценариев сборки. Например, вы можете
   создать профиль для разработки с дополнительными плагинами или
   настройками и другой профиль для сборки продакшн-версии вашего
   приложения.
## Практика Maven.Создание простого Java проекта с помощью Maven
Далее давай пройдемся по практической стороне работы с Maven, создадим
простой Java-проект и научимся собирать его с помощью Maven. Приступим!
Создание проекта с помощью Maven:

- Сначала установите Maven, следуя инструкциям на официальном сайте:
- ~~~
  https://maven.apache.org/install.html
  ~~~
- После установки убедитесь, что Maven работает, выполнив команду:
- ~~~
  mvn --version в командной строке.
  ~~~
- Чтобы создать новый проект с помощью Maven, воспользуйтесь архетипами.
~~~
Архетипы — это шаблоны проектов, которые упрощают создание структуры проекта.
~~~
- Для создания простого Java-проекта выполните следующую команду (лучше использовать стандартный CMD для избежания ошибок при сборке):
- ~~~
  mvn archetype:generate -DgroupId=ru.geekbrains -DartifactId="myProject" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  ~~~
  Здесь __com.mycompany.app__ — это пример __groupId__, а __my-app__ — __artifactId__ вашего
  проекта. Вы можете заменить их на свои значения. После выполнения команды
  Maven создаст новую директорию с именем my-app, содержащую структуру
  проекта.