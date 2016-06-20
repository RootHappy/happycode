package wyl.activemq.myactivemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class ActiveMQSample {

	public static void main(String[] args) throws Exception {
		ConnectionFactory connFactory = new ActiveMQConnectionFactory();
		
		Connection conn = connFactory.createConnection();
		
		conn.start();
		
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination dest = session.createQueue("SampleQueue");
		
		MessageProducer prod = session.createProducer(dest);
		
		Message msg = session.createTextMessage("Simples Assim");
		
		prod.send(msg);
		
		conn.close();

	}

}
