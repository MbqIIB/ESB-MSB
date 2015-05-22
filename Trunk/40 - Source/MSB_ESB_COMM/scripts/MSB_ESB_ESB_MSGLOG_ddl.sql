-- Start of DDL Script for Table MSB_ESB.ESB_MSGLOG
-- Generated 13-Nov-2012 15:48:35 from MSB_ESB@ORCL

CREATE TABLE esb_msglog
    (message_sn                     VARCHAR2(20 BYTE),
    tran_code                      VARCHAR2(20 BYTE),
    sender_id                      VARCHAR2(20 BYTE),
    tran_sn                        VARCHAR2(20 BYTE),
    tran_date                      VARCHAR2(10 BYTE),
    receive_date                   VARCHAR2(10 BYTE),
    receive_time                   VARCHAR2(8 BYTE),
    req_date                       VARCHAR2(20 BYTE),
    req_time                       VARCHAR2(20 BYTE),
    rsp_date                       VARCHAR2(20 BYTE),
    rsp_time                       VARCHAR2(20 BYTE),
    resp_code                      VARCHAR2(20 BYTE),
    resp_msg                       VARCHAR2(200 BYTE),
    in_message                     CLOB,
    out_message                    CLOB,
    teller_id                      VARCHAR2(10 BYTE),
    approver_id                    VARCHAR2(10 BYTE),
    ref_app_id                     VARCHAR2(10 BYTE),
    ref_service                    VARCHAR2(10 BYTE),
    ref_channel                    VARCHAR2(10 BYTE),
    ref_port                       VARCHAR2(10 BYTE),
    ref_tran_code                  VARCHAR2(10 BYTE),
    ref_cif_acct                   VARCHAR2(20 BYTE),
    ref_amt                        VARCHAR2(20 BYTE))
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  STORAGE   (
    INITIAL     65536
    NEXT        1048576
    MINEXTENTS  1
    MAXEXTENTS  2147483645
  )
  NOCACHE
  MONITORING
  LOB ("IN_MESSAGE") STORE AS SYS_LOB0000076042C00014$$
  (
  TABLESPACE  users
  STORAGE   (
    INITIAL     65536
    NEXT        1048576
    MINEXTENTS  1
    MAXEXTENTS  2147483645
  )
   NOCACHE LOGGING
   CHUNK 8192
  )
  LOB ("OUT_MESSAGE") STORE AS SYS_LOB0000076042C00015$$
  (
  TABLESPACE  users
  STORAGE   (
    INITIAL     65536
    NEXT        1048576
    MINEXTENTS  1
    MAXEXTENTS  2147483645
  )
   NOCACHE LOGGING
   CHUNK 8192
  )
  NOPARALLEL
  LOGGING
/





-- End of DDL Script for Table MSB_ESB.ESB_MSGLOG

