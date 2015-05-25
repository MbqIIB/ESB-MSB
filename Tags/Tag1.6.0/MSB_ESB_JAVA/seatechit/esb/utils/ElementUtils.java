package seatechit.esb.utils;

import java.util.Date;
import java.util.List;

import com.ibm.broker.plugin.MbDate;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbTime;
import com.ibm.broker.plugin.MbTimestamp;
import com.ibm.broker.plugin.MbXML;
import com.ibm.broker.plugin.MbXMLNS;
import com.ibm.broker.plugin.MbXMLNSC;

/**
 * @author trungkien
 * 
 */
public class ElementUtils {

	/**
	 * 
	 * @param inElm
	 * @param inXpath
	 * @param outElm
	 * @param outXpath
	 * @return
	 * @throws MbException
	 */
	public static Object copyValue(MbElement inElm, String inXpath, MbElement outElm, String outXpath) throws MbException {
		Object value = inElm.getFirstElementByPath(inXpath).getValue();

		outElm.getFirstElementByPath(outXpath).setValue(value);

		return value;
	}

	/**
	 * 
	 * @param outElm
	 * @param outXpath
	 * @param value
	 * @throws MbException
	 */
	public static void setValue(MbElement outElm, String outXpath, Object value) throws MbException {
		outElm.getFirstElementByPath(outXpath).setValue(value);
	}

	/**
	 * 
	 * @param inElm
	 * @param inXpath
	 * @return
	 * @throws MbException
	 */
	public static Object getValue(MbElement inElm, String inXpath) throws MbException {
		return inElm.getFirstElementByPath(inXpath).getValue();
	}

	/**
	 * 
	 * @param elm
	 * @return
	 * @throws MbException
	 */
	public static boolean isMRM(MbElement elm) throws MbException {
		return elm.getParserClassName().toUpperCase().equals("MRM");
	}

	/**
	 * 
	 * @param elm
	 * @return
	 * @throws MbException
	 */
	public static boolean isXML(MbElement elm) throws MbException {
		return elm.getParserClassName().toUpperCase().equals(MbXML.PARSER_NAME);
	}

	/**
	 * 
	 * @param elm
	 * @return
	 * @throws MbException
	 */
	public static boolean isXMLNS(MbElement elm) throws MbException {
		return elm.getParserClassName().toUpperCase().equals(MbXMLNS.PARSER_NAME);
	}

	/**
	 * 
	 * @param elm
	 * @return
	 * @throws MbException
	 */
	public static boolean isXMLNSC(MbElement elm) throws MbException {
		return elm.getParserClassName().toUpperCase().equals(MbXMLNSC.PARSER_NAME);
	}

	/**
	 * 
	 * @param xmlContent
	 * @return
	 */
	public static MbMessage createMbMessageFromString(String xmlContent) {
		MbMessage outMessage = null;
		try {
			outMessage = new MbMessage();
			outMessage.getRootElement().createElementAsLastChildFromBitstream(xmlContent.getBytes(), MbXMLNS.PARSER_NAME, null, null, null, 0, 0, 0);
		} catch (MbException e) {
			e.printStackTrace();
		}
		return outMessage;
	}

	/**
	 * 
	 * @param inMessage
	 * @param xpath
	 * @return
	 */
	public static String getXMLElementInStringValue(final MbMessage inMessage, final String xpath) {
		String resValue = "";
		try {
			resValue = (String) inMessage.evaluateXPath("string(" + xpath + ")");
		} catch (MbException e) {
			resValue = "";
		}
		return resValue.trim();
	}

	public static double getXMLElementInNumericValue(final MbMessage inMessage, final String xpath) {
		Object resValue = new Object();
		try {
			resValue = (String) inMessage.evaluateXPath("string(" + xpath + ")");
			if (resValue == null) {
				return 0;
			} else if (resValue instanceof Double) {
				return ((Double) resValue).doubleValue();
			} else {
				return Double.parseDouble(resValue.toString());
			}
		} catch (MbException e) {
			throw new ClassCastException("Type can not be cast to a double type: " + resValue.getClass());
		}
	}

	/**
	 * Gets the value as a date
	 * 
	 * @return Element value as a date
	 * @throws MbException
	 */
	public Date getXMLElementInDateValue(final MbMessage inMessage, final String xpath) throws MbException {
		Object resValue = (String) inMessage.evaluateXPath("string(" + xpath + ")");
		if (resValue == null) {
			return null;
		} else if (resValue instanceof MbTimestamp) {
			// return ((MbTimestamp) getValue()).getTime();
		} else if (resValue instanceof MbDate) {
			// return ((MbDate) getValue()).getTime();
		} else if (resValue instanceof MbTime) {
			// return ((MbTime) getValue()).getTime();
		} else {
			throw new ClassCastException("Type can not be cast to a date type: " + resValue.getClass());
		}
		return null;
	}

	/**
	 * 
	 * @param inMessage
	 * @param xpath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static MbElement getXMLElement(final MbMessage inMessage, final String xpath) {
		List<MbElement> retArrElement = null;
		try {
			retArrElement = (List<MbElement>) inMessage.evaluateXPath(xpath);
			if (retArrElement.size() > 0)
				return retArrElement.get(0);
		} catch (MbException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param inMessage
	 * @param xpath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<MbElement> getListOfXMLElement(final MbMessage inMessage, final String xpath) {
		List<MbElement> retArrElement = null;
		try {
			retArrElement = (List<MbElement>) inMessage.evaluateXPath(xpath);
		} catch (MbException e) {
			e.printStackTrace();
		}
		return retArrElement;
	}

	/**
	 * Set value for MbMessage element.
	 * 
	 * @param inMessage
	 * @param xpath
	 * @param args
	 */
	public static void setXMLElementValue(MbMessage inMessage, String xpath, Object... args) {
		try {			
			inMessage.evaluateXPath(String.format(xpath, args));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String ReplaceCorebankSpecChar(String input) {
		if (input != null) {
			input = input.replace("$", "");
			input = input.replace("'", "");
			//input = input.replace("/", "");
			input = input.replace("\\", "");
			input = input.replace("#", "");
			input = input.replace("~", "");
			input = input.replace("`", "");
			input = input.replace("^", "");
			input = input.replace("'", "");
			input = input.replace("\\<", "");
			input = input.replace("<", "");
			input = input.replace("\\>", "");
			input = input.replace(">", "");
			return input;
		} else {
			return "";
		}
	}

	/**
	 * Get XML content of MbMessage element.
	 * 
	 * @param inMessage
	 * @return
	 */
	public static String getXMLContent(final MbMessage inMessage) {
		String strXMLContent = "";
		byte[] msgByteStreamIn = null;
		try {
			MbMessage outMessage = new MbMessage(inMessage);
			MbElement msgBody = outMessage.getRootElement().getLastChild();
			msgBody.detach();//
			msgByteStreamIn = msgBody.toBitstream("", "", "", 0, 0, 0);
			strXMLContent = new String(msgByteStreamIn);
		} catch (MbException ex) {
			ex.printStackTrace();
		}
		return strXMLContent;
	}
}
