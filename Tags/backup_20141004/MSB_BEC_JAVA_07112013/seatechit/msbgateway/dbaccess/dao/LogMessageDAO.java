package seatechit.msbgateway.dbaccess.dao;

import seatechit.msbgateway.dbaccess.entity.LogMessage;

//import seatechit.msbgateway.processor.LogMessage;

public interface LogMessageDAO {

	abstract int insertLogMessage(LogMessage logMsg);

}
