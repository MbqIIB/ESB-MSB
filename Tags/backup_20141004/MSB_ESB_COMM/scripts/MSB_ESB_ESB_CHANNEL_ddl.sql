-- Start of DDL Script for Table MSB_ESB.ESB_CHANNEL
-- Generated 13-Nov-2012 15:48:20 from MSB_ESB@ORCL

CREATE TABLE esb_channel
    (channel_id                     VARCHAR2(10 BYTE),
    channel_name                   VARCHAR2(100 BYTE),
    ref_port                       NUMBER,
    status                         VARCHAR2(1 BYTE))
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  NOCACHE
  MONITORING
  NOPARALLEL
  LOGGING
/





-- End of DDL Script for Table MSB_ESB.ESB_CHANNEL

