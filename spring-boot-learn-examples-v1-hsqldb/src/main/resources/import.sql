--
-- Sample dataset containing a number of Hotels in various Cities across the world.  The reviews are entirely fictional :)
--

-- =================================================================================================
-- AUSTRALIA
CREATE TABLE city(
id int primary key not null ,
country varchar(50) default null, 
name varchar(50) default null, 
state varchar(50) default null, 
map varchar(50) default null
);
-- Brisbane
insert into city(country, name, state, map) values ('Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502')
