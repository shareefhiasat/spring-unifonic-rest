Check Vedio First
I used Cloudinary for hosting it its cool cloud SAAS
[Vedio Swagger Usage Debug from IDE with configuration MySql](http://res.cloudinary.com/shareefhiasat/raw/upload/v1528838326/Video-2018-06-13_00.12.39_s9wymo.mp4)

[Vedio How to use SDK client with maven java command](http://res.cloudinary.com/shareefhiasat/raw/upload/v1528839623/Video-2018-06-13_00.31.06_gx8dyq.mp4)
[Get the pre prepared WAR packaged ](Download War https://drive.google.com/open?id=1KDG99pANcg41P0YV1uYh75sYIm0vKPkv)

[How to run with spring boot command with mysql and jdbc, then spring-data-jpa profiles](http://res.cloudinary.com/shareefhiasat/raw/upload/v1528839984/Video-2018-06-13_00.42.47_nzasdb.mp4)
[Try Hsqldb profile no need to configure any database locally](http://res.cloudinary.com/shareefhiasat/raw/upload/v1528840165/Video-2018-06-13_00.46.57_jm7ibi.mp4)

# REST version of Spring Boot Unifonic Sample Application

This backend version of the `Spring Unifonic` application only provides a REST API. </br>**There is no UI**.

## Understanding the Spring Unifonic application with a few diagrams
<a href="https://unifonic.docs.apiary.io">Unifonic API Guide</a>

## Running Unifonic locally ( depending on OS)
```
	git clone  https://github.com/shareefhiasat/spring-unifonic-rest.git
	cd spring-unifonic
	./mvnw spring-boot:run
or
	mvn.cmd spring-boot:run
	
	You may need to try this if you had trouble running
	mvn.cmd spring-boot:run -Dspring.profiles.active=hsqldb,spring-data-jpa
```

You can then access Unifonic here: http://localhost:9966/unifonic/

## Swagger REST API documentation 
After running you can also access the swagger Mockup Service for test at

http://localhost:9966/unifonic/swagger-ui.html

![](https://s.nimbusweb.me/attachment/1804114/uxyxfp5yxzwnh9pa31iz/735212-DFAnOKTmGu95JnsP/Swagger_UI_-_Google_Chrome.png)

![](https://s.nimbusweb.me/attachment/1804103/i8teut8v1h6dqedfgy6c/735212-6qRXHbUr4SzqfDHS/Screenshot_2018-06-12_10.12.06.png)

## Database configuration

In its **default** configuration, Unifonic uses an in-memory database (**HSQLDB**) which
gets populated at startup with message.
A similar setups is provided for **MySql** and **PostgreSQL** in case a persistent database configuration is needed.
To run unifonic locally using persistent database, it is needed to change profile defined in `application.properties` file.

For MySQL database, it is needed to change param `"hsqldb"` to `"mysql"` in string
```
spring.profiles.active=hsqldb,spring-data-jpa
```
 defined in application.properties file.

Before do this, would be good to check properties defined in `application-mysql.properties` file.

```
spring.datasource.url = jdbc:mysql://localhost:3306/unifonic?useUnicode=true
spring.datasource.username=pc
spring.datasource.password=unifonic 
spring.datasource.driver-class-name=com.mysql.jdbc.Driver 
spring.jpa.database=MYSQL
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=none
```      

You may also start a MySql database with docker:

```
docker run --name mysql-unifonic -e MYSQL_ROOT_PASSWORD=unifonci -e MYSQL_DATABASE=unifonic -p 3306:3306 mysql:5.7.8
```

For PostgeSQL database, it is needed to change param `"hsqldb"` to `"postgresql"` in string
```
spring.profiles.active=hsqldb,spring-data-jpa
```
 defined in `application.properties` file.

Before do this, would be good to check properties defined in application-postgresql.properties file.

```
spring.datasource.url=jdbc:postgresql://localhost:5432/unifonic
spring.datasource.username=postgres
spring.datasource.password=unifonic
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database=POSTGRESQL
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=none
```
You may also start a Postgres database with **docker**:

```
docker run --name postgres-unifonic -e POSTGRES_PASSWORD=unifonic -e POSTGRES_DB=unifonic -p 5432:5432 -d postgres:9.6.0
```

## Working with Unifonic in Eclipse/STS

### prerequisites
The following items should be installed in your system:
* Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
* git command line tool (https://help.github.com/articles/set-up-git)
* Eclipse with the m2e plugin (m2e is installed by default when using the STS (http://www.springsource.org/sts) distribution of Eclipse)

Note: when m2e is available, there is an m2 icon in Help -> About dialog.
If m2e is not there, just follow the install process here: http://eclipse.org/m2e/download/


### Steps:

1) In the command line
```
git clone  https://github.com/shareefhiasat/spring-unifonic-rest.git

```
2) Inside Eclipse
```
File -> Import -> Maven -> Existing Maven project
```

## Looking for something in particular?

| Layer | Source |
|--|--|
| REST API controllers | [REST folder](src/main/java/com/unifonic/rest) |
| Service | [ClinicServiceImpl.java](src/main/java/com/unifonic/service/ClinicServiceImpl.java) |
|JDBC | [jdbc folder](src/main/java/com/unifonic/repository/jdb) |
| JPA | [jpa folder](src/main/java/com/unifonic/repository/jpa) |
| Spring Data JPA | [springdatajpa folder](src/main/java/com/unifonic/repository/springdatajpa) |

## fIntellij Run/Debug Configuration
Glimps for intellij configuration
![](https://s.nimbusweb.me/attachment/1804191/pf1xmytk69fmim3c5oy0/735212-ZrMSuR5q2TTEIcMk/Screenshot_2018-06-12_10.43.15.png)</br> 





### Personal Infomation / Shareef Hiasat
#### 1. My GitHub Account
<a href="https://github.com/shareefhiasat">See My GitHub Profile Here</a>

#### 2. My StackOverFlow Account
<a href="https://stackoverflow.com/users/944593/shareef">See My StackOverFlow Profile Here</a>


### Questions to Unifonic about API Guide
#### After implementing some end points at </br> [Unifonic API Guide](https://unifonic.docs.apiary.io) <br/>i noticed the following issues in docs please continue reading.

#### 1. <span style="color:orange;">Why in docs the sample saying DateCreated" :"2014-07-22" but at same time it say formate should be yyyy-mm-dd hh:mm:ss !</span>
#### 2. <span style="color:orange;">Why and what (there is no/is) time zone specification or default ?</span>
#### 3. <span style="color:orange;">Why the amount is it in fraction cent as i discovered ,I believe docs should have more info!</span>
#### 4. <span style="color:orange;">Why Balance is String and cost is double ? </span>
#### 5. <span style="color:orange;">Why Balance is in `sendBulk` is string while its in `send` double ? </span>
#### 6. <span style="color:orange;">Why is optional parameter `Priority: (optional, string)` has default of only value High; what would happen if i sent any dummy value ?</span>
#### 7. <span style="color:orange;">Why Docs dose not say the `NumberOfUnits` is according to carier or lanuage or its really same for all ?</span>

## Tasks Done
- [x] _Implement_ <b>`Send`</b> (https://unifonic.docs.apiary.io/#reference/messages/send)
- [x] _Implement_ <b>`SendBulk`</b> (https://unifonic.docs.apiary.io/#reference/messages/sendbulk)
- [x] _Implement_ <b>`GetMessageIDStatus`</b> (https://unifonic.docs.apiary.io/#reference/messages/getmessageidstatus)
- [x] _Add_ <b>`documentation comments`</b> and <b>`javaDocs`</b> in code.
- [] _Prepare_ Design Document
- [x] A Maven based `java client SDK` that can be shared with customers to use this service. This SDK will encapsulate the HTTP requests and responses.(#Running Unifonic locally depending on OS))
you can use [Run packaged war](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#build-tool-plugins-maven-packaging)
    1. Run `$ mvn package`
    2. If you look in the target directory, you should see spring-unifonic-1.5.2.war. The file should be around 34 MB in size. If you want to peek inside, you can use jar tvf, as follows:</br>
    `$ jar tvf target/spring-unifonic-1.5.2.war`
    3. `$ java -jar target/spring-unifonic-1.5.2.war` you shall see:
       
       ``  .   ____          _            __ _ _
        /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
       ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
        \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
         '  |____| .__|_| |_|_| |_\__, | / / / /
        =========|_|==============|___/=/_/_/_/
        :: Spring Boot ::  (v2.0.2.RELEASE)
       ....... . . .
       ....... . . . (log output here)
       ....... . . .
       ........ Started Example in 2.536 seconds (JVM running for 2.864) ``
       **As before, to exit the application, press ctrl-c.**
       4. Go to http://localhost:9966/unifonic/swagger-ui.html
- [X] Prepare `JMeter version 3.1r` A stress testing setup to demonstrate the service performance
Test Plan is here [JMeter Unifonic-send-then-get-status-3000-calls.jmx](http://res.cloudinary.com/shareefhiasat/raw/upload/v1528856619/Unifonic-send-then-get-status_jswah0.jmx)
Sending 1500 message, and get status 1500 times.
**Test was on my local laptop i7 8GB RAM , 250 GB SSD HDD.**
![](https://s.nimbusweb.me/attachment/1807019/tigfp1ldiccxf8gbw911/735212-m54wGEzrWS8aXkyZ/Screenshot_2018-06-13_05.41.17.png)
No errors or overflow.
       
## Technology details
- [x] [Java 8+](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
- [x] [Spring boot](https://spring.io/projects/spring-boot)
- [x] [Tomcat](http://tomcat.apache.org/)
- [x] [Swagger](https://swagger.io/) Service Mockups
- [X] _Implement_ <b>Profiles</b> for 
    - [MySql](https://www.mysql.com/) </br>
    - [Hsqldb](http://hsqldb.org/)</br>
    - [Postgresql](https://www.postgresql.org/)</br>
    
- [X] _Implement_ [HazelCast](https://hazelcast.com/)
- [X] Prepare `JMeter` A stress testing setup to demonstrate the service performance

[Vedio for JMeter 3000 call per seconds]
- [] **Deploy on cloud**
### Extra Libraries/Tools Used:
- _Implement_ [Logback](https://logback.qos.ch/) logging successor for log4j and implements slf4j API
- Used [SonarLint](https://www.sonarlint.org/) to clean up warning, and best practices/fixing code issues.
- Used [SprintData](http://projects.spring.io/spring-data/)
- Used [Hibernatet]()
- Used [JPA](https://en.wikipedia.org/wiki/Java_Persistence_API)
- Used [Spring Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html)

### Extra Tweaks
- Use [JDK 7+](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) diamond operator
- Implement [Caching](https://spring.io/guides/gs/caching/) data with spring on status table Repository
- [editor config](https://github.com/xxx/master/.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>.
for unifying editor in all IDE's.
- Implement Caching hibernate ( its not very usefull after i added hazelcast cache)

### Extra Logic Implemented
- Implement API end point for `getAllMessages`
- Implement API end point for `deleteMessage`
- Implement API end point for `getMessageById`
- Implement Rest API end points for `status table` / query/add/delete and getAllStatuses
- Add Simple Logic for calculating `cost` cost is 1USD
- Add Simple Logic for calculating `balance` is set to 10000USD
- Add Simple Logic for calculating `number of units` 160 Chars mean 1 unit
- Add acceptance for any `appSid` for simplifying the mockin process
- Add default `priority` as `Height` according to `priority` docs [Priority Send Operation API Guide](https://unifonic.docs.apiary.io/#reference/messages/send?console=1)
- Add default `senderId` if its optional and empty.
- Add default `currencyCode` as <b>USD</b>.
- Add Calulating `cost`, and `numberOfUnits` to `SendBulk` realistic calcs.
for example when send bulk we save record with `numberOfUnits` for each one
- Add Default status as Queued" other possiblities are , "Sent", "Failed" and "Rejected"
but i <b>implemented</b> a `table lookup` for possible values and its name(description e.g Queued)
is fetched at runtime.


### Further Suggested Extra Work If i only had more time :disappointed_relieved: 
- [x] Implement <b>Error messages</b> link with error of body exceeds limit.
from <br/> [Unifonic API Guide Error-List](https://unifonic.docs.apiary.io/#reference/errors-list/send?console=1)
- [X] Implement Authorization correct `appSid`, and other security.
- [] Implement proper response `headers` for <b>security</b> and <b>caching</b> purposes.        
- [] Implement more advanced check on `Accounts` and `Balances` and validate proper errors accordingly.  
- [] Implement simple `senderId`(s) per account and set default one
- [] Implement Tests using [Mockito](https://github.com/mockito/mockito) ,[JUnit](https://junit.org/junit5/) and [Spring boot test](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html)
- [] Add Metrics Spring watcher for hazelcast

### Notes
 application use two active profiles

 1. One for **_select_ repository layer**
     - When using HSQL, use: hsqldb
     - When using MySQL, use: mysql
     - When using PostgeSQL, use: postgresql

 2. One - for **_select_ database**
     - When using Spring jpa, use: jpa
     - When using Spring JDBC, use: jdbc
     - When using Spring Message JPA, use: spring-data-jpa
