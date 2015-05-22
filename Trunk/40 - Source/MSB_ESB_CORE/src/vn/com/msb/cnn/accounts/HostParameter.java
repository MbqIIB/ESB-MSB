package vn.com.msb.cnn.accounts;

public class HostParameter {

	private String name;
	private String value;
	private String param1;
	private String param2;

	public HostParameter() {
	}

	public HostParameter(String name, String value, String param1, String param2) {
		super();
		this.name = name;
		this.value = value;
		this.param1 = param1;
		this.param2 = param2;
	}

	// public static HostParameter findByNameAndValue(String port_number,
	// String refChannel) {
	// // HostParameter retHostParameter = null;
	// // ArrayList<String> arrOfChannelInfo = new ArrayList<String>();
	// // //arrOfChannelInfo.add("9600");
	// // arrOfChannelInfo.add("9800");
	// // if (port_number.equals("port_number")) {
	// // int id = (int) (Math.random() * arrOfChannelInfo.size());
	// // String port = "9800";//(String) arrOfChannelInfo.get(id);
	// // retHostParameter = new HostParameter("port_number", "SMSBK01",
	// // port, "");
	// // }
	// // return retHostParameter;
	//
	// HostParameter retHostParameter = null;
	// if (port_number.equals("port_number")) {
	// retHostParameter = new HostParameter("port_number", "SMSBK01", "9600",
	// "");
	// }
	// return retHostParameter;
	// }

	public static HostParameter findByName(String findByName) {
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam2() {
		return param2;
	}

}
