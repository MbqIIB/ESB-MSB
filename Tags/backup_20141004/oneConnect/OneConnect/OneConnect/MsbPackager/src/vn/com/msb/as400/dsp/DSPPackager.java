package vn.com.msb.as400.dsp;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import vn.com.msb.as400.dsp.packager.*;

import com.ftl.util.StreamUtil;
import com.ftl.util.StringUtil;

public class DSPPackager {
	// ABCS RESPONSE FORMAT CODE
	public static DSPCharField ABCSRESPONSEFORMATCODE = new DSPCharField(2,
			"ABCS RESPONSE FORMAT CODE");

	public static DSPPackedField ABCSRESPONSELENGTH = new DSPPackedField(2, 0,
			"ABCS RESPONSE LENGTH");

	// ABCS DSP Packager
	public static DSPPackager ABCSHEADERPACKAGER = null;

	// Static variables initialization
	public static Map<String, DSPField[]> mapHeader = new HashMap<String, DSPField[]>();
	public static Map<String, DSPField[]> mapBody = new HashMap<String, DSPField[]>();
	public static Map<String, DSPPackager> mapDSPPackager = new HashMap<String, DSPPackager>();

	static {
		// Init for ABCS RESPONSE FORMAT Field
		// ABCSRESPONSELENGTH.setOffset(319);// 321-2
		ABCSRESPONSEFORMATCODE.setOffset(321); // 325 - 4

		// Declare Packager Field
		new DSP11111IPackager().declare();
		new DSP13999IPackager().declare();
		new DSP13999RPackager().declare();
		new DSP15007IPackager().declare();
		new DSP15007RPackager().declare();
		new DSP15031IPackager().declare();
		new DSP15031RPackager().declare();
		new DSP15042IPackager().declare();
		new DSP15042RPackager().declare();
		new DSP15043IPackager().declare();
		new DSP15043RPackager().declare();
		new DSP15044IPackager().declare();
		new DSP15044RPackager().declare();
		new DSP15045IPackager().declare();
		new DSP15045RPackager().declare();
		new DSP15046IPackager().declare();
		new DSP15046RPackager().declare();
		new DSP15102IPackager().declare();
		new DSP15102RPackager().declare();
		new DSP15103IPackager().declare();
		new DSP15103RPackager().declare();
		new DSP15104IPackager().declare();
		new DSP15104RPackager().declare();
		new DSP15106IPackager().declare();
		new DSP15106RPackager().declare();
		new DSP15107IPackager().declare();
		new DSP15107RPackager().declare();
		new DSP15109IPackager().declare();
		new DSP15109RPackager().declare();
		new DSP15121IPackager().declare();
		new DSP15121RPackager().declare();
		new DSP15122IPackager().declare();
		new DSP15122RPackager().declare();
		new DSP15125IPackager().declare();
		new DSP15125RPackager().declare();
		new DSP15132IPackager().declare();
		new DSP15132RPackager().declare();
		new DSP15140IPackager().declare();
		new DSP15140RPackager().declare();
		new DSP15141IPackager().declare();
		new DSP15141RPackager().declare();
		new DSP15151IPackager().declare();
		new DSP15151RPackager().declare();
		new DSP15152IPackager().declare();
		new DSP15152RPackager().declare();
		new DSP15188IPackager().declare();
		new DSP15188RPackager().declare();
		new DSP15189IPackager().declare();
		new DSP15189RPackager().declare();
		new DSP15191IPackager().declare();
		new DSP15191RPackager().declare();
		new DSP15201IPackager().declare();
		new DSP15201RPackager().declare();
		new DSP15211IPackager().declare();
		new DSP15211RPackager().declare();
		new DSP15221IPackager().declare();
		new DSP15221RPackager().declare();
		new DSP15222IPackager().declare();
		new DSP15222RPackager().declare();
		new DSP15240IPackager().declare();
		new DSP15240RPackager().declare();
		new DSP15241IPackager().declare();
		new DSP15241RPackager().declare();
		new DSP15242IPackager().declare();
		new DSP15242RPackager().declare();
		new DSP15251IPackager().declare();
		new DSP15251RPackager().declare();
		new DSP15261IPackager().declare();
		new DSP15261RPackager().declare();
		new DSP15281IPackager().declare();
		new DSP15281RPackager().declare();
		new DSP15291IPackager().declare();
		new DSP15291RPackager().declare();
		new DSP15292IPackager().declare();
		new DSP15292RPackager().declare();
		new DSP15293IPackager().declare();
		new DSP15293RPackager().declare();
		new DSP15301IPackager().declare();
		new DSP15301RPackager().declare();
		new DSP15311IPackager().declare();
		new DSP15311RPackager().declare();
		new DSP15321IPackager().declare();
		new DSP15321RPackager().declare();
		new DSP15342IPackager().declare();
		new DSP15342RPackager().declare();
		new DSP15351IPackager().declare();
		new DSP15351RPackager().declare();
		new DSP15352IPackager().declare();
		new DSP15352RPackager().declare();
		new DSP15381IPackager().declare();
		new DSP15381RPackager().declare();
		new DSP15400IPackager().declare();
		new DSP15400RPackager().declare();
		new DSP15600IPackager().declare();
		new DSP15600RPackager().declare();
		new DSP15601IPackager().declare();
		new DSP15601RPackager().declare();
		new DSP15602IPackager().declare();
		new DSP15602RPackager().declare();
		new DSP15603IPackager().declare();
		new DSP15603RPackager().declare();
		new DSP15604IPackager().declare();
		new DSP15604RPackager().declare();
		new DSP15605IPackager().declare();
		new DSP15605RPackager().declare();
		new DSP15606IPackager().declare();
		new DSP15606RPackager().declare();
		new DSP15607IPackager().declare();
		new DSP15607RPackager().declare();
		new DSP15609IPackager().declare();
		new DSP15609RPackager().declare();
		new DSP15611IPackager().declare();
		new DSP15611RPackager().declare();
		new DSP15801IPackager().declare();
		new DSP15801RPackager().declare();
		new DSP15802IPackager().declare();
		new DSP15802RPackager().declare();
		new DSP15803IPackager().declare();
		new DSP15803RPackager().declare();
		new DSP15804IPackager().declare();
		new DSP15804RPackager().declare();
		new DSP15805IPackager().declare();
		new DSP15805RPackager().declare();
		new DSP15806IPackager().declare();
		new DSP15806RPackager().declare();
		new DSP15807IPackager().declare();
		new DSP15807RPackager().declare();
		new DSP15820IPackager().declare();
		new DSP15820RPackager().declare();
		new DSP15999IPackager().declare();
		new DSP15999RPackager().declare();
		new DSP16007IPackager().declare();
		new DSP16007RPackager().declare();
		new DSP16031IPackager().declare();
		new DSP16031RPackager().declare();
		new DSP16041IPackager().declare();
		new DSP16041RPackager().declare();
		new DSP16042IPackager().declare();
		new DSP16042RPackager().declare();
		new DSP16043IPackager().declare();
		new DSP16043RPackager().declare();
		new DSP16045IPackager().declare();
		new DSP16045RPackager().declare();
		new DSP16046IPackager().declare();
		new DSP16046RPackager().declare();
		new DSP16051IPackager().declare();
		new DSP16051RPackager().declare();
		new DSP16060IPackager().declare();
		new DSP16060RPackager().declare();
		new DSP16101IPackager().declare();
		new DSP16101RPackager().declare();
		new DSP16103IPackager().declare();
		new DSP16103RPackager().declare();
		new DSP16106IPackager().declare();
		new DSP16106RPackager().declare();
		new DSP16121IPackager().declare();
		new DSP16121RPackager().declare();
		new DSP16122IPackager().declare();
		new DSP16122RPackager().declare();
		new DSP16123IPackager().declare();
		new DSP16123RPackager().declare();
		new DSP16125IPackager().declare();
		new DSP16125RPackager().declare();
		new DSP16126IPackager().declare();
		new DSP16126RPackager().declare();
		new DSP16132IPackager().declare();
		new DSP16132RPackager().declare();
		new DSP16133IPackager().declare();
		new DSP16133RPackager().declare();
		new DSP16140IPackager().declare();
		new DSP16140RPackager().declare();
		new DSP16141IPackager().declare();
		new DSP16141RPackager().declare();
		new DSP16151IPackager().declare();
		new DSP16151RPackager().declare();
		new DSP16152IPackager().declare();
		new DSP16152RPackager().declare();
		new DSP16189IPackager().declare();
		new DSP16189RPackager().declare();
		new DSP16191IPackager().declare();
		new DSP16191RPackager().declare();
		new DSP16211IPackager().declare();
		new DSP16211RPackager().declare();
		new DSP16221IPackager().declare();
		new DSP16221RPackager().declare();
		new DSP16240IPackager().declare();
		new DSP16240RPackager().declare();
		new DSP16241IPackager().declare();
		new DSP16241RPackager().declare();
		new DSP16242IPackager().declare();
		new DSP16242RPackager().declare();
		new DSP16251IPackager().declare();
		new DSP16251RPackager().declare();
		new DSP16261IPackager().declare();
		new DSP16261RPackager().declare();
		new DSP16281IPackager().declare();
		new DSP16281RPackager().declare();
		new DSP16293IPackager().declare();
		new DSP16293RPackager().declare();
		new DSP16301IPackager().declare();
		new DSP16301RPackager().declare();
		new DSP16311IPackager().declare();
		new DSP16311RPackager().declare();
		new DSP16321IPackager().declare();
		new DSP16321RPackager().declare();
		new DSP16322IPackager().declare();
		new DSP16322RPackager().declare();
		new DSP16342IPackager().declare();
		new DSP16342RPackager().declare();
		new DSP16351IPackager().declare();
		new DSP16351RPackager().declare();
		new DSP16352IPackager().declare();
		new DSP16352RPackager().declare();
		new DSP16353IPackager().declare();
		new DSP16353RPackager().declare();
		new DSP16354IPackager().declare();
		new DSP16354RPackager().declare();
		new DSP16361IPackager().declare();
		new DSP16361RPackager().declare();
		new DSP16371IPackager().declare();
		new DSP16371RPackager().declare();
		new DSP16381IPackager().declare();
		new DSP16381RPackager().declare();
		new DSP16382IPackager().declare();
		new DSP16382RPackager().declare();
		new DSP16391IPackager().declare();
		new DSP16391RPackager().declare();
		new DSP16400IPackager().declare();
		new DSP16400RPackager().declare();
		new DSP16561IPackager().declare();
		new DSP16561RPackager().declare();
		new DSP16602IPackager().declare();
		new DSP16602RPackager().declare();
		new DSP16603IPackager().declare();
		new DSP16603RPackager().declare();
		new DSP16604IPackager().declare();
		new DSP16604RPackager().declare();
		new DSP16605IPackager().declare();
		new DSP16605RPackager().declare();
		new DSP16606IPackager().declare();
		new DSP16606RPackager().declare();
		new DSP16607IPackager().declare();
		new DSP16607RPackager().declare();
		new DSP16609IPackager().declare();
		new DSP16609RPackager().declare();
		new DSP16611IPackager().declare();
		new DSP16611RPackager().declare();
		new DSP16612IPackager().declare();
		new DSP16612RPackager().declare();
		new DSP16613IPackager().declare();
		new DSP16613RPackager().declare();
		new DSP16614IPackager().declare();
		new DSP16614RPackager().declare();
		new DSP16820IPackager().declare();
		new DSP16820RPackager().declare();
		new DSP16821IPackager().declare();
		new DSP16821RPackager().declare();
		new DSP16999IPackager().declare();
		new DSP16999RPackager().declare();
		new DSP17007IPackager().declare();
		new DSP17007RPackager().declare();
		new DSP17031IPackager().declare();
		new DSP17031RPackager().declare();
		new DSP17101IPackager().declare();
		new DSP17101RPackager().declare();
		new DSP17125IPackager().declare();
		new DSP17125RPackager().declare();
		new DSP17132IPackager().declare();
		new DSP17132RPackager().declare();
		new DSP17141IPackager().declare();
		new DSP17141RPackager().declare();
		new DSP17151IPackager().declare();
		new DSP17151RPackager().declare();
		new DSP17189IPackager().declare();
		new DSP17189RPackager().declare();
		new DSP17191IPackager().declare();
		new DSP17191RPackager().declare();
		new DSP17211IPackager().declare();
		new DSP17211RPackager().declare();
		new DSP17221IPackager().declare();
		new DSP17221RPackager().declare();
		new DSP17240IPackager().declare();
		new DSP17240RPackager().declare();
		new DSP17251IPackager().declare();
		new DSP17251RPackager().declare();
		new DSP17261IPackager().declare();
		new DSP17261RPackager().declare();
		new DSP17281IPackager().declare();
		new DSP17281RPackager().declare();
		new DSP17293IPackager().declare();
		new DSP17293RPackager().declare();
		new DSP17301IPackager().declare();
		new DSP17301RPackager().declare();
		new DSP17311IPackager().declare();
		new DSP17311RPackager().declare();
		new DSP17321IPackager().declare();
		new DSP17321RPackager().declare();
		new DSP17342IPackager().declare();
		new DSP17342RPackager().declare();
		new DSP17351IPackager().declare();
		new DSP17351RPackager().declare();
		new DSP17352IPackager().declare();
		new DSP17352RPackager().declare();
		new DSP17354IPackager().declare();
		new DSP17354RPackager().declare();
		new DSP17361IPackager().declare();
		new DSP17361RPackager().declare();
		new DSP17400IPackager().declare();
		new DSP17400RPackager().declare();
		new DSP17625IPackager().declare();
		new DSP17625RPackager().declare();
		new DSP17801IPackager().declare();
		new DSP17801RPackager().declare();
		new DSP17802IPackager().declare();
		new DSP17802RPackager().declare();
		new DSP17820IPackager().declare();
		new DSP17820RPackager().declare();
		new DSP18007IPackager().declare();
		new DSP18007RPackager().declare();
		new DSP18031IPackager().declare();
		new DSP18031RPackager().declare();
		new DSP18051IPackager().declare();
		new DSP18051RPackager().declare();
		new DSP18101IPackager().declare();
		new DSP18101RPackager().declare();
		new DSP18102IPackager().declare();
		new DSP18102RPackager().declare();
		new DSP18122IPackager().declare();
		new DSP18122RPackager().declare();
		new DSP18125IPackager().declare();
		new DSP18125RPackager().declare();
		new DSP18132IPackager().declare();
		new DSP18132RPackager().declare();
		new DSP18133IPackager().declare();
		new DSP18133RPackager().declare();
		new DSP18134IPackager().declare();
		new DSP18134RPackager().declare();
		new DSP18141IPackager().declare();
		new DSP18141RPackager().declare();
		new DSP18151IPackager().declare();
		new DSP18151RPackager().declare();
		new DSP18152IPackager().declare();
		new DSP18152RPackager().declare();
		new DSP18189IPackager().declare();
		new DSP18189RPackager().declare();
		new DSP18191IPackager().declare();
		new DSP18191RPackager().declare();
		new DSP18211IPackager().declare();
		new DSP18211RPackager().declare();
		new DSP18221IPackager().declare();
		new DSP18221RPackager().declare();
		new DSP18240IPackager().declare();
		new DSP18240RPackager().declare();
		new DSP18251IPackager().declare();
		new DSP18251RPackager().declare();
		new DSP18261IPackager().declare();
		new DSP18261RPackager().declare();
		new DSP18293IPackager().declare();
		new DSP18293RPackager().declare();
		new DSP18301IPackager().declare();
		new DSP18301RPackager().declare();
		new DSP18311IPackager().declare();
		new DSP18311RPackager().declare();
		new DSP18321IPackager().declare();
		new DSP18321RPackager().declare();
		new DSP18322IPackager().declare();
		new DSP18322RPackager().declare();
		new DSP18342IPackager().declare();
		new DSP18342RPackager().declare();
		new DSP18351IPackager().declare();
		new DSP18351RPackager().declare();
		new DSP18352IPackager().declare();
		new DSP18352RPackager().declare();
		new DSP18354IPackager().declare();
		new DSP18354RPackager().declare();
		new DSP18361IPackager().declare();
		new DSP18361RPackager().declare();
		new DSP18371IPackager().declare();
		new DSP18371RPackager().declare();
		new DSP18400IPackager().declare();
		new DSP18400RPackager().declare();
		new DSP18613IPackager().declare();
		new DSP18613RPackager().declare();
		new DSP18614IPackager().declare();
		new DSP18614RPackager().declare();
		new DSP18625IPackager().declare();
		new DSP18625RPackager().declare();
		new DSP18801IPackager().declare();
		new DSP18801RPackager().declare();
		new DSP18802IPackager().declare();
		new DSP18802RPackager().declare();
		new DSP18820IPackager().declare();
		new DSP18820RPackager().declare();
		new DSP18821IPackager().declare();
		new DSP18821RPackager().declare();
		new DSP19007IPackager().declare();
		new DSP19007RPackager().declare();
		new DSP19031IPackager().declare();
		new DSP19031RPackager().declare();
		new DSP19102IPackager().declare();
		new DSP19102RPackager().declare();
		new DSP19103IPackager().declare();
		new DSP19103RPackager().declare();
		new DSP19125IPackager().declare();
		new DSP19125RPackager().declare();
		new DSP19132IPackager().declare();
		new DSP19132RPackager().declare();
		new DSP19141IPackager().declare();
		new DSP19141RPackager().declare();
		new DSP19151IPackager().declare();
		new DSP19151RPackager().declare();
		new DSP19189IPackager().declare();
		new DSP19189RPackager().declare();
		new DSP19191IPackager().declare();
		new DSP19191RPackager().declare();
		new DSP19211IPackager().declare();
		new DSP19211RPackager().declare();
		new DSP19221IPackager().declare();
		new DSP19221RPackager().declare();
		new DSP19240IPackager().declare();
		new DSP19240RPackager().declare();
		new DSP19251IPackager().declare();
		new DSP19251RPackager().declare();
		new DSP19261IPackager().declare();
		new DSP19261RPackager().declare();
		new DSP19281IPackager().declare();
		new DSP19281RPackager().declare();
		new DSP19293IPackager().declare();
		new DSP19293RPackager().declare();
		new DSP19301IPackager().declare();
		new DSP19301RPackager().declare();
		new DSP19311IPackager().declare();
		new DSP19311RPackager().declare();
		new DSP19321IPackager().declare();
		new DSP19321RPackager().declare();
		new DSP19342IPackager().declare();
		new DSP19342RPackager().declare();
		new DSP19352IPackager().declare();
		new DSP19352RPackager().declare();
		new DSP19354IPackager().declare();
		new DSP19354RPackager().declare();
		new DSP19355IPackager().declare();
		new DSP19355RPackager().declare();
		new DSP19400IPackager().declare();
		new DSP19400RPackager().declare();
		new DSP19625IPackager().declare();
		new DSP19625RPackager().declare();
		new DSP19801IPackager().declare();
		new DSP19801RPackager().declare();
		new DSP19802IPackager().declare();
		new DSP19802RPackager().declare();
		new DSP19820IPackager().declare();
		new DSP19820RPackager().declare();
		new DSP23002IPackager().declare();
		new DSP23002RPackager().declare();
		new DSP23006IPackager().declare();
		new DSP23006RPackager().declare();
		new DSP23010IPackager().declare();
		new DSP23010RPackager().declare();
		new DSP23013IPackager().declare();
		new DSP23013RPackager().declare();
		new DSP23402IPackager().declare();
		new DSP23402RPackager().declare();
		new DSP23999IPackager().declare();
		new DSP23999RPackager().declare();
		new DSP25001IPackager().declare();
		new DSP25001RPackager().declare();
		new DSP25002IPackager().declare();
		new DSP25002RPackager().declare();
		new DSP25003IPackager().declare();
		new DSP25003RPackager().declare();
		new DSP25005IPackager().declare();
		new DSP25005RPackager().declare();
		new DSP25050IPackager().declare();
		new DSP25050RPackager().declare();
		new DSP25051IPackager().declare();
		new DSP25051RPackager().declare();
		new DSP25105IPackager().declare();
		new DSP25105RPackager().declare();
		new DSP25121IPackager().declare();
		new DSP25121RPackager().declare();
		new DSP25131IPackager().declare();
		new DSP25131RPackager().declare();
		new DSP25141IPackager().declare();
		new DSP25141RPackager().declare();
		new DSP25142IPackager().declare();
		new DSP25142RPackager().declare();
		new DSP25151IPackager().declare();
		new DSP25151RPackager().declare();
		new DSP25161IPackager().declare();
		new DSP25161RPackager().declare();
		new DSP25162IPackager().declare();
		new DSP25162RPackager().declare();
		new DSP25205IPackager().declare();
		new DSP25205RPackager().declare();
		new DSP25206IPackager().declare();
		new DSP25206RPackager().declare();
		new DSP25271IPackager().declare();
		new DSP25271RPackager().declare();
		new DSP25272IPackager().declare();
		new DSP25272RPackager().declare();
		new DSP25294IPackager().declare();
		new DSP25294RPackager().declare();
		new DSP25503IPackager().declare();
		new DSP25503RPackager().declare();
		new DSP25504IPackager().declare();
		new DSP25504RPackager().declare();
		new DSP25520IPackager().declare();
		new DSP25520RPackager().declare();
		new DSP26003IPackager().declare();
		new DSP26003RPackager().declare();
		new DSP26004IPackager().declare();
		new DSP26004RPackager().declare();
		new DSP26005IPackager().declare();
		new DSP26005RPackager().declare();
		new DSP26014IPackager().declare();
		new DSP26014RPackager().declare();
		new DSP26050IPackager().declare();
		new DSP26050RPackager().declare();
		new DSP26051IPackager().declare();
		new DSP26051RPackager().declare();
		new DSP26105IPackager().declare();
		new DSP26105RPackager().declare();
		new DSP26110IPackager().declare();
		new DSP26110RPackager().declare();
		new DSP26121IPackager().declare();
		new DSP26121RPackager().declare();
		new DSP26122IPackager().declare();
		new DSP26122RPackager().declare();
		new DSP26130IPackager().declare();
		new DSP26130RPackager().declare();
		new DSP26131IPackager().declare();
		new DSP26131RPackager().declare();
		new DSP26141IPackager().declare();
		new DSP26141RPackager().declare();
		new DSP26142IPackager().declare();
		new DSP26142RPackager().declare();
		new DSP26151IPackager().declare();
		new DSP26151RPackager().declare();
		new DSP26152IPackager().declare();
		new DSP26152RPackager().declare();
		new DSP26161IPackager().declare();
		new DSP26161RPackager().declare();
		new DSP26205IPackager().declare();
		new DSP26205RPackager().declare();
		new DSP26206IPackager().declare();
		new DSP26206RPackager().declare();
		new DSP26271IPackager().declare();
		new DSP26271RPackager().declare();
		new DSP26272IPackager().declare();
		new DSP26272RPackager().declare();
		new DSP26294IPackager().declare();
		new DSP26294RPackager().declare();
		new DSP26295IPackager().declare();
		new DSP26295RPackager().declare();
		new DSP26296IPackager().declare();
		new DSP26296RPackager().declare();
		new DSP26302IPackager().declare();
		new DSP26302RPackager().declare();
		new DSP26350IPackager().declare();
		new DSP26350RPackager().declare();
		new DSP26504IPackager().declare();
		new DSP26504RPackager().declare();
		new DSP26505IPackager().declare();
		new DSP26505RPackager().declare();
		new DSP26520IPackager().declare();
		new DSP26520RPackager().declare();
		new DSP26621IPackager().declare();
		new DSP26621RPackager().declare();
		new DSP26901IPackager().declare();
		new DSP26901RPackager().declare();
		new DSP26902IPackager().declare();
		new DSP26902RPackager().declare();
		new DSP27050IPackager().declare();
		new DSP27050RPackager().declare();
		new DSP27111IPackager().declare();
		new DSP27111RPackager().declare();
		new DSP27121IPackager().declare();
		new DSP27121RPackager().declare();
		new DSP27122IPackager().declare();
		new DSP27122RPackager().declare();
		new DSP27130IPackager().declare();
		new DSP27130RPackager().declare();
		new DSP27131IPackager().declare();
		new DSP27131RPackager().declare();
		new DSP27141IPackager().declare();
		new DSP27141RPackager().declare();
		new DSP27151IPackager().declare();
		new DSP27151RPackager().declare();
		new DSP27205IPackager().declare();
		new DSP27205RPackager().declare();
		new DSP27206IPackager().declare();
		new DSP27206RPackager().declare();
		new DSP27271IPackager().declare();
		new DSP27271RPackager().declare();
		new DSP28014IPackager().declare();
		new DSP28014RPackager().declare();
		new DSP28050IPackager().declare();
		new DSP28050RPackager().declare();
		new DSP28105IPackager().declare();
		new DSP28105RPackager().declare();
		new DSP28110IPackager().declare();
		new DSP28110RPackager().declare();
		new DSP28111IPackager().declare();
		new DSP28111RPackager().declare();
		new DSP28121IPackager().declare();
		new DSP28121RPackager().declare();
		new DSP28122IPackager().declare();
		new DSP28122RPackager().declare();
		new DSP28130IPackager().declare();
		new DSP28130RPackager().declare();
		new DSP28131IPackager().declare();
		new DSP28131RPackager().declare();
		new DSP28141IPackager().declare();
		new DSP28141RPackager().declare();
		new DSP28151IPackager().declare();
		new DSP28151RPackager().declare();
		new DSP28205IPackager().declare();
		new DSP28205RPackager().declare();
		new DSP28206IPackager().declare();
		new DSP28206RPackager().declare();
		new DSP28271IPackager().declare();
		new DSP28271RPackager().declare();
		new DSP28272IPackager().declare();
		new DSP28272RPackager().declare();
		new DSP28294IPackager().declare();
		new DSP28294RPackager().declare();
		new DSP28302IPackager().declare();
		new DSP28302RPackager().declare();
		new DSP28350IPackager().declare();
		new DSP28350RPackager().declare();
		new DSP28503IPackager().declare();
		new DSP28503RPackager().declare();
		new DSP28504IPackager().declare();
		new DSP28504RPackager().declare();
		new DSP28901IPackager().declare();
		new DSP28901RPackager().declare();
		new DSP28902IPackager().declare();
		new DSP28902RPackager().declare();
		new DSP28903IPackager().declare();
		new DSP28903RPackager().declare();
		new DSP28904IPackager().declare();
		new DSP28904RPackager().declare();
		new DSP28905IPackager().declare();
		new DSP28905RPackager().declare();
		new DSP29050IPackager().declare();
		new DSP29050RPackager().declare();
		new DSP29105IPackager().declare();
		new DSP29105RPackager().declare();
		new DSP29111IPackager().declare();
		new DSP29111RPackager().declare();
		new DSP29121IPackager().declare();
		new DSP29121RPackager().declare();
		new DSP29122IPackager().declare();
		new DSP29122RPackager().declare();
		new DSP29130IPackager().declare();
		new DSP29130RPackager().declare();
		new DSP29131IPackager().declare();
		new DSP29131RPackager().declare();
		new DSP29141IPackager().declare();
		new DSP29141RPackager().declare();
		new DSP29151IPackager().declare();
		new DSP29151RPackager().declare();
		new DSP29205IPackager().declare();
		new DSP29205RPackager().declare();
		new DSP29206IPackager().declare();
		new DSP29206RPackager().declare();
		new DSP29271IPackager().declare();
		new DSP29271RPackager().declare();
		new DSP29999IPackager().declare();
		new DSP29999RPackager().declare();
		new DSP33013IPackager().declare();
		new DSP33013RPackager().declare();
		new DSP33016IPackager().declare();
		new DSP33016RPackager().declare();
		new DSP33018IPackager().declare();
		new DSP33018RPackager().declare();
		new DSP33019IPackager().declare();
		new DSP33019RPackager().declare();
		new DSP33020IPackager().declare();
		new DSP33020RPackager().declare();
		new DSP33021IPackager().declare();
		new DSP33021RPackager().declare();
		new DSP33026IPackager().declare();
		new DSP33026RPackager().declare();
		new DSP33054IPackager().declare();
		new DSP33054RPackager().declare();
		new DSP33058IPackager().declare();
		new DSP33058RPackager().declare();
		new DSP33070IPackager().declare();
		new DSP33070RPackager().declare();
		new DSP33400IPackager().declare();
		new DSP33400RPackager().declare();
		new DSP33419IPackager().declare();
		new DSP33419RPackager().declare();
		new DSP33999IPackager().declare();
		new DSP33999RPackager().declare();
		new DSP35500IPackager().declare();
		new DSP35500RPackager().declare();
		new DSP35501IPackager().declare();
		new DSP35501RPackager().declare();
		new DSP35518IPackager().declare();
		new DSP35518RPackager().declare();
		new DSP35520IPackager().declare();
		new DSP35520RPackager().declare();
		new DSP36110IPackager().declare();
		new DSP36110RPackager().declare();
		new DSP36500IPackager().declare();
		new DSP36500RPackager().declare();
		new DSP36501IPackager().declare();
		new DSP36501RPackager().declare();
		new DSP36518IPackager().declare();
		new DSP36518RPackager().declare();
		new DSP36520IPackager().declare();
		new DSP36520RPackager().declare();
		new DSP36521IPackager().declare();
		new DSP36521RPackager().declare();
		new DSP36530IPackager().declare();
		new DSP36530RPackager().declare();
		new DSP36900IPackager().declare();
		new DSP36900RPackager().declare();
		new DSP36901IPackager().declare();
		new DSP36901RPackager().declare();
		new DSP38110IPackager().declare();
		new DSP38110RPackager().declare();
		new DSP38900IPackager().declare();
		new DSP38900RPackager().declare();
		new DSP38901IPackager().declare();
		new DSP38901RPackager().declare();
		new DSP38902IPackager().declare();
		new DSP38902RPackager().declare();
		
		new DSP41Packager().declare();
		new DSP42Packager().declare();
		new DSP42V2Packager().declare();
		new DSP43Packager().declare();
		new DSP53104IPackager().declare();
		new DSP53104RPackager().declare();
		new DSP55100IPackager().declare();
		new DSP55100RPackager().declare();
		new DSP55101IPackager().declare();
		new DSP55101RPackager().declare();
		new DSP55102IPackager().declare();
		new DSP55102RPackager().declare();
		new DSP55103IPackager().declare();
		new DSP55103RPackager().declare();
		new DSP55104IPackager().declare();
		new DSP55104RPackager().declare();
		new DSP55105IPackager().declare();
		new DSP55105RPackager().declare();
		new DSP55106IPackager().declare();
		new DSP55106RPackager().declare();
		new DSP55110IPackager().declare();
		new DSP55110RPackager().declare();
		new DSP55111IPackager().declare();
		new DSP55111RPackager().declare();
		new DSP55113IPackager().declare();
		new DSP55113RPackager().declare();
		new DSP55115IPackager().declare();
		new DSP55115RPackager().declare();
		new DSP55501IPackager().declare();
		new DSP55501RPackager().declare();
		new DSP55502IPackager().declare();
		new DSP55502RPackager().declare();
		new DSP55503IPackager().declare();
		new DSP55503RPackager().declare();
		new DSP55600IPackager().declare();
		new DSP55600RPackager().declare();
		new DSP55620IPackager().declare();
		new DSP55620RPackager().declare();
		new DSP55622IPackager().declare();
		new DSP55622RPackager().declare();
		new DSP55625IPackager().declare();
		new DSP55625RPackager().declare();
		new DSP55627IPackager().declare();
		new DSP55627RPackager().declare();
		new DSP55629IPackager().declare();
		new DSP55629RPackager().declare();
		new DSP55631IPackager().declare();
		new DSP55631RPackager().declare();
		new DSP55633IPackager().declare();
		new DSP55633RPackager().declare();
		new DSP55636IPackager().declare();
		new DSP55636RPackager().declare();
		new DSP55637IPackager().declare();
		new DSP55637RPackager().declare();
		new DSP55670IPackager().declare();
		new DSP55670RPackager().declare();
		new DSP55671IPackager().declare();
		new DSP55671RPackager().declare();
		new DSP55800IPackager().declare();
		new DSP55800RPackager().declare();
		new DSP55801IPackager().declare();
		new DSP55801RPackager().declare();
		new DSP55805IPackager().declare();
		new DSP55805RPackager().declare();
		new DSP55807IPackager().declare();
		new DSP55807RPackager().declare();
		new DSP55809IPackager().declare();
		new DSP55809RPackager().declare();
		new DSP55820IPackager().declare();
		new DSP55820RPackager().declare();
		new DSP56100IPackager().declare();
		new DSP56100RPackager().declare();
		new DSP56101IPackager().declare();
		new DSP56101RPackager().declare();
		new DSP56105IPackager().declare();
		new DSP56105RPackager().declare();
		new DSP56106IPackager().declare();
		new DSP56106RPackager().declare();
		new DSP56111IPackager().declare();
		new DSP56111RPackager().declare();
		new DSP56113IPackager().declare();
		new DSP56113RPackager().declare();
		new DSP56115IPackager().declare();
		new DSP56115RPackager().declare();
		new DSP56500IPackager().declare();
		new DSP56500RPackager().declare();
		new DSP56503IPackager().declare();
		new DSP56503RPackager().declare();
		new DSP56504IPackager().declare();
		new DSP56504RPackager().declare();
		new DSP56600IPackager().declare();
		new DSP56600RPackager().declare();
		new DSP56620IPackager().declare();
		new DSP56620RPackager().declare();
		new DSP56622IPackager().declare();
		new DSP56622RPackager().declare();
		new DSP56625IPackager().declare();
		new DSP56625RPackager().declare();
		new DSP56626IPackager().declare();
		new DSP56626RPackager().declare();
		new DSP56627IPackager().declare();
		new DSP56627RPackager().declare();
		new DSP56628IPackager().declare();
		new DSP56628RPackager().declare();
		new DSP56629IPackager().declare();
		new DSP56629RPackager().declare();
		new DSP56630IPackager().declare();
		new DSP56630RPackager().declare();
		new DSP56631IPackager().declare();
		new DSP56631RPackager().declare();
		new DSP56632IPackager().declare();
		new DSP56632RPackager().declare();
		new DSP56633IPackager().declare();
		new DSP56633RPackager().declare();
		new DSP56634IPackager().declare();
		new DSP56634RPackager().declare();
		new DSP56635IPackager().declare();
		new DSP56635RPackager().declare();
		new DSP56636IPackager().declare();
		new DSP56636RPackager().declare();
		new DSP56637IPackager().declare();
		new DSP56637RPackager().declare();
		new DSP56638IPackager().declare();
		new DSP56638RPackager().declare();
		new DSP56639IPackager().declare();
		new DSP56639RPackager().declare();
		new DSP56670IPackager().declare();
		new DSP56670RPackager().declare();
		new DSP56671IPackager().declare();
		new DSP56671RPackager().declare();
		new DSP56672IPackager().declare();
		new DSP56672RPackager().declare();
		new DSP56673IPackager().declare();
		new DSP56673RPackager().declare();
		new DSP56700IPackager().declare();
		new DSP56700RPackager().declare();
		new DSP56800IPackager().declare();
		new DSP56800RPackager().declare();
		new DSP56801IPackager().declare();
		new DSP56801RPackager().declare();
		new DSP56802IPackager().declare();
		new DSP56802RPackager().declare();
		new DSP56807IPackager().declare();
		new DSP56807RPackager().declare();
		new DSP56808IPackager().declare();
		new DSP56808RPackager().declare();
		new DSP56809IPackager().declare();
		new DSP56809RPackager().declare();
		new DSP56820IPackager().declare();
		new DSP56820RPackager().declare();
		new DSP56821IPackager().declare();
		new DSP56821RPackager().declare();
		new DSP56850IPackager().declare();
		new DSP56850RPackager().declare();
		new DSP57106IPackager().declare();
		new DSP57106RPackager().declare();
		new DSP57107IPackager().declare();
		new DSP57107RPackager().declare();
		new DSP57108IPackager().declare();
		new DSP57108RPackager().declare();
		new DSP57109IPackager().declare();
		new DSP57109RPackager().declare();
		new DSP57503IPackager().declare();
		new DSP57503RPackager().declare();
		new DSP57625IPackager().declare();
		new DSP57625RPackager().declare();
		new DSP57626IPackager().declare();
		new DSP57626RPackager().declare();
		new DSP57627IPackager().declare();
		new DSP57627RPackager().declare();
		new DSP57801IPackager().declare();
		new DSP57801RPackager().declare();
		new DSP57809IPackager().declare();
		new DSP57809RPackager().declare();
		new DSP57810IPackager().declare();
		new DSP57810RPackager().declare();
		new DSP57820IPackager().declare();
		new DSP57820RPackager().declare();
		new DSP58100IPackager().declare();
		new DSP58100RPackager().declare();
		new DSP58101IPackager().declare();
		new DSP58101RPackager().declare();
		new DSP58102IPackager().declare();
		new DSP58102RPackager().declare();
		new DSP58103IPackager().declare();
		new DSP58103RPackager().declare();
		new DSP58104IPackager().declare();
		new DSP58104RPackager().declare();
		new DSP58106IPackager().declare();
		new DSP58106RPackager().declare();
		new DSP58107IPackager().declare();
		new DSP58107RPackager().declare();
		new DSP58108IPackager().declare();
		new DSP58108RPackager().declare();
		new DSP58109IPackager().declare();
		new DSP58109RPackager().declare();
		new DSP58111IPackager().declare();
		new DSP58111RPackager().declare();
		new DSP58112IPackager().declare();
		new DSP58112RPackager().declare();
		new DSP58113IPackager().declare();
		new DSP58113RPackager().declare();
		new DSP58500IPackager().declare();
		new DSP58500RPackager().declare();
		new DSP58503IPackager().declare();
		new DSP58503RPackager().declare();
		new DSP58504IPackager().declare();
		new DSP58504RPackager().declare();
		new DSP58622IPackager().declare();
		new DSP58622RPackager().declare();
		new DSP58625IPackager().declare();
		new DSP58625RPackager().declare();
		new DSP58626IPackager().declare();
		new DSP58626RPackager().declare();
		new DSP58627IPackager().declare();
		new DSP58627RPackager().declare();
		new DSP58628IPackager().declare();
		new DSP58628RPackager().declare();
		new DSP58629IPackager().declare();
		new DSP58629RPackager().declare();
		new DSP58630IPackager().declare();
		new DSP58630RPackager().declare();
		new DSP58631IPackager().declare();
		new DSP58631RPackager().declare();
		new DSP58632IPackager().declare();
		new DSP58632RPackager().declare();
		new DSP58634IPackager().declare();
		new DSP58634RPackager().declare();
		new DSP58636IPackager().declare();
		new DSP58636RPackager().declare();
		new DSP58801IPackager().declare();
		new DSP58801RPackager().declare();
		new DSP58803IPackager().declare();
		new DSP58803RPackager().declare();
		new DSP58804IPackager().declare();
		new DSP58804RPackager().declare();
		new DSP58809IPackager().declare();
		new DSP58809RPackager().declare();
		new DSP58810IPackager().declare();
		new DSP58810RPackager().declare();
		new DSP58820IPackager().declare();
		new DSP58820RPackager().declare();
		new DSP59106IPackager().declare();
		new DSP59106RPackager().declare();
		new DSP59107IPackager().declare();
		new DSP59107RPackager().declare();
		new DSP59108IPackager().declare();
		new DSP59108RPackager().declare();
		new DSP59109IPackager().declare();
		new DSP59109RPackager().declare();
		new DSP59503IPackager().declare();
		new DSP59503RPackager().declare();
		new DSP59625IPackager().declare();
		new DSP59625RPackager().declare();
		new DSP59626IPackager().declare();
		new DSP59626RPackager().declare();
		new DSP59627IPackager().declare();
		new DSP59627RPackager().declare();
		new DSP59801IPackager().declare();
		new DSP59801RPackager().declare();
		new DSP59809IPackager().declare();
		new DSP59809RPackager().declare();
		new DSP59820IPackager().declare();
		new DSP59820RPackager().declare();
		
		//TF TODO
		new DSP61008IPackager().declare();
		new DSP61008RPackager().declare();
		new DSP62008IPackager().declare();
		new DSP62008RPackager().declare();
		new DSP67007IPackager().declare();
		new DSP67007RPackager().declare();
		new DSP67028IPackager().declare();
		new DSP67028RPackager().declare();
		new DSP68305IPackager().declare();
		new DSP68305RPackager().declare();
		
		new DSP73131IPackager().declare();
		new DSP73131RPackager().declare();
		new DSP73200IPackager().declare();
		new DSP73200RPackager().declare();
		new DSP73211IPackager().declare();
		new DSP73211RPackager().declare();
		new DSP73212IPackager().declare();
		new DSP73212RPackager().declare();
		new DSP73213IPackager().declare();
		new DSP73213RPackager().declare();
		new DSP73999IPackager().declare();
		new DSP73999RPackager().declare();
		new DSP75000IPackager().declare();
		new DSP75000RPackager().declare();
		new DSP75009IPackager().declare();
		new DSP75009RPackager().declare();
		new DSP75010IPackager().declare();
		new DSP75010RPackager().declare();
		new DSP75012IPackager().declare();
		new DSP75012RPackager().declare();
		new DSP75055IPackager().declare();
		new DSP75055RPackager().declare();
		new DSP75110IPackager().declare();
		new DSP75110RPackager().declare();
		new DSP75113IPackager().declare();
		new DSP75113RPackager().declare();
		new DSP75115IPackager().declare();
		new DSP75115RPackager().declare();
		new DSP75150IPackager().declare();
		new DSP75150RPackager().declare();
		new DSP75400IPackager().declare();
		new DSP75400RPackager().declare();
		new DSP75450IPackager().declare();
		new DSP75450RPackager().declare();
		new DSP75452IPackager().declare();
		new DSP75452RPackager().declare();
		new DSP75510IPackager().declare();
		new DSP75510RPackager().declare();
		new DSP75850IPackager().declare();
		new DSP75850RPackager().declare();
		new DSP76000IPackager().declare();
		new DSP76000RPackager().declare();
		new DSP76012IPackager().declare();
		new DSP76012RPackager().declare();
		new DSP76016IPackager().declare();
		new DSP76016RPackager().declare();
		new DSP76050IPackager().declare();
		new DSP76050RPackager().declare();

		new DSP76055IPackager().declare();
		new DSP76055RPackager().declare();
		new DSP76100IPackager().declare();
		new DSP76100RPackager().declare();
		new DSP76150IPackager().declare();
		new DSP76150RPackager().declare();
		new DSP76400IPackager().declare();
		new DSP76400RPackager().declare();
		new DSP76410IPackager().declare();
		new DSP76410RPackager().declare();
		new DSP76450IPackager().declare();
		new DSP76450RPackager().declare();
		new DSP76452IPackager().declare();
		new DSP76452RPackager().declare();
		new DSP76510IPackager().declare();
		new DSP76510RPackager().declare();

		new DSP77000IPackager().declare();
		new DSP77000RPackager().declare();
		new DSP77050IPackager().declare();
		new DSP77050RPackager().declare();

		new DSP77055IPackager().declare();
		new DSP77055RPackager().declare();
		new DSP77113IPackager().declare();
		new DSP77113RPackager().declare();
		new DSP77150IPackager().declare();
		new DSP77150RPackager().declare();
		new DSP77400IPackager().declare();
		new DSP77400RPackager().declare();
		new DSP77450IPackager().declare();
		new DSP77450RPackager().declare();
		new DSP77452IPackager().declare();
		new DSP77452RPackager().declare();
		new DSP78000IPackager().declare();
		new DSP78000RPackager().declare();
		new DSP78050IPackager().declare();
		new DSP78050RPackager().declare();

		new DSP78055IPackager().declare();
		new DSP78055RPackager().declare();
		new DSP78100IPackager().declare();
		new DSP78100RPackager().declare();
		new DSP78113IPackager().declare();
		new DSP78113RPackager().declare();
		new DSP78150IPackager().declare();
		new DSP78150RPackager().declare();
		new DSP78155IPackager().declare();
		new DSP78155RPackager().declare();
		new DSP78400IPackager().declare();
		new DSP78400RPackager().declare();
		new DSP78410IPackager().declare();
		new DSP78410RPackager().declare();
		new DSP78450IPackager().declare();
		new DSP78450RPackager().declare();
		new DSP78452IPackager().declare();
		new DSP78452RPackager().declare();
		new DSP79000IPackager().declare();
		new DSP79000RPackager().declare();
		new DSP79050IPackager().declare();
		new DSP79050RPackager().declare();

		new DSP79055IPackager().declare();
		new DSP79055RPackager().declare();
		new DSP79150IPackager().declare();
		new DSP79150RPackager().declare();
		new DSP79400IPackager().declare();
		new DSP79400RPackager().declare();
		new DSP79450IPackager().declare();
		new DSP79450RPackager().declare();
		new DSP79452IPackager().declare();
		new DSP79452RPackager().declare();
		new DSP81503IPackager().declare();
		new DSP81503RPackager().declare();
		new DSP82301IPackager().declare();
		new DSP82301RPackager().declare();
		new DSP82303IPackager().declare();
		new DSP82303RPackager().declare();
		new DSP82307IPackager().declare();
		new DSP82307RPackager().declare();
		new DSP82501IPackager().declare();
		new DSP82501RPackager().declare();
		new DSP82503IPackager().declare();
		new DSP82503RPackager().declare();
		new DSP82504IPackager().declare();
		new DSP82504RPackager().declare();
		new DSP82523IPackager().declare();
		new DSP82523RPackager().declare();
		new DSP82524IPackager().declare();
		new DSP82524RPackager().declare();
		new DSP82537IPackager().declare();
		new DSP82537RPackager().declare();
		new DSP82547IPackager().declare();
		new DSP82547RPackager().declare();
		new DSP82548IPackager().declare();
		new DSP82548RPackager().declare();
		new DSP82800IPackager().declare();
		new DSP82800RPackager().declare();
		new DSP82804IPackager().declare();
		new DSP82804RPackager().declare();
		new DSP83200IPackager().declare();
		new DSP83200RPackager().declare();
		new DSP83301IPackager().declare();
		new DSP83301RPackager().declare();
		new DSP83503IPackager().declare();
		new DSP83503RPackager().declare();
		new DSP83504IPackager().declare();
		new DSP83504RPackager().declare();
		new DSP83523IPackager().declare();
		new DSP83523RPackager().declare();
		new DSP83804IPackager().declare();
		new DSP83804RPackager().declare();
		new DSP84Packager().declare();
		new DSP84200IPackager().declare();
		new DSP84200RPackager().declare();
		new DSP84301IPackager().declare();
		new DSP84301RPackager().declare();
		new DSP84302IPackager().declare();
		new DSP84302RPackager().declare();
		new DSP84503IPackager().declare();
		new DSP84503RPackager().declare();
		new DSP84504IPackager().declare();
		new DSP84504RPackager().declare();
		new DSP84800IPackager().declare();
		new DSP84800RPackager().declare();
		new DSP84804IPackager().declare();
		new DSP84804RPackager().declare();
		new DSP85Packager().declare();
		new DSP85002IPackager().declare();
		new DSP85002RPackager().declare();
		new DSP85003IPackager().declare();
		new DSP85003RPackager().declare();
		new DSP85020IPackager().declare();
		new DSP85020RPackager().declare();
		new DSP85029IPackager().declare();
		new DSP85029RPackager().declare();
		new DSP85108IPackager().declare();
		new DSP85108RPackager().declare();
		new DSP85120IPackager().declare();
		new DSP85120RPackager().declare();
		new DSP85131IPackager().declare();
		new DSP85131RPackager().declare();
		new DSP85172IPackager().declare();
		new DSP85172RPackager().declare();
		new DSP85200IPackager().declare();
		new DSP85200RPackager().declare();
		new DSP85217IPackager().declare();
		new DSP85217RPackager().declare();
		new DSP85218IPackager().declare();
		new DSP85218RPackager().declare();
		new DSP85224IPackager().declare();
		new DSP85224RPackager().declare();
		new DSP85225IPackager().declare();
		new DSP85225RPackager().declare();
		new DSP85226IPackager().declare();
		new DSP85226RPackager().declare();
		new DSP85301IPackager().declare();
		new DSP85301RPackager().declare();
		new DSP85302IPackager().declare();
		new DSP85302RPackager().declare();
		new DSP85303IPackager().declare();
		new DSP85303RPackager().declare();
		new DSP85304IPackager().declare();
		new DSP85304RPackager().declare();
		new DSP85305IPackager().declare();
		new DSP85305RPackager().declare();
		new DSP85306IPackager().declare();
		new DSP85306RPackager().declare();
		new DSP85307IPackager().declare();
		new DSP85307RPackager().declare();
		new DSP85314IPackager().declare();
		new DSP85314RPackager().declare();
		new DSP85315IPackager().declare();
		new DSP85315RPackager().declare();
		new DSP85317IPackager().declare();
		new DSP85317RPackager().declare();
		new DSP85319IPackager().declare();
		new DSP85319RPackager().declare();
		new DSP85321IPackager().declare();
		new DSP85321RPackager().declare();
		new DSP85370IPackager().declare();
		new DSP85370RPackager().declare();
		new DSP85380IPackager().declare();
		new DSP85380RPackager().declare();
		new DSP85410IPackager().declare();
		new DSP85410RPackager().declare();
		new DSP85439IPackager().declare();
		new DSP85439RPackager().declare();
		new DSP85440IPackager().declare();
		new DSP85440RPackager().declare();
		new DSP85444IPackager().declare();
		new DSP85444RPackager().declare();
		new DSP85446IPackager().declare();
		new DSP85446RPackager().declare();
		new DSP85460IPackager().declare();
		new DSP85460RPackager().declare();
		new DSP85461IPackager().declare();
		new DSP85461RPackager().declare();
		new DSP85464IPackager().declare();
		new DSP85464RPackager().declare();
		new DSP85465IPackager().declare();
		new DSP85465RPackager().declare();
		new DSP85466IPackager().declare();
		new DSP85466RPackager().declare();
		new DSP85467IPackager().declare();
		new DSP85467RPackager().declare();
		new DSP85470RPackager().declare();
		new DSP85480IPackager().declare();
		new DSP85480RPackager().declare();
		new DSP85500IPackager().declare();
		new DSP85500RPackager().declare();
		new DSP85502IPackager().declare();
		new DSP85502RPackager().declare();
		new DSP85504IPackager().declare();
		new DSP85504RPackager().declare();
		new DSP85506IPackager().declare();
		new DSP85506RPackager().declare();
		new DSP85510IPackager().declare();
		new DSP85510RPackager().declare();
		new DSP85511IPackager().declare();
		new DSP85511RPackager().declare();
		new DSP85512IPackager().declare();
		new DSP85512RPackager().declare();
		new DSP85513IPackager().declare();
		new DSP85513RPackager().declare();
		new DSP85514IPackager().declare();
		new DSP85514RPackager().declare();
		new DSP85515IPackager().declare();
		new DSP85515RPackager().declare();
		new DSP85516IPackager().declare();
		new DSP85516RPackager().declare();
		new DSP85517IPackager().declare();
		new DSP85517RPackager().declare();
		new DSP85518IPackager().declare();
		new DSP85518RPackager().declare();
		new DSP85519IPackager().declare();
		new DSP85519RPackager().declare();
		new DSP85520IPackager().declare();
		new DSP85520RPackager().declare();
		new DSP85521IPackager().declare();
		new DSP85521RPackager().declare();
		new DSP85522IPackager().declare();
		new DSP85522RPackager().declare();
		new DSP85523IPackager().declare();
		new DSP85523RPackager().declare();
		new DSP85524IPackager().declare();
		new DSP85524RPackager().declare();
		new DSP85525IPackager().declare();
		new DSP85525RPackager().declare();
		new DSP85526IPackager().declare();
		new DSP85526RPackager().declare();
		new DSP85529IPackager().declare();
		new DSP85529RPackager().declare();
		new DSP85531IPackager().declare();
		new DSP85531RPackager().declare();
		new DSP85532IPackager().declare();
		new DSP85532RPackager().declare();
		new DSP85533IPackager().declare();
		new DSP85533RPackager().declare();
		new DSP85534IPackager().declare();
		new DSP85534RPackager().declare();
		new DSP85535IPackager().declare();
		new DSP85535RPackager().declare();
		new DSP85536IPackager().declare();
		new DSP85536RPackager().declare();
		new DSP85537IPackager().declare();
		new DSP85537RPackager().declare();
		new DSP85549IPackager().declare();
		new DSP85549RPackager().declare();
		new DSP85551IPackager().declare();

		new DSP85551RPackager().declare();

		new DSP85552IPackager().declare();
		new DSP85552RPackager().declare();
		new DSP85554IPackager().declare();
		new DSP85554RPackager().declare();
		new DSP85555IPackager().declare();
		new DSP85555RPackager().declare();
		new DSP85575IPackager().declare();
		new DSP85575RPackager().declare();
		new DSP85576IPackager().declare();
		new DSP85576RPackager().declare();
		new DSP85577IPackager().declare();
		new DSP85577RPackager().declare();
		new DSP85578IPackager().declare();
		new DSP85578RPackager().declare();
		new DSP85590IPackager().declare();
		new DSP85590RPackager().declare();
		new DSP85610IPackager().declare();
		new DSP85610RPackager().declare();
		new DSP85630IPackager().declare();
		new DSP85630RPackager().declare();
		new DSP85680IPackager().declare();
		new DSP85680RPackager().declare();
		new DSP85690IPackager().declare();
		new DSP85690RPackager().declare();
		new DSP85800IPackager().declare();
		new DSP85800RPackager().declare();
		new DSP85801IPackager().declare();
		new DSP85801RPackager().declare();
		new DSP85802IPackager().declare();
		new DSP85802RPackager().declare();
		new DSP85803IPackager().declare();
		new DSP85803RPackager().declare();
		new DSP85804IPackager().declare();
		new DSP85804RPackager().declare();
		new DSP85805IPackager().declare();
		new DSP85805RPackager().declare();
		new DSP85806IPackager().declare();
		new DSP85806RPackager().declare();
		new DSP85807IPackager().declare();
		new DSP85807RPackager().declare();
		new DSP85808IPackager().declare();
		new DSP85808RPackager().declare();
		new DSP85815IPackager().declare();
		new DSP85815RPackager().declare();
		new DSP85816IPackager().declare();
		new DSP85816RPackager().declare();
		new DSP85825IPackager().declare();
		new DSP85825RPackager().declare();
		new DSP85830IPackager().declare();
		new DSP85830RPackager().declare();
		new DSP85831IPackager().declare();
		new DSP85831RPackager().declare();
		new DSP85832IPackager().declare();
		new DSP85832RPackager().declare();
		new DSP85833IPackager().declare();
		new DSP85833RPackager().declare();
		new DSP85835IPackager().declare();
		new DSP85835RPackager().declare();
		new DSP85836IPackager().declare();
		new DSP85836RPackager().declare();
		new DSP85900IPackager().declare();
		new DSP85900RPackager().declare();
		new DSP85901IPackager().declare();
		new DSP85901RPackager().declare();
		new DSP85903IPackager().declare();
		new DSP85903RPackager().declare();
		new DSP86Packager().declare();
		new DSP86020IPackager().declare();
		new DSP86020RPackager().declare();
		new DSP86029IPackager().declare();
		new DSP86029RPackager().declare();
		new DSP86160IPackager().declare();
		new DSP86160RPackager().declare();
		new DSP86201IPackager().declare();
		new DSP86201RPackager().declare();
		new DSP86202IPackager().declare();
		new DSP86202RPackager().declare();
		new DSP86203IPackager().declare();
		new DSP86203RPackager().declare();
		new DSP86204IPackager().declare();
		new DSP86204RPackager().declare();
		new DSP86205IPackager().declare();
		new DSP86205RPackager().declare();
		new DSP86206IPackager().declare();
		new DSP86206RPackager().declare();
		new DSP86207IPackager().declare();
		new DSP86207RPackager().declare();
		new DSP86208IPackager().declare();
		new DSP86208RPackager().declare();
		new DSP86209IPackager().declare();
		new DSP86209RPackager().declare();
		new DSP86210IPackager().declare();
		new DSP86210RPackager().declare();
		new DSP86211IPackager().declare();
		new DSP86211RPackager().declare();
		new DSP86212IPackager().declare();
		new DSP86212RPackager().declare();
		new DSP86213IPackager().declare();
		new DSP86213RPackager().declare();
		new DSP86214IPackager().declare();
		new DSP86214RPackager().declare();
		new DSP86215IPackager().declare();
		new DSP86215RPackager().declare();
		new DSP86216IPackager().declare();
		new DSP86216RPackager().declare();
		new DSP86217IPackager().declare();
		new DSP86217RPackager().declare();
		new DSP86218IPackager().declare();
		new DSP86218RPackager().declare();
		new DSP86224IPackager().declare();
		new DSP86224RPackager().declare();
		new DSP86225IPackager().declare();
		new DSP86225RPackager().declare();
		new DSP86301IPackager().declare();
		new DSP86301RPackager().declare();
		new DSP86302IPackager().declare();
		new DSP86302RPackager().declare();
		new DSP86303IPackager().declare();
		new DSP86303RPackager().declare();
		new DSP86304IPackager().declare();
		new DSP86304RPackager().declare();
		new DSP86305IPackager().declare();
		new DSP86305RPackager().declare();
		new DSP86306IPackager().declare();
		new DSP86306RPackager().declare();
		new DSP86307IPackager().declare();
		new DSP86307RPackager().declare();
		new DSP86309IPackager().declare();
		new DSP86309RPackager().declare();
		new DSP86310IPackager().declare();
		new DSP86310RPackager().declare();
		new DSP86311IPackager().declare();
		new DSP86311RPackager().declare();
		new DSP86314IPackager().declare();
		new DSP86314RPackager().declare();
		new DSP86315IPackager().declare();
		new DSP86315RPackager().declare();
		new DSP86316IPackager().declare();
		new DSP86316RPackager().declare();
		new DSP86317IPackager().declare();
		new DSP86317RPackager().declare();
		new DSP86319IPackager().declare();
		new DSP86319RPackager().declare();
		new DSP86380IPackager().declare();
		new DSP86380RPackager().declare();
		new DSP86410IPackager().declare();
		new DSP86410RPackager().declare();
		new DSP86440IPackager().declare();
		new DSP86440RPackager().declare();

		new DSP86441IPackager().declare();
		new DSP86441RPackager().declare();
		new DSP86443IPackager().declare();
		new DSP86443RPackager().declare();
		new DSP86445IPackager().declare();
		new DSP86445RPackager().declare();
		new DSP86448IPackager().declare();
		new DSP86448RPackager().declare();
		new DSP86450IPackager().declare();
		new DSP86450RPackager().declare();
		new DSP86490IPackager().declare();
		new DSP86490RPackager().declare();
		new DSP86500IPackager().declare();
		new DSP86500RPackager().declare();
		new DSP86502IPackager().declare();
		new DSP86502RPackager().declare();
		new DSP86510IPackager().declare();
		new DSP86510RPackager().declare();
		new DSP86511IPackager().declare();
		new DSP86511RPackager().declare();
		new DSP86512IPackager().declare();
		new DSP86512RPackager().declare();
		new DSP86513IPackager().declare();
		new DSP86513RPackager().declare();
		new DSP86514IPackager().declare();
		new DSP86514RPackager().declare();
		new DSP86515IPackager().declare();
		new DSP86515RPackager().declare();
		new DSP86516IPackager().declare();
		new DSP86516RPackager().declare();
		new DSP86517IPackager().declare();
		new DSP86517RPackager().declare();
		new DSP86518IPackager().declare();
		new DSP86518RPackager().declare();
		new DSP86519IPackager().declare();
		new DSP86519RPackager().declare();
		new DSP86520IPackager().declare();
		new DSP86520RPackager().declare();
		new DSP86521IPackager().declare();
		new DSP86521RPackager().declare();
		new DSP86522IPackager().declare();
		new DSP86522RPackager().declare();
		new DSP86523IPackager().declare();
		new DSP86523RPackager().declare();
		new DSP86525IPackager().declare();
		new DSP86525RPackager().declare();
		new DSP86529IPackager().declare();
		new DSP86529RPackager().declare();
		new DSP86530IPackager().declare();
		new DSP86530RPackager().declare();
		new DSP86531IPackager().declare();
		new DSP86531RPackager().declare();
		new DSP86532IPackager().declare();
		new DSP86532RPackager().declare();
		new DSP86533IPackager().declare();
		new DSP86533RPackager().declare();
		new DSP86534IPackager().declare();
		new DSP86534RPackager().declare();
		new DSP86535IPackager().declare();
		new DSP86535RPackager().declare();
		new DSP86536IPackager().declare();
		new DSP86536RPackager().declare();
		new DSP86537IPackager().declare();
		new DSP86537RPackager().declare();
		new DSP86538IPackager().declare();
		new DSP86538RPackager().declare();
		new DSP86539IPackager().declare();
		new DSP86539RPackager().declare();
		new DSP86540IPackager().declare();
		new DSP86540RPackager().declare();
		new DSP86545IPackager().declare();
		new DSP86545RPackager().declare();
		new DSP86546IPackager().declare();
		new DSP86546RPackager().declare();
		new DSP86547IPackager().declare();
		new DSP86547RPackager().declare();
		new DSP86549IPackager().declare();
		new DSP86549RPackager().declare();
		new DSP86551IPackager().declare();
		new DSP86551RPackager().declare();
		new DSP86552IPackager().declare();
		new DSP86552RPackager().declare();
		new DSP86554IPackager().declare();
		new DSP86554RPackager().declare();
		new DSP86575IPackager().declare();
		new DSP86575RPackager().declare();
		new DSP86576IPackager().declare();
		new DSP86576RPackager().declare();
		new DSP86577IPackager().declare();
		new DSP86577RPackager().declare();
		new DSP86578IPackager().declare();
		new DSP86578RPackager().declare();
		new DSP86590IPackager().declare();
		new DSP86590RPackager().declare();
		new DSP86600IPackager().declare();
		new DSP86600RPackager().declare();
		new DSP86630IPackager().declare();
		new DSP86630RPackager().declare();
		new DSP86670IPackager().declare();
		new DSP86670RPackager().declare();
		new DSP86700IPackager().declare();
		new DSP86700RPackager().declare();
		new DSP86710IPackager().declare();
		new DSP86710RPackager().declare();
		new DSP86720IPackager().declare();
		new DSP86720RPackager().declare();
		new DSP86740IPackager().declare();
		new DSP86740RPackager().declare();
		new DSP86801IPackager().declare();
		new DSP86801RPackager().declare();
		new DSP86803IPackager().declare();
		new DSP86803RPackager().declare();
		new DSP86807IPackager().declare();
		new DSP86807RPackager().declare();
		new DSP86809IPackager().declare();
		new DSP86809RPackager().declare();
		new DSP86812IPackager().declare();
		new DSP86812RPackager().declare();
		new DSP86820IPackager().declare();
		new DSP86820RPackager().declare();
		new DSP86821IPackager().declare();
		new DSP86821RPackager().declare();
		new DSP86822IPackager().declare();
		new DSP86822RPackager().declare();
		new DSP86823IPackager().declare();
		new DSP86823RPackager().declare();
		new DSP86824IPackager().declare();
		new DSP86824RPackager().declare();
		new DSP86825IPackager().declare();
		new DSP86825RPackager().declare();
		new DSP86826IPackager().declare();
		new DSP86826RPackager().declare();
		new DSP86827IPackager().declare();
		new DSP86827RPackager().declare();
		new DSP86828IPackager().declare();
		new DSP86828RPackager().declare();
		new DSP86829IPackager().declare();
		new DSP86829RPackager().declare();
		new DSP86830IPackager().declare();
		new DSP86830RPackager().declare();
		new DSP86836IPackager().declare();
		new DSP86836RPackager().declare();
		new DSP86837IPackager().declare();
		new DSP86837RPackager().declare();
		new DSP86903IPackager().declare();
		new DSP86903RPackager().declare();
		new DSP87Packager().declare();
		new DSP87201IPackager().declare();
		new DSP87201RPackager().declare();
		new DSP87202IPackager().declare();
		new DSP87202RPackager().declare();
		new DSP87203IPackager().declare();
		new DSP87203RPackager().declare();
		new DSP87204IPackager().declare();
		new DSP87204RPackager().declare();
		new DSP87205IPackager().declare();
		new DSP87205RPackager().declare();
		new DSP87206IPackager().declare();
		new DSP87206RPackager().declare();
		new DSP87207IPackager().declare();
		new DSP87207RPackager().declare();
		new DSP87208IPackager().declare();
		new DSP87208RPackager().declare();
		new DSP87209IPackager().declare();
		new DSP87209RPackager().declare();
		new DSP87210IPackager().declare();
		new DSP87210RPackager().declare();
		new DSP87211IPackager().declare();
		new DSP87211RPackager().declare();
		new DSP87212IPackager().declare();
		new DSP87212RPackager().declare();
		new DSP87213IPackager().declare();
		new DSP87213RPackager().declare();
		new DSP87214IPackager().declare();
		new DSP87214RPackager().declare();
		new DSP87215IPackager().declare();
		new DSP87215RPackager().declare();
		new DSP87216IPackager().declare();
		new DSP87216RPackager().declare();
		new DSP87217IPackager().declare();
		new DSP87217RPackager().declare();
		new DSP87218IPackager().declare();
		new DSP87218RPackager().declare();
		new DSP87224IPackager().declare();
		new DSP87224RPackager().declare();
		new DSP87225IPackager().declare();
		new DSP87225RPackager().declare();
		new DSP87304IPackager().declare();
		new DSP87304RPackager().declare();
		new DSP87305IPackager().declare();
		new DSP87305RPackager().declare();
		new DSP87306IPackager().declare();
		new DSP87306RPackager().declare();
		new DSP87307IPackager().declare();
		new DSP87307RPackager().declare();
		new DSP87314IPackager().declare();
		new DSP87314RPackager().declare();
		new DSP87315IPackager().declare();
		new DSP87315RPackager().declare();
		new DSP87317IPackager().declare();
		new DSP87317RPackager().declare();
		new DSP87319IPackager().declare();
		new DSP87319RPackager().declare();
		new DSP87370IPackager().declare();
		new DSP87370RPackager().declare();
		new DSP87380IPackager().declare();
		new DSP87380RPackager().declare();
		new DSP87410IPackager().declare();
		new DSP87410RPackager().declare();
		new DSP87500IPackager().declare();
		new DSP87500RPackager().declare();
		new DSP87510IPackager().declare();
		new DSP87510RPackager().declare();
		new DSP87511IPackager().declare();
		new DSP87511RPackager().declare();
		new DSP87512IPackager().declare();
		new DSP87512RPackager().declare();
		new DSP87513IPackager().declare();
		new DSP87513RPackager().declare();
		new DSP87514IPackager().declare();
		new DSP87514RPackager().declare();
		new DSP87515IPackager().declare();
		new DSP87515RPackager().declare();
		new DSP87516IPackager().declare();
		new DSP87516RPackager().declare();
		new DSP87517IPackager().declare();
		new DSP87517RPackager().declare();
		new DSP87518IPackager().declare();
		new DSP87518RPackager().declare();
		new DSP87519IPackager().declare();
		new DSP87519RPackager().declare();
		new DSP87520IPackager().declare();
		new DSP87520RPackager().declare();
		new DSP87521IPackager().declare();
		new DSP87521RPackager().declare();
		new DSP87522IPackager().declare();
		new DSP87522RPackager().declare();
		new DSP87525IPackager().declare();
		new DSP87525RPackager().declare();
		new DSP87529IPackager().declare();
		new DSP87529RPackager().declare();
		new DSP87531IPackager().declare();
		new DSP87531RPackager().declare();
		new DSP87532IPackager().declare();
		new DSP87532RPackager().declare();
		new DSP87533IPackager().declare();
		new DSP87533RPackager().declare();
		new DSP87535IPackager().declare();
		new DSP87535RPackager().declare();
		new DSP87536IPackager().declare();
		new DSP87536RPackager().declare();
		new DSP87537IPackager().declare();
		new DSP87537RPackager().declare();
		new DSP87549IPackager().declare();
		new DSP87549RPackager().declare();
		new DSP87551IPackager().declare();
		new DSP87551RPackager().declare();
		new DSP87552IPackager().declare();
		new DSP87552RPackager().declare();
		new DSP87554IPackager().declare();
		new DSP87554RPackager().declare();
		new DSP87575IPackager().declare();
		new DSP87575RPackager().declare();
		new DSP87577IPackager().declare();
		new DSP87577RPackager().declare();
		new DSP87590IPackager().declare();
		new DSP87590RPackager().declare();
		new DSP87903IPackager().declare();
		new DSP87903RPackager().declare();
		new DSP88Packager().declare();
		new DSP88201IPackager().declare();
		new DSP88201RPackager().declare();
		new DSP88202IPackager().declare();
		new DSP88202RPackager().declare();
		new DSP88203IPackager().declare();
		new DSP88203RPackager().declare();
		new DSP88204IPackager().declare();
		new DSP88204RPackager().declare();
		new DSP88205IPackager().declare();
		new DSP88205RPackager().declare();
		new DSP88206IPackager().declare();
		new DSP88206RPackager().declare();
		new DSP88207IPackager().declare();
		new DSP88207RPackager().declare();
		new DSP88208IPackager().declare();
		new DSP88208RPackager().declare();
		new DSP88209IPackager().declare();
		new DSP88209RPackager().declare();
		new DSP88210IPackager().declare();
		new DSP88210RPackager().declare();
		new DSP88211IPackager().declare();
		new DSP88211RPackager().declare();
		new DSP88212IPackager().declare();
		new DSP88212RPackager().declare();
		new DSP88213IPackager().declare();
		new DSP88213RPackager().declare();
		new DSP88214IPackager().declare();
		new DSP88214RPackager().declare();
		new DSP88215IPackager().declare();
		new DSP88215RPackager().declare();
		new DSP88216IPackager().declare();
		new DSP88216RPackager().declare();
		new DSP88217IPackager().declare();
		new DSP88217RPackager().declare();
		new DSP88218IPackager().declare();
		new DSP88218RPackager().declare();
		new DSP88219IPackager().declare();
		new DSP88219RPackager().declare();
		new DSP88221IPackager().declare();
		new DSP88221RPackager().declare();
		new DSP88222IPackager().declare();
		new DSP88222RPackager().declare();
		new DSP88223IPackager().declare();
		new DSP88223RPackager().declare();
		new DSP88224IPackager().declare();
		new DSP88224RPackager().declare();
		new DSP88225IPackager().declare();
		new DSP88225RPackager().declare();
		new DSP88301IPackager().declare();
		new DSP88301RPackager().declare();
		new DSP88303IPackager().declare();
		new DSP88303RPackager().declare();
		new DSP88304IPackager().declare();
		new DSP88304RPackager().declare();
		new DSP88305IPackager().declare();
		new DSP88305RPackager().declare();
		new DSP88306IPackager().declare();
		new DSP88306RPackager().declare();
		new DSP88307IPackager().declare();
		new DSP88307RPackager().declare();
		new DSP88309IPackager().declare();
		new DSP88309RPackager().declare();
		new DSP88310IPackager().declare();
		new DSP88310RPackager().declare();
		new DSP88311IPackager().declare();
		new DSP88311RPackager().declare();
		new DSP88314IPackager().declare();
		new DSP88314RPackager().declare();
		new DSP88316IPackager().declare();
		new DSP88316RPackager().declare();
		new DSP88317IPackager().declare();
		new DSP88317RPackager().declare();
		new DSP88319IPackager().declare();
		new DSP88319RPackager().declare();
		new DSP88370IPackager().declare();
		new DSP88370RPackager().declare();
		new DSP88380IPackager().declare();
		new DSP88380RPackager().declare();
		new DSP88410IPackager().declare();
		new DSP88410RPackager().declare();
		new DSP88500IPackager().declare();
		new DSP88500RPackager().declare();
		new DSP88501IPackager().declare();
		new DSP88501RPackager().declare();
		new DSP88502IPackager().declare();
		new DSP88502RPackager().declare();
		new DSP88510IPackager().declare();
		new DSP88510RPackager().declare();
		new DSP88511IPackager().declare();
		new DSP88511RPackager().declare();
		new DSP88512IPackager().declare();
		new DSP88512RPackager().declare();
		new DSP88513IPackager().declare();
		new DSP88513RPackager().declare();
		new DSP88514IPackager().declare();
		new DSP88514RPackager().declare();
		new DSP88515IPackager().declare();
		new DSP88515RPackager().declare();
		new DSP88516IPackager().declare();
		new DSP88516RPackager().declare();
		new DSP88517IPackager().declare();
		new DSP88517RPackager().declare();
		new DSP88518IPackager().declare();
		new DSP88518RPackager().declare();
		new DSP88519IPackager().declare();
		new DSP88519RPackager().declare();
		new DSP88521IPackager().declare();
		new DSP88521RPackager().declare();
		new DSP88522IPackager().declare();
		new DSP88522RPackager().declare();
		new DSP88523IPackager().declare();
		new DSP88523RPackager().declare();
		new DSP88525IPackager().declare();
		new DSP88525RPackager().declare();
		new DSP88529IPackager().declare();
		new DSP88529RPackager().declare();
		new DSP88530IPackager().declare();
		new DSP88530RPackager().declare();
		new DSP88531IPackager().declare();
		new DSP88531RPackager().declare();
		new DSP88532IPackager().declare();
		new DSP88532RPackager().declare();
		new DSP88533IPackager().declare();
		new DSP88533RPackager().declare();
		new DSP88534IPackager().declare();
		new DSP88534RPackager().declare();
		new DSP88535IPackager().declare();
		new DSP88535RPackager().declare();
		new DSP88536IPackager().declare();
		new DSP88536RPackager().declare();
		new DSP88537IPackager().declare();
		new DSP88537RPackager().declare();
		new DSP88538IPackager().declare();
		new DSP88538RPackager().declare();
		new DSP88539IPackager().declare();
		new DSP88539RPackager().declare();
		new DSP88540IPackager().declare();
		new DSP88540RPackager().declare();
		new DSP88545IPackager().declare();
		new DSP88545RPackager().declare();
		new DSP88546IPackager().declare();
		new DSP88546RPackager().declare();
		new DSP88547IPackager().declare();
		new DSP88547RPackager().declare();
		new DSP88548IPackager().declare();
		new DSP88548RPackager().declare();
		new DSP88549IPackager().declare();
		new DSP88549RPackager().declare();
		new DSP88551IPackager().declare();
		new DSP88551RPackager().declare();
		new DSP88552IPackager().declare();
		new DSP88552RPackager().declare();
		new DSP88554IPackager().declare();
		new DSP88554RPackager().declare();
		new DSP88576IPackager().declare();
		new DSP88576RPackager().declare();
		new DSP88578IPackager().declare();
		new DSP88578RPackager().declare();
		new DSP88590IPackager().declare();
		new DSP88590RPackager().declare();
		new DSP88834IPackager().declare();
		new DSP88834RPackager().declare();
		new DSP88902IPackager().declare();
		new DSP88902RPackager().declare();
		new DSP88903IPackager().declare();
		new DSP88903RPackager().declare();
		new DSP88904IPackager().declare();
		new DSP88904RPackager().declare();
		new DSP88V2Packager().declare();
		new DSP89217IPackager().declare();
		new DSP89217RPackager().declare();
		new DSP89218IPackager().declare();
		new DSP89218RPackager().declare();
		new DSP89224IPackager().declare();
		new DSP89224RPackager().declare();
		new DSP89225IPackager().declare();
		new DSP89225RPackager().declare();
		new DSP89301IPackager().declare();
		new DSP89301RPackager().declare();
		new DSP89303IPackager().declare();
		new DSP89303RPackager().declare();
		new DSP89304IPackager().declare();
		new DSP89304RPackager().declare();
		new DSP89305IPackager().declare();
		new DSP89305RPackager().declare();
		new DSP89306IPackager().declare();
		new DSP89306RPackager().declare();
		new DSP89307IPackager().declare();
		new DSP89307RPackager().declare();
		new DSP89314IPackager().declare();
		new DSP89314RPackager().declare();
		new DSP89315IPackager().declare();
		new DSP89315RPackager().declare();
		new DSP89317IPackager().declare();
		new DSP89317RPackager().declare();
		new DSP89319IPackager().declare();
		new DSP89319RPackager().declare();
		new DSP89370IPackager().declare();
		new DSP89370RPackager().declare();
		new DSP89380IPackager().declare();
		new DSP89380RPackager().declare();
		new DSP89410IPackager().declare();
		new DSP89410RPackager().declare();
		new DSP89500IPackager().declare();
		new DSP89500RPackager().declare();
		new DSP89502IPackager().declare();
		new DSP89502RPackager().declare();
		new DSP89510IPackager().declare();
		new DSP89510RPackager().declare();
		new DSP89511IPackager().declare();
		new DSP89511RPackager().declare();
		new DSP89512IPackager().declare();
		new DSP89512RPackager().declare();
		new DSP89513IPackager().declare();
		new DSP89513RPackager().declare();
		new DSP89514IPackager().declare();
		new DSP89514RPackager().declare();
		new DSP89515IPackager().declare();
		new DSP89515RPackager().declare();
		new DSP89516IPackager().declare();
		new DSP89516RPackager().declare();
		new DSP89517IPackager().declare();
		new DSP89517RPackager().declare();
		new DSP89518IPackager().declare();
		new DSP89518RPackager().declare();
		new DSP89519IPackager().declare();
		new DSP89519RPackager().declare();
		new DSP89520IPackager().declare();
		new DSP89520RPackager().declare();
		new DSP89521IPackager().declare();
		new DSP89521RPackager().declare();
		new DSP89522IPackager().declare();
		new DSP89522RPackager().declare();
		new DSP89525IPackager().declare();
		new DSP89525RPackager().declare();
		new DSP89529IPackager().declare();
		new DSP89529RPackager().declare();
		new DSP89531IPackager().declare();
		new DSP89531RPackager().declare();
		new DSP89532IPackager().declare();
		new DSP89532RPackager().declare();
		new DSP89533IPackager().declare();
		new DSP89533RPackager().declare();
		new DSP89535IPackager().declare();
		new DSP89535RPackager().declare();
		new DSP89536IPackager().declare();
		new DSP89536RPackager().declare();
		new DSP89537IPackager().declare();
		new DSP89537RPackager().declare();
		new DSP89549IPackager().declare();
		new DSP89549RPackager().declare();
		new DSP89551IPackager().declare();
		new DSP89551RPackager().declare();
		new DSP89552IPackager().declare();
		new DSP89552RPackager().declare();
		new DSP89554IPackager().declare();
		new DSP89554RPackager().declare();
		new DSP89575IPackager().declare();
		new DSP89575RPackager().declare();
		new DSP89577IPackager().declare();
		new DSP89577RPackager().declare();
		new DSP89590IPackager().declare();
		new DSP89590RPackager().declare();
		new DSP89903IPackager().declare();
		new DSP89903RPackager().declare();
		new DSP89V2Packager().declare();
		new DSP90V2Packager().declare();
		new DSP91Packager().declare();
		new DSP91V2Packager().declare();
		new DSP92Packager().declare();
		new DSP93Packager().declare();
		new DSP94Packager().declare();
		new DSP95000IPackager().declare();
		new DSP95000RPackager().declare();
		new DSP95013IPackager().declare();
		new DSP95013RPackager().declare();
		new DSP95025IPackager().declare();
		new DSP95025RPackager().declare();
		new DSP95042IPackager().declare();
		new DSP95042RPackager().declare();
		//
		new DSP97141IPackager().declare();
		new DSP97141RPackager().declare();
		new DSP98141IPackager().declare();
		new DSP98141RPackager().declare();
		new DSP99141IPackager().declare();
		new DSP99141RPackager().declare();
		//Card mainternance
		new DSP99000IPackager().declare();
		new DSP99000RPackager().declare();		
		new DSP99002IPackager().declare();
		new DSP99002RPackager().declare();
		new DSP99010IPackager().declare();
		new DSP99010RPackager().declare();
		new DSP99011IPackager().declare();
		new DSP99011RPackager().declare();
		new DSP99067IPackager().declare();
		new DSP99067RPackager().declare();
		
		new DSP99Packager().declare();
		new DSP99F1Packager().declare();
		new DSP99F2Packager().declare();
		new DSP99F3Packager().declare();
		new DSPA1Packager().declare();
		new DSPABCSBODYPackager().declare();
		new DSPABCSHEADERPackager().declare();
		new DSPB1Packager().declare();
		new DSPB2Packager().declare();
		new DSPBKPackager().declare();
		new DSPC0Packager().declare();
		new DSPC2Packager().declare();
		new DSPC3Packager().declare();
		new DSPC4V2Packager().declare();
		new DSPC5V2Packager().declare();
		new DSPCBPackager().declare();
		new DSPCD21PPackager().declare();
		new DSPCD21UPackager().declare();
		new DSPCD22PPackager().declare();
		new DSPCD22UPackager().declare();
		new DSPCD23PPackager().declare();
		new DSPCD23UPackager().declare();
		new DSPCIPackager().declare();
		new DSPCIFBODYPackager().declare();
		new DSPCIFHEADERPackager().declare();
		new DSPCLPackager().declare();
		new DSPCRPackager().declare();
		new DSPCXPackager().declare();
		new DSPCYPackager().declare();
		new DSPD1Packager().declare();
		new DSPD2Packager().declare();
		new DSPD3Packager().declare();
		new DSPD4Packager().declare();
		new DSPD5Packager().declare();
		new DSPD6Packager().declare();
		new DSPD7Packager().declare();
		new DSPD8Packager().declare();
		new DSPD9Packager().declare();
		new DSPDD21PPackager().declare();
		new DSPDD21UPackager().declare();
		new DSPDD22PPackager().declare();
		new DSPDD22UPackager().declare();
		new DSPDD23UPackager().declare();
		new DSPDSIBGPackager().declare();
		new DSPDSLNSPackager().declare();
		new DSPDSOPENPackager().declare();
		new DSPDSPD2Packager().declare();
		new DSPDSPDSPackager().declare();
		new DSPDSUTPackager().declare();
		new DSPDXPackager().declare();
		new DSPE3Packager().declare();
		new DSPE4Packager().declare();
		new DSPE5Packager().declare();
		new DSPE6Packager().declare();
		new DSPE7Packager().declare();
		new DSPE8Packager().declare();
		new DSPE9Packager().declare();
		new DSPEXPackager().declare();
		new DSPF1Packager().declare();
		new DSPF1V2Packager().declare();
		new DSPF2Packager().declare();
		new DSPF2OT1Packager().declare();
		new DSPF2OT2Packager().declare();
		new DSPF2OT3Packager().declare();
		new DSPF2V2Packager().declare();
		new DSPF4Packager().declare();
		new DSPF5Packager().declare();
		new DSPF5V2Packager().declare();
		new DSPF6Packager().declare();
		new DSPF7Packager().declare();
		new DSPF8Packager().declare();
		new DSPF9Packager().declare();
		new DSPFXPackager().declare();
		new DSPGIPackager().declare();
		new DSPGSPackager().declare();
		new DSPH1Packager().declare();
		new DSPHEADERPackager().declare();
		new DSPHLHISPackager().declare();
		new DSPIPackager().declare();
		new DSPI1Packager().declare();
		new DSPI2Packager().declare();
		new DSPI3Packager().declare();
		new DSPI4Packager().declare();
		new DSPI5Packager().declare();
		new DSPI6Packager().declare();
		new DSPI7Packager().declare();
		new DSPIAPackager().declare();
		new DSPIBGBODYPackager().declare();
		new DSPICFD2Packager().declare();
		new DSPICFD3Packager().declare();
		new DSPICFDSPackager().declare();
		new DSPLCPackager().declare();
		new DSPLN21PPackager().declare();
		new DSPLN21UPackager().declare();
		new DSPLN22PPackager().declare();
		new DSPLN22UPackager().declare();
		new DSPLN23PPackager().declare();
		new DSPLN23UPackager().declare();
		new DSPLN24PPackager().declare();
		new DSPLN24UPackager().declare();
		new DSPLN25PPackager().declare();
		new DSPLN25UPackager().declare();
		new DSPLN26PPackager().declare();
		new DSPLN27PPackager().declare();
		new DSPLN28PPackager().declare();
		new DSPLN29PPackager().declare();
		new DSPLN2APPackager().declare();
		new DSPLN2BPPackager().declare();
		new DSPLN2CPPackager().declare();
		new DSPLN2DPPackager().declare();
		new DSPLN2EPPackager().declare();
		new DSPLNBODYPackager().declare();
		new DSPLTPackager().declare();
		new DSPMBASEHEADPackager().declare();
		new DSPOLPackager().declare();
		new DSPR1Packager().declare();
		new DSPR2Packager().declare();
		new DSPR3Packager().declare();
		new DSPR5Packager().declare();
		new DSPR6Packager().declare();
		new DSPR7Packager().declare();
		new DSPR8Packager().declare();
		new DSPR9Packager().declare();
		new DSPRIPackager().declare();
		new DSPRMPackager().declare();
		new DSPRMCIFPackager().declare();
		new DSPRMHEADERPackager().declare();
		new DSPRMISSPackager().declare();
		new DSPRMOTHPackager().declare();
		new DSPRMREMIQPackager().declare();
		new DSPRMRESPackager().declare();
		new DSPRXPackager().declare();
		new DSPSBPackager().declare();
		new DSPSMPMAP1Packager().declare();
		new DSPSWPackager().declare();
		new DSPSW88Packager().declare();
		new DSPT2Packager().declare();
		new DSPT3Packager().declare();
		new DSPTAPackager().declare();
		new DSPTBPackager().declare();
		new DSPTMDS2Packager().declare();
		new DSPTSPackager().declare();
		new DSPUTBODYPackager().declare();
		new DSPV242DSPackager().declare();
		new DSPV2C3DSPackager().declare();
		new DSPV2F0DSPackager().declare();
		new DSPV2F2DSPackager().declare();
		new DSPVCPackager().declare();
		try {
			ABCSHEADERPACKAGER = DSPPackager.createPackager("ABCS", null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param strFormatName
	 *            String
	 * @param fld
	 *            DSPField[]
	 */
	public static void addBody(String strFormatName, DSPField fld[]) {
		mapBody.put(strFormatName.toUpperCase(), fld);
	}

	/**
	 * 
	 * @param strFormatName
	 *            String
	 * @param fld
	 *            DSPField[]
	 */
	public static void addHeader(String strFormatName, DSPField fld[]) {
		mapHeader.put(strFormatName.toUpperCase(), fld);
	}

	/**
	 * 
	 * @param strHeader
	 *            String
	 * @param strBody
	 *            String
	 * @return DSPPackager
	 * @throws Exception
	 */
	public static DSPPackager createPackager(String strHeader, String strBody)
			throws Exception {
		String strKey = strHeader;
		if (strBody != null) {
			strKey += strBody;
		}
				
		DSPPackager packager = (DSPPackager) mapDSPPackager.get(strKey);
		if (packager != null) {
			return packager;
		}
		
		

		DSPField fldHeader[] = (DSPField[]) mapHeader.get(strHeader);
		if (fldHeader == null) {
			throw new Exception("Header definition for " + strHeader
					+ " is not found.");
		}
		DSPField fldBody[] = null;
		
		if(strKey.contains("86303I")) {
			System.out.print("vao");
		}
		
		if (strBody == null) {
			fldBody = new DSPField[0];
		} else {
			fldBody = (DSPField[]) mapBody.get(strBody);
		}
		
		if (fldBody == null) {
			throw new Exception("Body definition for " + strBody
					+ " is not found.");
		}
		DSPField fld[] = new DSPField[fldHeader.length + fldBody.length];
		for (int iIndex = 0; iIndex < fldHeader.length; iIndex++) {
			fld[iIndex] = fldHeader[iIndex].createCopy();
		}
		for (int iIndex = 0; iIndex < fldBody.length; iIndex++) {
			fld[iIndex + fldHeader.length] = fldBody[iIndex].createCopy();
		}
		
		
		
		packager = createPackager(fld);
		mapDSPPackager.put(strKey, packager);
		return packager;
	}

	/**
	 * 
	 * @param strHeader
	 *            String
	 * @param hMap
	 *            HashMap
	 * @return DSPPackager
	 * @throws Exception
	 */
	public static DSPPackager createABCSPackager(String strHeader,
			HashMap<String, String> hMap) throws Exception {
		String strKey = strHeader;

		if (hMap != null) {
			strKey += hMap.get("1");
		}

		DSPPackager packager = (DSPPackager) mapDSPPackager.get(strKey);
		if (packager != null) {
			return packager;
		}

		DSPField fldHeader[] = (DSPField[]) mapHeader.get(strHeader);
		if (fldHeader == null) {
			throw new Exception("Header definition for " + strHeader
					+ " is not found.");
		}

		//		
		DSPField fldBody[] = null;
		DSPField fld[] = null;
		if (hMap == null) {
			fldBody = new DSPField[0];
		} else {
			DSPABCSResponse dspAResponses[] = new DSPABCSResponse[hMap.size()];

			//
			String strBody = "";
			for (int i = 0; i < hMap.size();) {
				i++;
				strBody = hMap.get(String.valueOf(i));				
				
				fldBody = (DSPField[]) mapBody.get(strBody);
				if (fldBody == null) {
					throw new Exception("Body definition for " + strBody
							+ " is not found.");
				}
				DSPABCSResponse dspAResponse = new DSPABCSResponse();
				dspAResponse.setStrBody(strBody);
				dspAResponse.setDspField(fldBody);
				dspAResponses[i - 1] = dspAResponse;
			}

			// Iterator<String> iteratorField = fieldMap.keySet().iterator();
			int fieldLength = fldHeader.length;
			// get Length
			for (int i = 0; i < hMap.size(); i++) {
				fieldLength += dspAResponses[i].getDspField().length;
			}

			fld = new DSPField[fieldLength];
			//						
			for (int iIndex = 0; iIndex < fldHeader.length; iIndex++) {
				fld[iIndex] = fldHeader[iIndex].createCopy();
			}
			int pos = fldHeader.length;

			for (int i = 0; i < hMap.size(); i++) {
				int iIndex = 0;
				for (; iIndex < dspAResponses[i].getDspField().length; iIndex++) {
					fld[pos + iIndex] = dspAResponses[i].getDspField()[iIndex]
							.createCopy();
				}
				pos += iIndex;
			}
		}
		packager = createPackager(fld);
		// mapDSPPackager.put(strKey, packager);
		return packager;
	}

	/**
	 * 
	 * @param fld
	 *            DSPField[]
	 * @return DSPPackager
	 */
	public static DSPPackager createPackager(DSPField fld[]) {
		int iMessageLength = 0;
		for (int iIndex = 0; iIndex < fld.length; iIndex++) {
			fld[iIndex].setOffset(iMessageLength);
			iMessageLength += fld[iIndex].getLength();
		}
		DSPPackager packager = new DSPPackager();
		packager.setFieldDefinitionList(fld);
		packager.setMessageLength(iMessageLength);
		return packager;
	}

	/**
	 * 
	 * @param is
	 *            InputStream
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] loadDSPMessage(InputStream is) throws Exception {
		byte[] btLength = StreamUtil.getFixedSizeBuffer(is, 4);
		int iMessageLength = StringUtil.byteArrayToInt(btLength);
		byte[] btData = StreamUtil.getFixedSizeBuffer(is, iMessageLength);
		
		// if (ABCSRESPONSEFORMATCODE.unpack(btData).equals("I5")) {
		// return loadDSPMessage(is);
		// }
		return btData;
	}
		
	// Constant
	// For query and tranfer command
	public static DSPPackager PACKAGER_ABCS_REQUEST;

	// For search customer by cif
	public static DSPPackager PACKAGER_MBASE_15103I;
	
	// For search customer by id
	public static DSPPackager PACKAGER_MBASE_15104I;
	
	// For SEARCH_MOBILEPHONE_BY_CIF
	public static DSPPackager PACKAGER_MBASE_15242I;
	
	// For query account by cif
	public static DSPPackager PACKAGER_MBASE_15291I;

	// For query account more by cif
	public static DSPPackager PACKAGER_MBASE_15999I;

	// For search customer information by cif
	public static DSPPackager PACKAGER_MBASE_16106I;
	
	// For lock tranfer cif
	public static DSPPackager PACKAGER_MBASE_16122I;
	
	// For UPDATE_PHONE_VIEW
	public static DSPPackager PACKAGER_MBASE_16240I;
	
	// For ADD_CIF_ECONTACT
	public static DSPPackager PACKAGER_MBASE_17240I;
	
	// For ADD_CIF_GROUP
	public static DSPPackager PACKAGER_MBASE_17321I;
	// For update officer code
	
	public static DSPPackager PACKAGER_MBASE_17342I; 
	
	// For create cif
	public static DSPPackager PACKAGER_MBASE_17625I;
	
	// For cif mainternance
	public static DSPPackager PACKAGER_MBASE_18102I;
	
	// For tranfer cif
	public static DSPPackager PACKAGER_MBASE_18122I;
	
	// For UPDATE_PHONE_MODIFY
	public static DSPPackager PACKAGER_MBASE_18240I;
	
	// For mini statement
	public static DSPPackager PACKAGER_MBASE_25520I;
	
	//for Lock account overdraft
		public static DSPPackager PACKAGER_MBASE_26050I;
		
		//for maintain thau chi
		public static DSPPackager PACKAGER_MBASE_28050I;

	//for query account overdraft
	public static DSPPackager PACKAGER_MBASE_26051I;
	
	// For lock new ca maintenance - update introducer code cif
	public static DSPPackager PACKAGER_MBASE_26110I;
	
	// For cancel AFT command
	public static DSPPackager PACKAGER_MBASE_26121I;
	
	// For lock - unhold command
	public static DSPPackager PACKAGER_MBASE_26141I;
	
	// For query account DD
	public static DSPPackager PACKAGER_MBASE_26161I;
	
	// For query account DD
//	public static DSPPackager PACKAGER_MBASE_26162I;
	
	// For lock approve row for salary
	public static DSPPackager PACKAGER_MBASE_26205I;
	
	// For lock ca maintenance - update introducer code cif
	public static DSPPackager PACKAGER_MBASE_26901I;
	
	//for create overdraft account
	
	public static DSPPackager PACKAGER_MBASE_27050I;
	
	// For AFT command
	public static DSPPackager PACKAGER_MBASE_27121I;
	
	// For hold command
	public static DSPPackager PACKAGER_MBASE_27141I;

	// For cash flow command
	public static DSPPackager PACKAGER_MBASE_28131I;
	
	// For unhold command
	public static DSPPackager PACKAGER_MBASE_28141I;
	
	// For ca maintenance - update introducer code cif
	public static DSPPackager PACKAGER_MBASE_28901I;
	
	// For salary add ref
	public static DSPPackager PACKAGER_MBASE_28902I;

	// For salary approve 
	public static DSPPackager PACKAGER_MBASE_28903I;

	// For salary posting
	public static DSPPackager PACKAGER_MBASE_28904I;
		
	// For new ca maintenance - update introducer code cif
	public static DSPPackager PACKAGER_MBASE_28110I;

	// For destroy AFT
	public static DSPPackager PACKAGER_MBASE_29121I;
		
	// For unlock salary - cancel lock
	public static DSPPackager PACKAGER_MBASE_29999I;
	
	// For inquiry FD
	public static DSPPackager PACKAGER_MBASE_36501I;
	
	// For rate schedule FD
	public static DSPPackager PACKAGER_MBASE_38902I;
		
	//For TF TODO
	public static DSPPackager PACKAGER_MBASE_61008I;
	
	public static DSPPackager PACKAGER_MBASE_62008I;
	
	public static DSPPackager PACKAGER_MBASE_67007I;
	
	public static DSPPackager PACKAGER_MBASE_67028I;
	
	public static DSPPackager PACKAGER_MBASE_68305I;
	
	// For CREATE GL_TRANSACTION_BATCH 
	public static DSPPackager PACKAGER_MBASE_77113I;

	// For GL_TRANSACTION_BATCH
	public static DSPPackager PACKAGER_MBASE_77150I;
	
	// For APPROVE GL_TRANSACTION_BATCH
	public static DSPPackager PACKAGER_MBASE_78155I;
	
	//search AA by Cif
	public static DSPPackager PACKAGER_MBASE_83301I;
	//search detail Facility
	public static DSPPackager PACKAGER_MBASE_86303I;
	//search all Facility
	
	
	public static DSPPackager PACKAGER_MBASE_88222I;
	
	public static DSPPackager PACKAGER_MBASE_85303I;
	
	//maintain  facility
	public static DSPPackager PACKAGER_MBASE_88303I;
	
	//unlock facility
	
	public static DSPPackager PACKAGER_MBASE_88902I;
	
	// For LN account inquiry
	public static DSPPackager PACKAGER_MBASE_85800I;
		
	// For master LN
	public static DSPPackager PACKAGER_MBASE_86820I;

	// For inquiry LN
	public static DSPPackager PACKAGER_MBASE_86821I;
	
	
	// For Collateral tao tai san dam bao
	public static DSPPackager PACKAGER_MBASE_87202I;
	
	// For Collateral link tao tai san dam bao
	public static DSPPackager PACKAGER_MBASE_87305I;
	
	// Search AA by cif
	public static DSPPackager PACKAGER_MBASE_85301I;
	
	// Search AA by cif
		public static DSPPackager PACKAGER_MBASE_88301I;
		
	// For Collateral link tao tai san dam bao
	public static DSPPackager PACKAGER_MBASE_86202I;
	
	//86202
	
	// For lock Collateral link tao tai san dam bao
		public static DSPPackager PACKAGER_MBASE_86305I;
		
	// For Collateral link delete  tai san dam bao
	public static DSPPackager PACKAGER_MBASE_89305I;
		
		
	// For get account information by card
	public static DSPPackager PACKAGER_MBASE_95000I;
	
	// For card inquiry
	public static DSPPackager PACKAGER_MBASE_95013I;
	
	// For link card
	public static DSPPackager PACKAGER_MBASE_95025I;
	
	// For card inquiry
	public static DSPPackager PACKAGER_MBASE_95042I;

	// For hold night mode
	public static DSPPackager PACKAGER_MBASE_97141I;	
	
	// For unhold night mode
	public static DSPPackager PACKAGER_MBASE_98141I;
	
	// For unhold night mode
	public static DSPPackager PACKAGER_MBASE_99141I;
		
	// For link card
	public static DSPPackager PACKAGER_MBASE_99000I;
	
	// For card mainternance
	public static DSPPackager PACKAGER_MBASE_99002I;
	
	// For lock card
	public static DSPPackager PACKAGER_MBASE_99010I;
	
	// For unlock card
	public static DSPPackager PACKAGER_MBASE_99011I;
	
		
	// For active ATM card
	public static DSPPackager PACKAGER_MBASE_99067I;
	
	
	
	
	static {
		try {
			// Packager for query and tranfer command
			PACKAGER_ABCS_REQUEST = createPackager("ABCS", "ABCSBODY");			
			
			// Packager for search by cif command
			PACKAGER_MBASE_15103I = createPackager("MBSD", "15103I");

			// Packager for search by id command
			PACKAGER_MBASE_15104I = createPackager("MBSD", "15104I");

			// Packager for SEARCH_MOBILEPHONE_BY_CIF
			PACKAGER_MBASE_15242I = createPackager("MBSD", "15242I");
			
			// Packager for search account by cif
			PACKAGER_MBASE_15291I = createPackager("MBSD", "15291I");
						
			// Packager for search account more by cif
			PACKAGER_MBASE_15999I = createPackager("MBSD", "15999I");

			// For get information by cif
			PACKAGER_MBASE_16106I = createPackager("MBSD", "16106I");

			// Packager for lock cif command
			PACKAGER_MBASE_16122I = createPackager("MBSD", "16122I");

			// Packager for UPDATE_PHONE_VIEW
			PACKAGER_MBASE_16240I = createPackager("MBSD", "16240I");

			// Packager for ADD_CIF_ECONTACT
			PACKAGER_MBASE_17240I = createPackager("MBSD", "17240I");
			
			// Packager for ADD_CIF_GROUP
			PACKAGER_MBASE_17321I = createPackager("MBSD", "17321I");
			
			// Packager for update office Code
			PACKAGER_MBASE_17342I = createPackager("MBSD", "17342I");

			// Packager for create cif command
			PACKAGER_MBASE_17625I = createPackager("MBSD", "17625I");

			// Packager for cif mainternance command
			PACKAGER_MBASE_18102I = createPackager("MBSD", "18102I");
						
			// Packager for transfer cif command
			PACKAGER_MBASE_18122I = createPackager("MBSD", "18122I");

			// Packager for UPDATE_PHONE_MODIFY
			PACKAGER_MBASE_18240I = createPackager("MBSD", "18240I");

			// For mini statement
			PACKAGER_MBASE_25520I = createPackager("MBSD", "25520I");
			
			// for query account overdrapt			
			PACKAGER_MBASE_26051I = createPackager("MBSD", "26051I");

			PACKAGER_MBASE_28050I = createPackager("MBSD", "28050I");
			
			PACKAGER_MBASE_26050I = createPackager("MBSD", "26050I");
			// For lock new ca maintenance - update introducer code cif
			PACKAGER_MBASE_26110I = createPackager("MBSD", "26110I");

			// For cancel AFT command
			PACKAGER_MBASE_26121I = createPackager("MBSD", "26121I");

			// For lock unhold command
			PACKAGER_MBASE_26141I = createPackager("MBSD", "26141I");
			
			// For DD master inquiry
			PACKAGER_MBASE_26161I = createPackager("MBSD", "26161I");			
			
			// For lock approve row for salary
			PACKAGER_MBASE_26205I = createPackager("MBSD", "26205I");
						
			// For lock ca maintenance - update introducer code cif
			PACKAGER_MBASE_26901I = createPackager("MBSD", "26901I");
			
			//for create overdraft 
			
			PACKAGER_MBASE_27050I = createPackager("MBSD", "27050I");
			
			// Packager for AFT command
			PACKAGER_MBASE_27121I = createPackager("MBSD", "27121I");
			
			// Packager for hold command
			PACKAGER_MBASE_27141I = createPackager("MBSD", "27141I");
						
			// For new ca maintenance - update introducer code cif
			PACKAGER_MBASE_28110I = createPackager("MBSD", "28110I");

			// Packager for cash flow
			PACKAGER_MBASE_28131I = createPackager("MBSD", "28131I");
			
			// Packager for unhold command
			PACKAGER_MBASE_28141I = createPackager("MBSD", "28141I");
			
			// For ca maintenance - update introducer code cif
			PACKAGER_MBASE_28901I = createPackager("MBSD", "28901I");

			// For salary - add ref
			PACKAGER_MBASE_28902I = createPackager("MBSD", "28902I");
			
			// For salary - approve
			PACKAGER_MBASE_28903I = createPackager("MBSD", "28903I");
			
			// For salary - posting
			PACKAGER_MBASE_28904I = createPackager("MBSD", "28904I");
			
			// For destroy AFT
			PACKAGER_MBASE_29121I = createPackager("MBSD", "29121I");
						
			// For salary - cancel lock
			PACKAGER_MBASE_29999I = createPackager("MBSD", "29999I");
						
			// For FD inquiry
			PACKAGER_MBASE_36501I = createPackager("MBSD", "36501I");
						
			// For FD rate schedule
			PACKAGER_MBASE_38902I = createPackager("MBSD", "38902I");
						
			// For TF TODO
			PACKAGER_MBASE_61008I = createPackager("MBSD", "61008I");
			
			PACKAGER_MBASE_62008I = createPackager("MBSD", "62008I");
			
			PACKAGER_MBASE_67007I = createPackager("MBSD", "67007I");
			
			PACKAGER_MBASE_67028I = createPackager("MBSD", "67028I");
			
			PACKAGER_MBASE_68305I = createPackager("MBSD", "68305I");
						
			// Packager for GL transaction - create batch
			PACKAGER_MBASE_77113I = createPackager("MBSD", "77113I");			
			
			// Packager for GL transaction
			PACKAGER_MBASE_77150I = createPackager("MBSD", "77150I");
			
			// Packager for GL transaction - approve batch
			PACKAGER_MBASE_78155I = createPackager("MBSD", "78155I");
			// for search AA by cif
			PACKAGER_MBASE_83301I = createPackager("MBSD", "83301I");
			//for search detail Facility 			
			PACKAGER_MBASE_86303I = createPackager("MBSD", "86303I");
			//for search all Facility
			PACKAGER_MBASE_85303I = createPackager("MBSD", "85303I");
			// for maintain Facility
			PACKAGER_MBASE_88303I = createPackager("MBSD", "88303I");
			// for unlock facility
			PACKAGER_MBASE_88902I = createPackager("MBSD", "88902I");
			
			
			// For LN account inquiry
			PACKAGER_MBASE_85800I = createPackager("MBSD", "85800I");
			
			// For LN master
			PACKAGER_MBASE_86820I = createPackager("MBSD", "86820I");
			
			// For LN inquiry
			PACKAGER_MBASE_86821I = createPackager("MBSD", "86821I");
			
			// For LN  Collateral
			PACKAGER_MBASE_87202I = createPackager("MBSD", "87202I");
			
				// For LN  Collateral link 
			PACKAGER_MBASE_87305I = createPackager("MBSD", "87305I");
		
			// For lock  Collateral for delete 
			PACKAGER_MBASE_86202I = createPackager("MBSD", "86202I");
			
			// For lock  Collateral for delete 
			PACKAGER_MBASE_88222I = createPackager("MBSD", "88222I");
						
		
			// For LN delete Collateral link 
			PACKAGER_MBASE_86305I = createPackager("MBSD", "86305I");

			// For LN delete Collateral link 
			PACKAGER_MBASE_89305I = createPackager("MBSD", "89305I");
		
			
			// Packager for get account information by card number
			PACKAGER_MBASE_95000I = createPackager("MBSD", "95000I");
			
			// Packager for card inquiry
			PACKAGER_MBASE_95013I = createPackager("MBSD", "95013I");
			
			// Packager for link card
			PACKAGER_MBASE_95025I = createPackager("MBSD", "95025I");
			
			// Packager for card inquiry
			PACKAGER_MBASE_95042I = createPackager("MBSD", "95042I");
			
			// Packager for hold night mode
			PACKAGER_MBASE_97141I = createPackager("MBSD", "97141I");
			
			// Packager for unhold night mode
			PACKAGER_MBASE_98141I = createPackager("MBSD", "98141I");
			
			// Packager for link card
			PACKAGER_MBASE_99000I = createPackager("MBSD", "99000I");
			
			// Packager for card mainternance
			PACKAGER_MBASE_99002I = createPackager("MBSD", "99002I");
			
			// Packager for lock card
			PACKAGER_MBASE_99010I = createPackager("MBSD", "99010I");
			
			// Packager for unlock card
			PACKAGER_MBASE_99011I = createPackager("MBSD", "99011I");
			
			// Packager for unhold night mode
			PACKAGER_MBASE_99141I = createPackager("MBSD", "99141I");
						
			
			
			// Packager for ATM card
			PACKAGER_MBASE_99067I = createPackager("MBSD", "99067I");

		} catch (Exception ex) {
			ex.printStackTrace();
//			System.exit(-1);
		}
	}

	// Member variables
	private DSPField fieldDefinitionList[];
	private int messageLength;
	private byte[] lengthField;

	/**
	 * 
	 * @param fieldDefinitionList
	 *            DSPField[]
	 */
	public void setFieldDefinitionList(DSPField[] fieldDefinitionList) {
		this.fieldDefinitionList = fieldDefinitionList;
	}

	/**
	 * 
	 * @param messageLength
	 *            int
	 */
	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}

	/**
	 * 
	 * @return DSPField[]
	 */
	public DSPField[] getFieldDefinitionList() {
		return fieldDefinitionList;
	}

	/**
	 * 
	 * @return int
	 */
	public int getMessageLength() {
		return messageLength;
	}

	/**
	 * 
	 * @return byte[]
	 */
	public byte[] getLengthField() {
		if (lengthField == null) {
			byte[] btValue = StringUtil.intToByteArray(getMessageLength());
			lengthField = new byte[4];
			System.arraycopy(btValue, 0, lengthField, lengthField.length
					- btValue.length, btValue.length);
		}
		return lengthField;
	}

	/**
	 * 
	 * @param strValue
	 *            String[]
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] pack(String[] strValue) throws Exception {
		byte[] btLengthField = getLengthField();
		int iLengthFieldLength = btLengthField.length;
		byte[] bt = new byte[getMessageLength() + iLengthFieldLength];
		System.arraycopy(btLengthField, 0, bt, 0, iLengthFieldLength);
		DSPField fld[] = getFieldDefinitionList();
		int iLength = fld.length;
		for (int iIndex = 0; iIndex < iLength; iIndex++) {
			try{
			DSPField fieldPackager = fld[iIndex];
			String strTemp = null;
			if (iIndex < strValue.length) {
				strTemp = strValue[iIndex];
			}

			byte[] btEncoded = fieldPackager.pack(strTemp);

			System.arraycopy(btEncoded, 0, bt, fieldPackager.getOffset()
					+ iLengthFieldLength, btEncoded.length);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return bt;
	}

	/**
	 * 
	 * @param bt
	 *            byte[]
	 * @param strSeparator
	 *            String
	 * @return String
	 * @throws Exception
	 */
	public String unpack(byte[] bt, String strSeparator) throws Exception {

		StringBuffer sb = new StringBuffer();
		DSPField fieldList[] = getFieldDefinitionList();
		int iIndex = 0; //TODO hard code for test 
		for (; iIndex < fieldList.length; iIndex++) {
			DSPField fld = fieldList[iIndex];
			if (fld.getOffset() + fld.getLength() <= bt.length) {
				String strContent = fld.unpack(bt);
				sb.append(strContent);
				sb.append(strSeparator);
			}else{
				break;
			}
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param bt
	 *            byte[]
	 * @param strSeparator
	 *            String
	 * @return String
	 * @throws Exception
	 */
	public String unpackMultiRecords(byte[] bt, String strHead,
			String strSeparator) throws Exception {
		StringBuffer sb = new StringBuffer();
		DSPField fieldList[] = getFieldDefinitionList();
		int iMessageLength = 0;
		int istart = 0;
//		int ilength = 0;
		int repeatedKey = 0;
		String strContent = "";
		while (iMessageLength < bt.length) {//			
			for (int iIndex = istart; iIndex < fieldList.length; iIndex++) {//run one round with packager
				DSPField fld = fieldList[iIndex];
//				ilength = iMessageLength;
				fld.setOffset(iMessageLength);
				iMessageLength += fld.getLength();//length of message				
//				if (ilength + fld.getLength() <= bt.length) {

				if (iMessageLength <= bt.length) {
					strContent = fld.unpack(bt);					
					sb.append(strContent);	
				}  else if(bt.length - fld.getOffset() > 0){//Updated 11/05/2012
					int oldLength = fld.getLength();
					fld.setLength(bt.length - fld.getOffset());//set for dynamic data end of messages
					strContent = fld.unpack(bt);
					sb.append(strContent);
					fld.setLength(oldLength);
				}else {
					break;
				}
				sb.append(strSeparator);
			}
			
			
			istart = strHead.equals(DSPMessageConstant.ABCS_NAME) ? DSPMessageConstant.ABCS_RESPONSE_FORMAT_CODE
					: DSPMessageConstant.MBASE_RESPONSE_FORMAT_CODE;

			// repeated Key for MBASE
			if(strHead.equals(DSPMessageConstant.MBSD_NAME)){
				repeatedKey = DSPConstants
						.getRepeatedKey(fieldList[DSPMessageConstant.RESPONSE_POSITION]
								.unpack(bt));
				if (repeatedKey > 0) {
					istart = istart - repeatedKey;
				}
			}

		}
		return sb.toString();
	}

	/**
	 * 
	 * @param btResponse
	 *            byte[]
	 * @return DSPPackager
	 * @throws Exception
	 */
	public static DSPPackager getMBSDResponsePackager(byte[] btResponse,
			DSPPackager packager) throws Exception {
		// Get Header
		String strDSPHeader = packager.getFieldDefinitionList()[DSPMessageConstant.DSP_DATA_FORMAT_ID]
				.unpack(btResponse);
		
		String strResponseFormatCode = null;
		if (strDSPHeader.equals(DSPMessageConstant.MBSD_NAME)) {
			// Get MBSD RESPONSE FORMAT CODE
			strResponseFormatCode = packager.getFieldDefinitionList()[DSPMessageConstant.RESPONSE_POSITION]
					.unpack(btResponse);
		}
		return DSPPackager.createPackager(strDSPHeader, strResponseFormatCode
				+ "R");
	}

	public static DSPPackager getABCSResponsePackager(byte[] btResponse)
			throws Exception {
		// Get Header
		String strDSPHeader = DSPPackager.ABCSHEADERPACKAGER
				.getFieldDefinitionList()[DSPMessageConstant.DSP_DATA_FORMAT_ID]
				.unpack(btResponse);
		String strResponseFormatCode = null;
		HashMap<String, String> hMap = new HashMap<String, String>();

		int iCurrentBytePos = 319;
		int iTempTotalByteReceive = btResponse.length - iCurrentBytePos;
		int iResponseLength = ABCSRESPONSELENGTH.getOffset();
		int count = 0;

		if (strDSPHeader.equals(DSPMessageConstant.ABCS_NAME)) {
			//
			for (; iTempTotalByteReceive > 0; iTempTotalByteReceive -= iResponseLength) {
				//

				ABCSRESPONSEFORMATCODE.setOffset(iCurrentBytePos + 2);
				ABCSRESPONSELENGTH.setOffset(iCurrentBytePos);

				strResponseFormatCode = ABCSRESPONSEFORMATCODE
						.unpack(btResponse);
				count++;
				iResponseLength = Integer.parseInt(ABCSRESPONSELENGTH
						.unpack(btResponse)) + 2;

				hMap.put(String.valueOf(count), strResponseFormatCode);
				iCurrentBytePos += iResponseLength;
			}
		}
		return DSPPackager.createABCSPackager(strDSPHeader, hMap);
	}

}
