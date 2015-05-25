-- Start of DDL Script for Table MSB_ESB.ESB_PARAM
-- Generated 13-Nov-2012 15:48:39 from MSB_ESB@ORCL

CREATE TABLE esb_param
    (grp_name                       VARCHAR2(5 BYTE),
    p_name                         VARCHAR2(150 BYTE) ,
    p_value                        VARCHAR2(200 BYTE) NOT NULL,
    p_desc                         VARCHAR2(300 BYTE),
    lstord                         NUMBER)
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  NOCACHE
  MONITORING
  NOPARALLEL
  LOGGING
/





-- Constraints for ESB_PARAM

ALTER TABLE esb_param
ADD CONSTRAINT param_pk PRIMARY KEY (p_name)
USING INDEX
  PCTFREE     10
  INITRANS    2
  MAXTRANS    255
  TABLESPACE  users
/

ALTER TABLE esb_param
ADD CHECK ("P_NAME" IS NOT NULL)
/


-- Comments for ESB_PARAM

COMMENT ON COLUMN esb_param.grp_name IS 'Nhom tham so'
/
COMMENT ON COLUMN esb_param.lstord IS 'Thu tu hien thi'
/
COMMENT ON COLUMN esb_param.p_desc IS 'Mo ta tham so'
/
COMMENT ON COLUMN esb_param.p_name IS 'Ten tham so'
/
COMMENT ON COLUMN esb_param.p_value IS 'Gia tri tham so'
/

-- End of DDL Script for Table MSB_ESB.ESB_PARAM

