-- Start of DDL Script for Table MSB_ESB.ESB_SEQMGR
-- Generated 13-Nov-2012 15:48:49 from MSB_ESB@ORCL

CREATE TABLE esb_seqmgr
    (autoid                         NUMBER,
    version                        VARCHAR2(5 BYTE),
    param1                         VARCHAR2(30 BYTE),
    param2                         VARCHAR2(30 BYTE),
    seq_limit                      NUMBER,
    seq_number                     NUMBER,
    branch_code                    VARCHAR2(5 BYTE),
    group_code                     VARCHAR2(5 BYTE),
    ccy_code                       VARCHAR2(3 BYTE))
  PCTFREE     10
  INITRANS    1
  MAXTRANS    255
  TABLESPACE  users
  NOCACHE
  MONITORING
  NOPARALLEL
  LOGGING
/





-- End of DDL Script for Table MSB_ESB.ESB_SEQMGR

