# JavaReadLog
Приложение для чтения лог файлов на Java, выполненное в рамках тестового задания.

Для запуска необходимы
Java 1.8.0_74
Maven 3.3.9
Tomcat 7

Порядок запуска приложения

1. Добавить в файл \conf\settings.xml в директории с Maven.
<servers>
  ...
  <server>
    <id>tomcat7</id>
    <username>admin</username>
    <password>admin</password>
  </server>
  ...
</servers>
  
2. Добавить в файл \conf\tomcat-users.xml в директории с Tomcat7.
<tomcat-users>
  <role rolename="manager"/>
  <role rolename="manager-script"/>
  <role rolename="manager-gui"/>
  <user username="admin" password="admin" roles="manager,manager-gui,manager-script"/>
</tomcat-users>

3. Настроить файл конфигурации \src\main\resources\connection.properties.
4. Запустить Tomcat.
5. Запустить файл deploy.bat
6. Приложение будет доступно по адресу http://localhost:8080



