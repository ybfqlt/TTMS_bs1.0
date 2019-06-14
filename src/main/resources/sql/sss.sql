use TTMS;

drop table Hall;
create table Hall
(
    hall_id           int not null auto_increment,
    hall_name         varchar(20) unique not null,
    hall_seat_count   int not null,
    hall_seat_row     int not null,
    hall_seat_col     int not null,
    hall_status       smallint comment '0:座位还没有初始化
                                        1:已经初始化座位
                                       -1:目前不可用，处于维修状态',
    primary key (hall_id)
);

drop table Seat;
/*========================================================================*/
/* Table: Seat座位表                                             */
/*========================================================================*/
create table Seat
(
    seat_id      int not null auto_increment,
    hall_id      int,
    seat_row     int,
    seat_col     int,
    seat_status  smallint comment '1:有座
                                   0:无座位/损坏',
    primary key (seat_id)
);

drop table Schedule;
/*========================================================================*/
/* Table:Schedule                                                */
/*========================================================================*/
create table Schedule
(
    schedule_id           int not null auto_increment,
    hall_id               int,
    movie_id              int,
    schedule_start_time   datetime not null,
    schedule_end_time     datetime not null,
    schedule_ticket_price numeric(5,2),
    ticketinit_status     smallint comment '1:未生成演出票
                                            0:已经生成演出票',
    primary key (schedule_id)
);

drop table Ticket;
/*========================================================================*/
/* Table: Ticket票表                                                */
/*========================================================================*/
create table Ticket
(
    ticket_id      bigint not null auto_increment,
    seat_id        int,
    schedule_id    int,
    ticket_status  smallint comment '0:已购
                                     1:可购',
    primary key (ticket_id)
);

drop table Orders;
/*========================================================================*/
/* Table:  Order订单表                                              */
/*========================================================================*/
create table Orders
(
    orders_id       int not null auto_increment,
    user_id         int,
    ticket_id       bigint,
    orders_time     datetime default CURRENT_TIMESTAMP not null,
    orders_type     smallint comment '1:已经购买
                                      0:已退款',
    primary key (orders_id)
);


drop table Salestatistics;
create table Salestatistics
(
    sales_statistics_id    int not null auto_increment,
    movie_id               int,
    sale_count             bigint,
    sale_money_count       numeric(10,2),
    primary key (Sales_statistics_id)
);


insert into Salestatistics (movie_id, sale_count, sale_money_count) values(1,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(2,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(3,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(4,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(5,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(6,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(7,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(8,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(9,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(10,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(11,0,0);
insert into Salestatistics (movie_id, sale_count, sale_money_count) values(12,0,0);