-- Start of DDL Script for Table MSB_ESB.ESB_ERRDEF
-- Generated 13-Nov-2012 15:48:25 from MSB_ESB@ORCL

CREATE TABLE esb_errdef
    (autoid                         NUMBER NOT NULL,
    src_err_num                    VARCHAR2(50 BYTE) NOT NULL,
    src_err_desc                   VARCHAR2(250 BYTE),
    des_err_num                    VARCHAR2(50 BYTE) NOT NULL,
    des_err_desc                   VARCHAR2(250 BYTE))
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  NOCACHE
  MONITORING
  NOPARALLEL
  LOGGING
/





-- Constraints for ESB_ERRDEF

ALTER TABLE esb_errdef
ADD CONSTRAINT errdef_pk PRIMARY KEY (autoid)
USING INDEX
  PCTFREE     10
  INITRANS    2
  MAXTRANS    255
  TABLESPACE  users
/


-- Comments for ESB_ERRDEF

COMMENT ON COLUMN esb_errdef.autoid IS 'So tu tang'
/
COMMENT ON COLUMN esb_errdef.des_err_desc IS 'Mo ta loi tra ve cho chuong trinh goi den ESB'
/
COMMENT ON COLUMN esb_errdef.des_err_num IS 'Ma loi tra ve cho chuong trinh goi den ESB'
/
COMMENT ON COLUMN esb_errdef.src_err_desc IS 'Mo ta loi tra ve tu chuong trinh duoc goi'
/
COMMENT ON COLUMN esb_errdef.src_err_num IS 'Ma loi tra ve tu chuong trinh duoc goi'
/

-- End of DDL Script for Table MSB_ESB.ESB_ERRDEF

