package seatechit.msbgateway.dbaccess.dao;
import seatechit.msbgateway.dbaccess.dao.impl.FDInfoDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.AftInfoDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.AccountInfoDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.BankStaffInfoDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.ChannelInfoDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.CurrencyDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.CustomerInfoDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.HostTranCodeInfoDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.LogMessageDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.SalaryDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.ServiceInfoDAOImpl;
import seatechit.msbgateway.dbaccess.dao.impl.SysParamDAOImpl;

public class DAOFactory {
	public DAOFactory() {

	}

	public SysParamDAO getSysParamDAO() {
		return new SysParamDAOImpl();
	}

	public LogMessageDAO getLogMessageDAO() {
		return new LogMessageDAOImpl();
	}

	public SalaryDAO getSalaryDAO() {
		return new SalaryDAOImpl();
	}

	public ChannelInfoDAO getChannelInfoDAO() {
		return new ChannelInfoDAOImpl();
	}

	public ServiceInfoDAO getServiceInfoDAO() {
		return new ServiceInfoDAOImpl();
	}

	public CurrencyDAO getCurrencyInfoFacade() {
		return new CurrencyDAOImpl();
	}

	public HostTranCodeInfoDAO getHostTranCodeInfoDAO() {
		return new HostTranCodeInfoDAOImpl();
	}

	public BankStaffInfoDAO getBankStaffInfoDAO() {
		return new BankStaffInfoDAOImpl();
	}

	public CustomerInfoDAO getCustomerInfoDAO() {
		return new CustomerInfoDAOImpl();
	}

	public AccountInfoDAO getAccountInfoDAO() {
		return new AccountInfoDAOImpl();
	}
	public AftInfoDAO getAftInfoDAO() {
		return new AftInfoDAOImpl();
	}
	public FDInfoDAO getFDInfoDAO() {
		return new FDInfoDAOImpl();
	}
}
