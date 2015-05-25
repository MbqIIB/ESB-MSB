-- Start of DDL Script for Table MSB_ESB.ESB_TLLOG
-- Generated 13-Nov-2012 15:48:59 from MSB_ESB@ORCL

CREATE TABLE esb_tllog
    (message_sn                     VARCHAR2(20 BYTE),
    tran_code                      VARCHAR2(5 BYTE),
    sender_id                      VARCHAR2(5 BYTE),
    req_date                       VARCHAR2(10 BYTE),
    req_time                       VARCHAR2(8 BYTE),
    receiver_id                    VARCHAR2(5 BYTE),
    receive_date                   VARCHAR2(10 BYTE),
    receive_time                   VARCHAR2(8 BYTE),
    resp_date                      VARCHAR2(10 BYTE),
    resp_time                      VARCHAR2(8 BYTE),
    resp_code                      VARCHAR2(3 BYTE),
    resp_msg                       VARCHAR2(250 BYTE),
    in_xml_msg                     CLOB,
    out_xml_msg                    CLOB)
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
  LOB ("IN_XML_MSG") STORE AS SYS_LOB0000075834C00013$$
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
  LOB ("OUT_XML_MSG") STORE AS SYS_LOB0000075834C00014$$
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





-- Comments for ESB_TLLOG

COMMENT ON COLUMN esb_tllog.in_xml_msg IS 'Message input'
/
COMMENT ON COLUMN esb_tllog.message_sn IS 'So sn cua message'
/
COMMENT ON COLUMN esb_tllog.out_xml_msg IS 'Message output'
/
COMMENT ON COLUMN esb_tllog.receive_date IS 'Ngay nhan'
/
COMMENT ON COLUMN esb_tllog.receive_time IS 'Gio nhan'
/
COMMENT ON COLUMN esb_tllog.receiver_id IS 'Ma ung dung nhan'
/
COMMENT ON COLUMN esb_tllog.req_date IS 'Ngay gui'
/
COMMENT ON COLUMN esb_tllog.req_time IS 'Gio gui'
/
COMMENT ON COLUMN esb_tllog.resp_code IS 'Ma loi tra ve'
/
COMMENT ON COLUMN esb_tllog.resp_date IS 'Ngay phan hoi'
/
COMMENT ON COLUMN esb_tllog.resp_msg IS 'Mo ta loi tra ve'
/
COMMENT ON COLUMN esb_tllog.resp_time IS 'Gio phan hoi'
/
COMMENT ON COLUMN esb_tllog.sender_id IS 'Ma ung dung gui'
/
COMMENT ON COLUMN esb_tllog.tran_code IS 'Ma giao dich'
/

-- End of DDL Script for Table MSB_ESB.ESB_TLLOG

