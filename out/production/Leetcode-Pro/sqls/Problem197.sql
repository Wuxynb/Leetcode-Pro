# 197. 上升的温度
# SQL架构
# 表 Weather
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | id            | int     |
# | recordDate    | date    |
# | temperature   | int     |
# +---------------+---------+
# id 是这个表的主键
# 该表包含特定日期的温度信息
#
#
# 编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 id 。
#
# 返回结果 不要求顺序 。
#
# 查询结果格式如下例：
#
# Weather
# +----+------------+-------------+
# | id | recordDate | Temperature |
# +----+------------+-------------+
# | 1  | 2015-01-01 | 10          |
# | 2  | 2015-01-02 | 25          |
# | 3  | 2015-01-03 | 20          |
# | 4  | 2015-01-04 | 30          |
# +----+------------+-------------+
#
# Result table:
# +----+
# | id |
# +----+
# | 2  |
# | 4  |
# +----+
# 2015-01-02 的温度比前一天高（10 -> 25）
# 2015-01-04 的温度比前一天高（20 -> 30）
use `leetcode`;
Create table If Not Exists Weather (id int, recordDate date, temperature int);
Truncate table Weather;
insert into Weather (id, recordDate, temperature) values ('1', '2015-01-01', '10');
insert into Weather (id, recordDate, temperature) values ('2', '2015-01-02', '25');
insert into Weather (id, recordDate, temperature) values ('3', '2015-01-03', '20');
insert into Weather (id, recordDate, temperature) values ('4', '2015-01-04', '30');

# Write your MySQL query statement below
select w2.id
from Weather w1, Weather w2
where datediff(w2.recordDate, w1.recordDate) = 1
and w2.Temperature > w1.Temperature;