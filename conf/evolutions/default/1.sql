# Tasks Schema

# --- !Ups

CREATE SEQUENCE board_id_seq;
CREATE TABLE boards (
    Id integer NOT NULL DEFAULT nextval('board_id_seq'),
    BoardName varchar(255),
    Height varchar(255),
    Width varchar(255),
    Volume varchar(255),
    Thickness varchar(255),
    Image varchar(255),
);

# --- !Downs
DROP TABLE boards;
DROP SEQUENCE board_id_seq;

    
