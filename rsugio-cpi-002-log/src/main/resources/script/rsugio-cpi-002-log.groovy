/**
 * Some logging technics for CPI IFlow
 *
 * @author Iliya Kuznetsov <iliya.kuznetsov@gmail.com>
 * @version 1.0.2
 * @date 2018-02-15
 * @see https://github.com/rsugio/cpi/tree/master/rsugio-cpi-002-log
 */

import com.sap.gateway.ip.core.customdev.util.Message as CpiMsg

import com.sap.it.api.ITApiFactory
import com.sap.it.spi.ITApiHandler
import com.sap.it.api.securestore.SecureStoreService
import com.sap.it.api.securestore.UserCredential
import com.sap.it.api.securestore.exception.SecureStoreException
import com.sap.it.api.keystore.KeystoreService
import com.sap.it.api.keystore.exception.KeystoreException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

CpiMsg mplDemo(CpiMsg msg) {
	def mpl = messageLogFactory.getMessageLog(msg)
	mpl.addAttachmentAsString("attachment1", "1234567890\n"*30+"."*30, "text/plain")
	mpl.addAttachmentAsString("attachment2", "<a><b c='1'><![CDATA[ text here ]]><!-- comment --></b></a>", "application/xml")
	throw new javax.script.ScriptException("Modelled exception thrown by us, at function mplDemo(msg)")
	msg
}

CpiMsg mplCatch(CpiMsg msg) {
	def mpl = messageLogFactory.getMessageLog(msg)
	Exception e = msg.properties.CamelExceptionCaught
	String xlog = "$e"
	mpl.addAttachmentAsString("exception", xlog, "text/plain")
	msg.setBody(xlog)
	msg
}

CpiMsg customCatch(CpiMsg msg) {
	Exception e = msg.properties.CamelExceptionCaught
	msg.setBody(e.toString())
	msg
}

CpiMsg bus0(CpiMsg msg) {
	msg.properties.log002.putSftp("bus0", msg.properties.bus0)
	msg
}

CpiMsg bus1(CpiMsg msg) {
	msg.properties.log002.logHttpResponse(msg)
	msg.properties.log002.putSftp("bus1", msg.properties.bus1)
	msg
}

CpiMsg bus2(CpiMsg msg) {
	msg.properties.log002.logHttpResponse(msg)
	msg.properties.log002.putSftp("bus2", msg.properties.bus2)
	msg
}

CpiMsg bus3(CpiMsg msg) {
	msg.properties.log002.logHttpResponse(msg)
	msg.properties.log002.putSftp("bus3", msg.properties.bus3)
	msg
}

