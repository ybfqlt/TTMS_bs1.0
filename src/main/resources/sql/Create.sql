drop DATABASE IF EXISTS TTMS;

create DATABASE TTMS;

use TTMS;

/*========================================================================*/
/* Table: User用户表                                               */
/*========================================================================*/
create table User
(
    user_id           int not null auto_increment,
    user_name         varchar(20) not null unique,
    user_password     varchar(50) not null,
    user_qq           varchar(15) not null,
    user_type         varchar(2)  not null default 'u' comment 'u:普通用户 g:管理员 j:经理',
    user_registertime varchar(30) default CURRENT_TIMESTAMP not null,
    primary key(user_id)
);


/*========================================================================*/
/* Table:Movie 影片表                                            */
/*========================================================================*/
create table Movie
(
    movie_id            int not null auto_increment,
    movie_rating        double not null,
    movie_genres        varchar(40) not null,
    movie_runtime       int not null,
    movie_title         varchar(100) not null unique,
    movie_poster        varchar(100) not null,
    movie_Writers       varchar(80) not null,
    movie_directors     varchar(80) not null,
    movie_actors        varchar(300) not null,
    movie_plot_simple   varchar(1000) not null,
    movie_country       varchar(20) not null,
    movie_also_known_as varchar(100),
    primary key(movie_id)
);



/*========================================================================*/
/* Table:movie hall   放映厅表                                            */
/*========================================================================*/
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

/*========================================================================*/
/* Table: Seat座位表                                             */
/*========================================================================*/
create table Seat
(
    seat_id      int not null auto_increment,
    hall_id      int,
    seat_row     int,
    seat_col     int,
    seat_status  tinyint comment '1:有座
                                  0:无座位/损坏',
    primary key (seat_id)
);


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
    primary key (schedule_id)
);


/*========================================================================*/
/* Table: Ticket票表                                                */
/*========================================================================*/
create table Ticket
(
    ticket_id      bigint not null auto_increment,
    seat_id        int,
    schedule_id    int,
    ticket_status  tinyint comment '1:已购
                                    0:未购',
    primary key (ticket_id)
);


/*========================================================================*/
/* Table:  Order订单表                                              */
/*========================================================================*/
create table Orders
(
    orders_id       int not null auto_increment,
    user_id        int,
    ticket_id      bigint,
    orders_time     datetime not null,
    orders_type     tinyint comment '1:购买订单
                                    0:退款订单',
    primary key (orders_id)
);



/*========================================================================*/
/* Table: Salestatistics 票房表                                              */
/*========================================================================*/
create table Salestatistics
(
    sales_statistics_id    int not null auto_increment,
    movie_id               int,
    sale_count             bigint,
    sale_money_count       numeric(10,2),
    primary key (Sales_statistics_id)
);

alter table Seat add constraint FK_hall_seat foreign key(hall_id)
    references Hall(hall_id) on delete restrict on update restrict;

alter table Schedule add constraint FK_schedule_hall foreign key(hall_id)
    references Hall(hall_id) on delete restrict on update restrict;

alter table Schedule add constraint FK_schedule_movie foreign key(movie_id)
    references Movie(movie_id) on delete restrict on update restrict;

alter table Orders add constraint FK_order_user foreign key(user_id)
    references User(user_id) on delete restrict on update restrict;

alter table Orders add constraint FK_order_ticket foreign key(ticket_id)
    references Ticket(ticket_id) on delete restrict on update restrict;

alter table Ticket add constraint FK_ticket_seat foreign key(seat_id)
    references Seat(seat_id) on delete restrict on update restrict;

alter table Ticket add constraint FK_ticket_schedule foreign key(schedule_id)
    references Schedule(schedule_id) on delete restrict on update restrict;

alter table Salestatistics add constraint FK_Salestatistics_movie foreign key(movie_id)
    references Movie(movie_id) on delete restrict on update restrict;

