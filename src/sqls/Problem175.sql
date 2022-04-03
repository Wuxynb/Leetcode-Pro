Create table If Not Exists Person
(
    personId  int,
    firstName varchar(255),
    lastName  varchar(255)
);
Create table If Not Exists Address
(
    addressId int,
    personId  int,
    city      varchar(255),
    state     varchar(255)
);
Truncate table Person;
insert into Person (personId, lastName, firstName)
values ('1', 'Wang', 'Allen');
insert into Person (personId, lastName, firstName)
values ('2', 'Alice', 'Bob');
Truncate table Address;
insert into Address (addressId, personId, city, state)
values ('1', '2', 'New York City', 'New York');
insert into Address (addressId, personId, city, state)
values ('2', '3', 'Leetcode', 'California');

# Write your MySQL query statement below
# 左连接：以左表为主，显示左表所有的数据，右表中没有的显示null值。
# 右连接：以右表为主，显示右表所有的数据，左表中没有的显示null值
select firstName, lastName, city, state
from Person
         left join Address A on Person.personId = A.personId;
