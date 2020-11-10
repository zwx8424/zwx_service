DROP TABLE IF EXISTS `T_TRANS_REQUEST`;
CREATE TABLE T_TRANS_REQUEST
(
  trans_request_id 	BIGINT(12) NOT NULL AUTO_INCREMENT primary key COMMENT '主键ID',
  trans_req_path   	VARCHAR(100) COMMENT '交易路径(交易类型)',
  trans_process_type	VARCHAR(10) COMMENT '交易流程类型',
  trans_req_time   	TIMESTAMP COMMENT '交易请求时间',
  trans_res_time   	TIMESTAMP COMMENT '交易返回时间',
  trans_status     	SMALLINT(1) DEFAULT -1 COMMENT '交易完成状态',
  trans_result     	SMALLINT(1) DEFAULT -1 COMMENT '交易结果',
  elapsed_time     	INT(8) COMMENT '经过时间',
  req_message_id   	BIGINT(12) COMMENT '请求报文ID',
  res_message_id   	BIGINT(12) COMMENT '返回报文ID',
  is_duplicated    	CHAR(1) DEFAULT 'N' COMMENT '是否重复交易',
  client_code      	VARCHAR(10) COMMENT '客户代码',
  client_req_no    	VARCHAR(40) COMMENT '客户交易号',
  client_req_time  	DATETIME COMMENT '客户交易时间',
  client_ip_port   	VARCHAR(40) COMMENT '客户端IP',
  app_ip_port      	VARCHAR(40) COMMENT '处理服务端IP',
  app_name         	VARCHAR(40) COMMENT '处理服务端名称',
  error_id         	BIGINT(12) COMMENT '异常日志ID',
  para_1      		VARCHAR(20) COMMENT '参数1',
  para_2       		VARCHAR(20) COMMENT '参数2',
  insert_time      	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '主键ID'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='交易记录表';
-- Create/Recreate indexes 
CREATE INDEX IDX_TRANS_REQ_CN ON T_TRANS_REQUEST (CLIENT_REQ_NO);
CREATE INDEX IDX_TRANS_REQ_C_C ON T_TRANS_REQUEST (CLIENT_CODE, CLIENT_REQ_TIME);
CREATE INDEX IDX_TRANS_REQ_T_C ON T_TRANS_REQUEST (TRANS_REQ_PATH, CLIENT_CODE);
CREATE INDEX IDX_TRANS_REQ_IT ON T_TRANS_REQUEST (INSERT_TIME);


DROP TABLE IF EXISTS `T_TRANS_CLOB`;
CREATE TABLE T_TRANS_CLOB
(
  clob_id     BIGINT(12) NOT NULL AUTO_INCREMENT primary key COMMENT '主键ID',
  create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建日期',
  content     LONGTEXT COMMENT '文本数据内容'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='交易文本存储表';


DROP TABLE IF EXISTS `TEST_PERSON`;
-- Create table
CREATE TABLE TEST_PERSON
(
  id            BIGINT(10) NOT NULL,
  NAME          VARCHAR(50) NOT NULL,
  age           SMALLINT(3),
  sex           CHAR(1),
  note          VARCHAR(100),
  insert_time   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建日期',
  PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='测试表';