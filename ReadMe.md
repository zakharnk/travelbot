Telegram bot that response with a description of the city by the given city name.

Telegram bot name: "@cityDescrBot"
Telegram bot token: "1451518942:AAG0ggdFubjmuh719Knwif_T139Mf8emNgw"

DBMS: PostgresSql
Database Create Script:

CREATE DATABASE bot
create table city
(
city_id     serial not null
constraint city_pkey
primary key,
name        text,
description varchar
);

Run spring app

