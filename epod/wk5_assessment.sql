drop database if exists jlogistics;

create database jlogistics;

use jlogistics;

-- delivery 
-- one for every package
create table delivery (
	pkg_id mediumint not null auto_increment,
	-- receiver's name
	name varchar(256) not null,
	-- receiver's address
	address varchar(256) not null,
	-- receiver's phone number
	phone varchar(24) not null,
	-- when this record is created
	create_date timestamp,
	primary key(pkg_id)
);

-- pod table
-- each delivery will have one entry
create table pod (
	pod_id mediumint not null auto_increment,
	pkg_id mediumint not null,
	note text, 
	-- image of package on delivery
	image blob,
	delivery_date timestamp,
	-- acknowledgement id received from HQ
	ack_id varchar(16),
	primary key(pod_id),
	constraint fk_pkg_id foreign key (pkg_id) references delivery(pkg_id)
);
