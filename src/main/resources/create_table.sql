--文件表
create table t_file(
  file_id bigint not null auto_increment  comment '文件ID',
  user_id bigint not null comment '用户id',
  folder_id bigint not null comment '文件夹id',
  file_path varchar(100) not null comment '文件路径',
  file_name varchar(50) not null comment '文件名称',
  file_size varchar(50) comment '文件大小',
  file_type varchar(50) comment '文件类型',
  upload_user varchar(50) comment '文件上传人姓名',
  upload_date timestamp not null comment '上传时间',
  delete_flag int not null comment '删除标记:0正常,1删除',
  primary key (file_id)
)engine=InnoDB auto_increment=1 default charset=utf8 comment '文件表';

--用户表
create table t_user(
  userId bigint not null auto_increment comment '用户id',
  staffId varchar(25) not null comment '登录使用',
  userName varchar(50) comment '用户名称',
  passWord varchar(50) not null comment '密码',
  primary key (userId)
)engine=InnoDB auto_increment=2 default charset=utf8 comment '用户表';

--文件夹表
create table t_folder(
  folder_id bigint not null auto_increment comment '文件夹id',
  folder_name varchar(50) not null comment '文件夹名称',
  create_date timestamp not null comment '创建时间',
  create_user varchar(50) comment '创建人姓名',
  primary key (folder_id)
)engine=InnoDB auto_increment=1000 default charset=utf8 comment '文件夹表';