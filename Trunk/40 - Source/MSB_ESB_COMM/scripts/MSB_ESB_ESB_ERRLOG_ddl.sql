-- Start of DDL Script for Table MSB_ESB.ESB_ERRLOG
-- Generated 13-Nov-2012 15:48:31 from MSB_ESB@ORCL

CREATE TABLE esb_errlog
    (auto_id                        VARCHAR2(20 BYTE),
    log_date                       DATE,
    err_code                       NUMBER,
    err_msg                        VARCHAR2(250 BYTE))
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
  NOPARALLEL
  LOGGING
/





-- Comments for ESB_ERRLOG

COMMENT ON COLUMN esb_errlog.err_msg IS 'Mo ta loi'
/
COMMENT ON COLUMN esb_errlog.log_date IS 'Ngay ghi log loi'
/

-- End of DDL Script for Table MSB_ESB.ESB_ERRLOG

