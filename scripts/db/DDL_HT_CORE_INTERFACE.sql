create table T_CORE_SERVICE_TRANS_REQUEST
(
  trans_request_id NUMBER(12) not null,
  trans_req_path   VARCHAR2(100),
  trans_process_type     VARCHAR2(10),
  trans_req_time   DATE,
  trans_res_time   DATE,
  trans_status     NUMBER(1) default -1,
  trans_result     NUMBER(1) default -1,
  elapsed_time     NUMBER(8),
  req_message_id   NUMBER(12),
  res_message_id   NUMBER(12),
  is_duplicated    CHAR(1) default 'N',
  client_code      VARCHAR2(10),
  client_req_no    VARCHAR2(40),
  client_req_time  DATE,
  client_ip_port   VARCHAR2(40),
  app_ip_port      VARCHAR2(40),
  app_name         VARCHAR2(40),
  error_id         NUMBER(12),
  policy_code      VARCHAR2(20),
  apply_code       VARCHAR2(20),
  insert_time      DATE default SYSDATE not null
);
-- Add comments to the table 
comment on table T_CORE_SERVICE_TRANS_REQUEST is '核心接口交易记录表';
-- Add comments to the columns 
comment on column T_CORE_SERVICE_TRANS_REQUEST.trans_req_path is '交易路径(交易类型)';
comment on column T_CORE_SERVICE_TRANS_REQUEST.trans_process_type is '交易流程类型';
comment on column T_CORE_SERVICE_TRANS_REQUEST.trans_status is '交易完成状态(1-finish,0-error)';
comment on column T_CORE_SERVICE_TRANS_REQUEST.trans_result is '交易成功状态(1-success,0-error)';
-- Create/Recreate indexes 
create index IDX_CORE_SERVICE_TRANS_REQ_CN on T_CORE_SERVICE_TRANS_REQUEST (CLIENT_REQ_NO);
create index IDX_CORE_SERVICE_TRANS_REQ_C_C on T_CORE_SERVICE_TRANS_REQUEST (CLIENT_CODE, CLIENT_REQ_TIME);
create index IDX_CORE_SERVICE_TRANS_REQ_T_C on T_CORE_SERVICE_TRANS_REQUEST (TRANS_REQ_PATH, CLIENT_CODE);
create index IDX_CORE_SERVICE_TRANS_REQ_IT on T_CORE_SERVICE_TRANS_REQUEST (INSERT_TIME);
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CORE_SERVICE_TRANS_REQUEST add constraint PK_CORE_SERVICE_TRANS_REQ_ID primary key (TRANS_REQUEST_ID);
create sequence S_CORE_SERVICE_TRANS_REQ__ID;

create table T_CORE_SERVICE_TRANS_CLOB
(
  clob_id     NUMBER(12) not null,
  create_date DATE default SYSDATE not null,
  content     CLOB
);
comment on table T_CORE_SERVICE_TRANS_CLOB is '核心接口交易CLOB存储表';
-- Add comments to the columns 
comment on column T_CORE_SERVICE_TRANS_CLOB.clob_id is '流水号';
comment on column T_CORE_SERVICE_TRANS_CLOB.create_date  is '记录创建日期';
comment on column T_CORE_SERVICE_TRANS_CLOB.content  is '文本数据内容';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CORE_SERVICE_TRANS_CLOB add constraint PK_CORE_SERVICE_TRANS_CLOB primary key (CLOB_ID);
create sequence S_CORE_SERVICE_TRANS_CLOB__ID;

-- Create table
create table TEST_PERSON
(
  id            NUMBER(10) not null,
  name          VARCHAR2(50) not null,
  age           NUMBER(3),
  sex           CHAR(1),
  note          VARCHAR2(100),
  insert_time   DATE default SYSDATE not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table TEST_PERSON add constraint PK_TEST_PERSON primary key (ID);
create sequence S_TEST_PERSON__ID;