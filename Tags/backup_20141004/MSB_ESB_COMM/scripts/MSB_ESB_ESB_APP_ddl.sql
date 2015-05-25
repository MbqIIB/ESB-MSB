-- Start of DDL Script for Table MSB_ESB.ESB_APP
-- Generated 13-Nov-2012 15:48:14 from MSB_ESB@ORCL

CREATE TABLE esb_app
    (app_id                         VARCHAR2(5 BYTE) NOT NULL,
    app_name                       VARCHAR2(200 BYTE))
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  NOCACHE
  MONITORING
  NOPARALLEL
  LOGGING
/





-- Comments for ESB_APP

COMMENT ON COLUMN esb_app.app_id IS 'Ma ung dung'
/
COMMENT ON COLUMN esb_app.app_name IS 'Ten(mo ta) ung dung'
/

-- End of DDL Script for Table MSB_ESB.ESB_APP

