## Web Application E-Commerce
## Spring Technologies + React/Redux.

### A. Build Client-Side
##### 1. Make sure you've installed:
> [Nodejs](https://nodejs.org/en/)

> [Webpack](https://webpack.github.io/docs/installation.html)

> [Yarn](https://yarnpkg.com/en/) (strongly recommended instead of npm)

##### 2. I suggest you using [npm-check-updates](https://www.npmjs.com/package/npm-check-updates) to check dependencies's updates

##### 3. Direct to UI module
```bash
cd wae-thesis-ui
```

##### 4. Installation Packages
```bash
yarn install
```

##### 5. Build
```bash
yarn run build
```
##### 6. Run
+ Development Mode :
```bash
yarn run dev
```
+ Production Mode : 
```bash
yarn run prod
```

### B. Build Server-Side
##### 1. Make sure you've installed:
> [Java](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html)

> [Maven](https://maven.apache.org/)

> [MySQL](https://www.mysql.com/)

##### 2. Back to root directory
```bash
cd..
```

##### 3. Build
```bash
mvn clean install
```

### C. Run Project
##### 1. Direct to Application Module
```bash
cd wae-thesis-application
```
##### 2. Run
+ Development Mode :
```bash
mvn spring-boot:run
```
+ Production Mode : 
```bash
cd target
java -jar app.jar
```
