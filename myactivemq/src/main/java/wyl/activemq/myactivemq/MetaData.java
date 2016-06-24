package wyl.activemq.myactivemq;

import java.util.Enumeration;

import javax.jms.ConnectionMetaData;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MetaData {
	
	public static void main(String[] atgs) {
		try {
			Context cxt = new InitialContext();
			QueueConnectionFactory connFactory = (QueueConnectionFactory)cxt.lookup("TopicCF");
			QueueConnection conn = connFactory.createQueueConnection();
			ConnectionMetaData meta = conn.getMetaData();
			System.out.println("JMS version: " + meta.getJMSMajorVersion() + "." + meta.getJMSMinorVersion());
			System.out.println("JMS Provider: " +meta.getJMSProviderName());
			System.out.println("JMSX Properties Suppored : ");
			Enumeration e = meta.getJMSXPropertyNames();
			while(e.hasMoreElements()) {
				System.out.println(" " + e.nextElement());
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
