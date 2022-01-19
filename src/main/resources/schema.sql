create table Node (
	external_id varchar(20) NOT NULL
);

create table RDB_VIEW_XVU_MEDIAROOMS (
	tn varchar(20) NOT NULL,
	mediaroom_id varchar(20) NOT NULL,
	node_name varchar(100) NOT NULL,
	node_slot int NOT NULL
);