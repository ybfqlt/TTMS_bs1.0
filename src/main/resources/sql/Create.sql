drop DATABASE IF EXISTS TTMS;

create DATABASE TTMS;

use TTMS;

/*========================================================================*/
/* Table: User用户表                                               */
/*========================================================================*/
create table User
(
    user_id       int not null auto_increment,
    user_name     char(20) not null unique,
    user_password char(50) not null,
    user_type     char(2) not null comment 'u:普通用户 g:管理员 j:经理',
    user_qq       char(11) not null,
    primary key(user_id)
);



/*========================================================================*/
/* Table:Studio放映厅                                               */
/*========================================================================*/
create table Studio
(
    studio_id      int not null auto_increment,
    studio_name    char(10) unique not null,
    studio_seat    int not null,
    studio_row     int not null,
    studio_col     int not null,
    studio_status  smallint comment '0:可以使用，目前处于未使用状态
                                     1:目前处于使用状态
                                    -1:目前不可用，处于维修状态',
    primary key (studio_id)
);



/*========================================================================*/
/* Table:                                                */
/*========================================================================*/