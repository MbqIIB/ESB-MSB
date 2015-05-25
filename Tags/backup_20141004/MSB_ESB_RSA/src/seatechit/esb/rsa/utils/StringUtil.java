/*     */package seatechit.esb.rsa.utils;
/*     */
/*     */import java.lang.reflect.Field; /*     */
import java.lang.reflect.Method; /*     */
import java.util.ArrayList; /*     */
import java.util.Iterator; /*     */
import java.util.List; /*     */
import java.util.Map; /*     */
import java.util.Set; /*     */
import java.util.StringTokenizer; /*     */
import java.util.regex.Matcher; /*     */
import java.util.regex.Pattern;
/*     */
/*     */public class StringUtil
/*     */{
	/*     */public static String dealNull(Object object)
	/*     */{
		/* 31 */String returnstr = "";
		/* 32 */if (object == null) {
			/* 33 */returnstr = "";
			/*     */}
		/* 35 */else if ("null".equals(object)) {
			/* 36 */returnstr = "";
			/*     */}
		/*     */else {
			/* 39 */returnstr = (String) object;
			/*     */}
		/* 41 */return returnstr.trim();
		/*     */}
	/*     */
	/*     */public static String[] splitString(String stringList, String delimiter) {
		/* 45 */if (stringList == null)
			/* 46 */return null;
		/* 47 */if (stringList.length() == 0) {
			/* 48 */return null;
			/*     */}
		/* 50 */StringTokenizer st = new StringTokenizer(stringList, delimiter);
		/* 51 */String[] result = new String[st.countTokens()];
		/* 52 */int i = 0;
		/* 53 */while (st.hasMoreTokens()) {
			/* 54 */result[i] = st.nextToken();
			/* 55 */++i;
			/*     */}
		/* 57 */return result;
		/*     */}
	/*     */
	/*     */public static Boolean isEmpty(String str) {
		/* 61 */Boolean isEmpty = Boolean.TRUE;
		/*     */
		/* 63 */if ((str != null) && (!(str.isEmpty()))) {
			/* 64 */isEmpty = Boolean.FALSE;
			/*     */}
		/* 66 */return isEmpty;
		/*     */}
	/*     */
	/*     */public static String getI18NNamePath(String locale, String pNamePath)
	/*     */{
		/* 71 */return "/" + locale + pNamePath;
		/*     */}
	/*     */
	/*     */public static String getNamePath(String pI18NNamePath) {
		/* 75 */String ret = "/";
		/* 76 */if (pI18NNamePath != null) {
			/* 77 */int start = pI18NNamePath.indexOf("/home");
			/* 78 */if (start != -1) {
				/* 79 */ret = pI18NNamePath.substring(start);
				/*     */}
			/*     */}
		/* 82 */return ret;
		/*     */}
	/*     */
	/*     */public static String messageReplace(String messageContent, Map params)
	/*     */{
		/* 88 */List list = new ArrayList();
		/* 89 */list.add("%userName%");
		/* 90 */list.add("%dateTime%");
		/* 91 */list.add("%accountNo%");
		/* 92 */list.add("%maturityDate%");
		/* 93 */list.add("%message%");
		/* 94 */list.add("%url%");
		/* 95 */list.add("%password%");
		/* 96 */list.add("%roleName%");
		/*     */
		/* 98 */String content = messageContent;
		/* 99 */if ((content != null) && (params != null))
		/*     */{
			/* 101 */Set set = params.keySet();
			/* 102 */Iterator it = set.iterator();
			/* 103 */while (it.hasNext()) {
				/* 104 */String key = (String) it.next();
				/* 105 */if (params.get(key) != null) {
					/* 106 */content = content.replaceAll(key, (String) params.get(key));
					/*     */}
				/*     */}
			/*     */
			/* 110 */Iterator its = list.iterator();
			/* 111 */while (its.hasNext()) {
				/* 112 */String key = (String) its.next();
				/* 113 */content = content.replaceAll(key, "");
				/*     */}
			/*     */}
		/*     */
		/* 117 */return content;
		/*     */}
	/*     */
	/*     */public static List<String> getAllImageSrc(String content) {
		/* 121 */List list = new ArrayList();
		/* 122 */Matcher m = Pattern.compile("src=\"?(.*?)(\"|> |\\s+)").matcher(content);
		/* 123 */while (m.find()) {
			/* 124 */list.add(m.group(1));
			/*     */}
		/* 126 */return list;
		/*     */}
	/*     */
	/*     */public static List<String> getAllImageName(String content)
	/*     */{
		/* 131 */List<String> imageSrcList = getAllImageSrc(content);
		/* 132 */List imageNameList = new ArrayList();
		/* 133 */if ((imageSrcList != null) && (imageSrcList.size() > 0)) {
			/* 134 */for (String imageSrc : imageSrcList) {
				/* 135 */String[] imageNames = imageSrc.split("/");
				/* 136 */imageNameList.add(imageNames[(imageNames.length - 1)]);
				/*     */}
			/*     */}
		/* 139 */return imageNameList;
		/*     */}
	/*     */
	/*     */public static String getImageName(String imageSrc) {
		/* 143 */String ret = "";
		/* 144 */if (imageSrc != null) {
			/* 145 */String[] imageNames = imageSrc.split("/");
			/* 146 */if ((imageNames != null) && (imageNames.length > 0)) {
				/* 147 */ret = imageNames[(imageNames.length - 1)];
				/*     */}
			/*     */}
		/* 150 */return ret;
		/*     */}
	/*     */
	/*     */public static boolean isImage(String pName) {
		/* 154 */String postfix = "BMP,PCX,PNG,JPEG,GIF,TIFF,DXF,CGM,WMF,JPG,DIB,RLE,BW,CDR,COL,DWG,DXB,EMF,EPS,ICO,IFF,LBM,MAG,MAC,MPT,MSK,OPT,OLY,PBM,PGM,PPM,PCD,PIC,PICT,PICT2,PNT,PSD,PDD,PXR,RAS,TGA,WIN,XBM";
		/* 155 */boolean ret = false;
		/* 156 */if (pName != null) {
			/* 157 */String[] names = pName.split("\\.");
			/* 158 */if ((names.length > 0) && (postfix.indexOf(names[(names.length - 1)].toUpperCase()) != -1)) {
				/* 159 */ret = true;
				/*     */}
			/*     */}
		/* 162 */return ret;
		/*     */}
	/*     */
	/*     */public static String getRealSrc(String src) {
		/* 166 */String ret = "";
		/* 167 */int a = src.indexOf("attachmentId=");
		/* 168 */if ((src != null) && (a != -1)) {
			/* 169 */ret = "realIageUri&amp;" + src.substring(src.indexOf("attachmentId="));
			/*     */}
		/* 171 */return ret;
		/*     */}
	/*     */
	/*     */public static String substring(String str, int toCount)
	/*     */{
		/* 181 */int reInt = 0;
		/* 182 */String reStr = "";
		/* 183 */if (str == null) {
			/* 184 */return "";
			/*     */}
		/* 186 */char[] tempChar = str.toCharArray();
		/* 187 */for (int kk = 0; (kk < tempChar.length) && (toCount > reInt); ++kk) {
			/* 188 */String s1 = String.valueOf(tempChar[kk]);
			/* 189 */byte[] b = s1.getBytes();
			/* 190 */reInt += b.length;
			/* 191 */reStr = reStr + tempChar[kk];
			/*     */}
		/* 193 */if ((toCount == reInt) || (toCount == reInt - 1)) {
			/* 194 */reStr = reStr + "...";
			/*     */}
		/* 196 */return reStr;
		/*     */}
	/*     */
	/*     */public static String replaceSpace(String content) {
		/* 200 */if ((content == null) || ("".equals(content.trim()))) {
			/* 201 */return "";
			/*     */}
		/*     */
		/*     */do
			/* 205 */content = content.replaceAll("\r\n", "");
		/* 204 */while (content.indexOf("\r\n") != -1);
		/*     */
		/* 207 */while (content.indexOf("\n") != -1) {
			/* 208 */content = content.replaceAll("\n", "");
			/*     */}
		/* 210 */return content;
		/*     */}
	/*     */
	/*     */public static boolean checkNumber(String str)
	/*     */{
		/* 220 */Pattern pattern = Pattern.compile("[0-9]+");
		/* 221 */Matcher matcher = pattern.matcher(str);
		/* 222 */boolean result = matcher.matches();
		/* 223 */return result;
		/*     */}
	/*     */
	/*     */public static boolean isUpload(String imageSrc) {
		/* 227 */boolean ret = false;
		/* 228 */if ((imageSrc != null) && (imageSrc.indexOf("./static/uploads/") != -1)) {
			/* 229 */ret = true;
			/*     */}
		/* 231 */return ret;
		/*     */}
	/*     */
	/*     */public static boolean isServlet(String imageSrc) {
		/* 235 */boolean ret = false;
		/* 236 */if ((imageSrc != null) && (imageSrc.indexOf("CmsImageServlet") != -1)) {
			/* 237 */ret = true;
			/*     */}
		/* 239 */return ret;
		/*     */}
	/*     */
	/*     */public static void main(String[] args)
	/*     */{
		/*     */}
	/*     */
	/*     */public static void nullConverNullString(Object pojo)
	/*     */{
		/* 263 */Class c = pojo.getClass();
		/* 264 */Field[] fields = c.getDeclaredFields();
		/* 265 */for (int i = 0; i < fields.length; ++i)
			/* 266 */if (fields[i].getType() == String.class) {
				/* 267 */String fieldName = fields[i].getName();
				/* 268 */String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				/* 269 */String getName = "get" + methodName;
				/* 270 */Method method = null;
				/* 271 */Object resultObject = "";
				/*     */try {
					/* 273 */method = c.getMethod(getName, null);
					/* 274 */resultObject = method.invoke(pojo, null);
					/*     */}
				/*     */catch (Exception e) {
					/* 277 */e.printStackTrace();
					/*     */}
				/* 279 */if ((method != null) && (resultObject == null)) {
					/* 280 */String setName = "set" + methodName;
					/* 281 */Class[] params = new Class[1];
					/* 282 */params[0] = String.class;
					/*     */try {
						/* 284 */method = c.getMethod(setName, params);
						/* 285 */if (method != null) {
							/* 286 */String[] str = {""};
							/* 287 */method.invoke(pojo, str);
							/*     */}
						/*     */}
					/*     */catch (Exception e) {
						/* 291 */e.printStackTrace();
						/*     */}
					/*     */}
				/*     */}
		/*     */}
	/*     */
	/*     */public static boolean haveNotKeyboardInput(String str)
	/*     */{
		/* 305 */for (int i = str.length(); --i >= 0;) {
			/* 306 */int chr = str.charAt(i);
			/* 307 */if ((chr < 32) || (chr > 126))
				/* 308 */return true;
			/*     */}
		/* 310 */return false;
		/*     */}
	/*     */
	/*     */public static boolean checkSql(String sql)
	/*     */{
		/* 320 */return ((sql.toLowerCase().indexOf("insert") == -1) || (sql.toLowerCase().indexOf("update") == -1) || (sql
				.toLowerCase().indexOf("delete") == -1));
		/*     */}
	/*     */
}

/*
 * Location:
 * D:\Seatech_Workspace_New\IBS-Admin-Web\WebContent\WEB-INF\lib\Geong-
 * IBS-Common.jar Qualified Name: com.geong.ibs.common.util.StringUtil Java
 * Class Version: 6 (50.0) JD-Core Version: 0.5.3
 */