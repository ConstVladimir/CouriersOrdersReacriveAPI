CREATE TYPE courier_type_enum AS ENUM ('FOOT','BIKE','AUTO');

CREATE CAST (varchar AS courier_type_enum) WITH INOUT AS IMPLICIT;

CREATE TABLE IF NOT EXISTS couriers(
    courier_id bigserial PRIMARY KEY,
    courier_type courier_type_enum,
    regions integer[],
    working_hours VARCHAR []
);


CREATE TABLE IF NOT EXISTS orders(
    order_id bigserial PRIMARY KEY,
    weight real,
    regions integer,
    delivery_hours VARCHAR [],
    cost integer,
    completed_time TIMESTAMP
);

--spring.jpa.generate-ddl = false
--spring.jpa.hibernate.ddl-auto = none
--spring.datasource.initialization-mode=always