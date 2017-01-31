CREATE SEQUENCE grocery_gro_id_seq;

CREATE TABLE helloworld.grocery_item
(
    gro_id integer DEFAULT nextval('grocery_item_gro_id_seq'::regclass) PRIMARY KEY NOT NULL,
    gro_name varchar(26),
    gro_cat varchar(26),
    gro_aisle integer,
    gro_price numeric
);
CREATE UNIQUE INDEX grocery_item_gro_id_uindex ON helloworld.grocery_item (gro_id);
INSERT INTO helloworld.grocery_item (gro_id, gro_name, gro_cat, gro_aisle, gro_price) VALUES (1, 'Milk', 'Food', 17, 2.99);
