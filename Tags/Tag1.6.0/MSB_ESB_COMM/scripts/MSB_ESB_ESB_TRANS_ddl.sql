-- Start of DDL Script for Table MSB_ESB.ESB_TRANS
-- Generated 13-Nov-2012 15:49:04 from MSB_ESB@ORCL

CREATE TABLE esb_trans
    (tran_code                      VARCHAR2(5 BYTE) NOT NULL,
    tran_name                      VARCHAR2(200 BYTE),
    tran_type                      VARCHAR2(1 BYTE),
    app_id                         VARCHAR2(5 BYTE))
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  NOCACHE
  MONITORING
  NOPARALLEL
  LOGGING
/





-- Constraints for ESB_TRANS

ALTER TABLE esb_trans
ADD CONSTRAINT trans_pk PRIMARY KEY (tran_code)
USING INDEX
  PCTFREE     10
  INITRANS    2
  MAXTRANS    255
  TABLESPACE  users
/


-- Comments for ESB_TRANS

COMMENT ON COLUMN esb_trans.app_id IS 'Ung dung goi'
/
COMMENT ON COLUMN esb_trans.tran_code IS 'Ma giao dich'
/
COMMENT ON COLUMN esb_trans.tran_name IS 'Ten,mo ta ung dung'
/
COMMENT ON COLUMN esb_trans.tran_type IS 'Loai giao dich'
/

-- End of DDL Script for Table MSB_ESB.ESB_TRANS

