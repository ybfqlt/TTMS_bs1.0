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
/* Table:movie hall                                               */
/*========================================================================*/
create table Hall
(
    hall_id           int not null auto_increment,
    hall_name         varchar(20) unique not null,
    hall_seat_count   int not null,
    hall_seat_row     int not null,
    hall_seat_col     int not null,
    hall_status       smallint comment '0:可以使用，目前处于未使用状态
                                        1:目前处于使用状态
                                       -1:目前不可用，处于维修状态',
    primary key (hall_id)
);

/*========================================================================*/
/* Table:                                                */
/*========================================================================*/

/*========================================================================*/
/* Table:                                                */
/*========================================================================*/

/*========================================================================*/
/* Table:                                                */
/*========================================================================*/

/*========================================================================*/
/* Table:                                                */
/*========================================================================*/

/*========================================================================*/
/* Table:                                                */
/*========================================================================*/

/*========================================================================*/
/* Table:                                                */
/*========================================================================*/