# feedr
UBC CPSC 304 PROJECT

### To build this project:

./gradlew build;

cd web;

yarn install;

yarn run build;

cd ..;

./gradlew build;

java -jar build/libs/Feedr-0.1.0.jar

Go to localhost:8080

### Run population scripts in this order:
db_setup -- drop_all_tables -- table_setup -- populate_users -- populate_food -- populate_orders -- populate_order_include_food -- populate_cancellation -- populate_delivered -- populate_rating

### To build Protobuf
./gradlew generateProto
