### sample camel on springboot with java dsl, jpa, jaxb  and mysql

#### For bench marking purpose 

Requires a running mysql at the endpoint specified in `applications.properties`



```
c new-app mysql -e MYSQL_PASSWORD=Passw0rd_ -e MYSQL_USER=demouser -e MYSQL_DATABASE=demodb


oc new-app ubi8-openjdk-11:1.3~https://github.com/wohshon/springboot-camel-javadsl.git --name=springboot-camel --env=MYSQL_HOST=mysql

curl -v  -X POST localhost:8080/api/rest/order/test-id -d @order.xml -H "Content-type: application/xml"
```



