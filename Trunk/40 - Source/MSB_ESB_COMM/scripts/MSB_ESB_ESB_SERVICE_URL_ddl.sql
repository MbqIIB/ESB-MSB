-- Start of DDL Script for Table MSB_ESB.ESB_SERVICE_URL
-- Generated 13-Nov-2012 15:48:54 from MSB_ESB@ORCL

CREATE TABLE esb_service_url
    (service_id                     VARCHAR2(10 BYTE) NOT NULL,
    service_type                   VARCHAR2(5 BYTE),
    service_name                   VARCHAR2(250 BYTE),
    service_url                    VARCHAR2(300 BYTE),
    service_timeout                NUMBER)
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





-- Comments for ESB_SERVICE_URL

COMMENT ON COLUMN esb_service_url.service_id IS 'Ma dich vu'
/
COMMENT ON COLUMN esb_service_url.service_name IS 'Mo ta dich vuj'
/
COMMENT ON COLUMN esb_service_url.service_type IS 'Loai dich vu'
/
COMMENT ON COLUMN esb_service_url.service_url IS 'Chuoi ket noi'
/

-- End of DDL Script for Table MSB_ESB.ESB_SERVICE_URL

