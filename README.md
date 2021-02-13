### sample camel on springboot with kafka and mysql


c new-app ubi8-openjdk-11:1.3~https://github.com/wohshon/camel-kafka-mysql.git --name=kafka-db-app --env=MYSQL_HOST=mysql --env=BROKER_URL=my-cluster-kafka-bootstrap --env=BROKER_PORT=9091 --env=TOPIC_NAME=my-topic


oc run kafka-producer -i --image=registry.redhat.io/amq7/amq-streams-kafka-25-rhel7:1.5.0 --rm=true --restart=Never -- bin/kafka-console-producer.sh  --bootstrap-server my-cluster-kafka-bootstrap.fuse-app.svc.cluster.local:9092  --topic my-topic

{"id":"","name":"PS5","description":"PS5 White","price":200} 

{"id":"","name":"MBP16","description":"macbook pro","price":2500}

{"id":"","name":"Switch","description":"Switch Console","price":100}


mvn clean fabric8:deploy -Popenshift

oc patch -n fuse-app hawtio.hawt.io/fuse-console --type=merge -p '{"metadata": {"finalizers":null}}'


