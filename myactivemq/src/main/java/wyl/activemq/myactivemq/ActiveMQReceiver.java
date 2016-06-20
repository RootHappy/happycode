package wyl.activemq.myactivemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class ActiveMQReceiver {

	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ActiveMQConnectionFactory();
		
		Connection conn = factory.createConnection();
		
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination dest = session.createQueue("SampleQueue");
		
		MessageConsumer consumer = session.createConsumer(dest);
		
		Message msg = consumer.receive();
		
		System.out.println(msg.toString());
		
		conn.close();
		
	}

}
