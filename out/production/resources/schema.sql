CREATE TABLE IF NOT EXISTS couriers(
    courier_id bigserial PRIMARY KEY,
    courier_type courier_type_enum,
    regions integer[],
    working_hours VARCHAR []
);
CREATE TYPE courier_type_enum AS ENUM ('FOOT','BIKE','AUTO');
CREATE CAST (varchar AS courier_type_enum) WITH INOUT AS IMPLICIT;