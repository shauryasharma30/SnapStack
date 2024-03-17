# SnapStack
An image hosting web application built on the powerful Spring Boot framework, leveraging the elegant templating engine Thymeleaf for seamless frontend integration.

## Steps to Setup
- Clone the application
`git clone https://github.com/shauryasharma30/SnapStack.git`
- Create MySQL Database
 `create database {database name}`
- Add Details for Database (db name, username, password) in `persistence.xml`
- Add your AWS S3 Bucket details *(access-key, secret-key, bucket name)* in `application.yml`
- To run the app use command `mvn spring-boot:run`
- Voila ! Snapstack will start running at [http://localhost:8080](http://localhost:8080/).

I've implemented AWS S3 to efficiently store images uploaded by users, ensuring seamless scalability and reliability. Additionally, I persistently store the corresponding image URLs in a MySQL Database for easy access and management of image data.

