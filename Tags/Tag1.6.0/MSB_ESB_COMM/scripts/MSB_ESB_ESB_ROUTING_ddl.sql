-- Start of DDL Script for Table MSB_ESB.ESB_ROUTING
-- Generated 13-Nov-2012 15:48:44 from MSB_ESB@ORCL

CREATE TABLE esb_routing
    (tran_code                      VARCHAR2(5 BYTE),
    sender_id                      VARCHAR2(5 BYTE),
    receiver_id                    VARCHAR2(5 BYTE),
    qmgr_name                      VARCHAR2(50 BYTE),
    queue_name                     VARCHAR2(200 BYTE))
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





-- Comments for ESB_ROUTING

COMMENT ON COLUMN esb_routing.qmgr_name IS 'Ten queue manager se duoc routing den'
/
COMMENT ON COLUMN esb_routing.queue_name IS 'Ten queue se duoc routing den'
/
COMMENT ON COLUMN esb_routing.receiver_id IS 'Ma ung dung bi goi'
/
COMMENT ON COLUMN esb_routing.sender_id IS 'Ma ung dung goi'
/

-- End of DDL Script for Table MSB_ESB.ESB_ROUTING

