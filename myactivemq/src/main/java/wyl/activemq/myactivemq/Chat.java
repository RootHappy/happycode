package wyl.activemq.myactivemq;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;


public class Chat implements javax.jms.MessageListener{
	
	private TopicConnection connection;
	private TopicSession pubSession;
	private TopicPublisher publisher;
	private String username;
	
	public Chat(String topicFactory,String topicName,String username) throws Exception{
		InitialContext ctx = new InitialContext();
		
		TopicConnectionFactory connfactory = (TopicConnectionFactory)ctx.lookup(topicFactory);
		TopicConnection connection = connfactory.createTopicConnection();
		
		TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic chatTopic = (Topic)ctx.lookup(topicName);
		
		TopicPublisher publisher = pubSession.createPublisher(chatTopic);
		TopicSubscriber subscriber = subSession.createSubscriber(chatTopic,null,true);
		
		subscriber.setMessageListener(this);
		
		this.connection = connection;
		this.publisher = publisher;
		this.pubSession = pubSession;
		this.username = username;
		connection.start();
	}

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage)message;
			System.out.println(username + ": " + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	protected void writeMessage(String text)throws JMSException {
		TextMessage msg = pubSession.createTextMessage();
		msg.setText(text);
		publisher.publish(msg);
	}
	
	public void close()throws JMSException{
		connection.close();
	}
	
	public static void main(String[] args) {
		try {
			if(args.length != 3) {
				System.out.println("Factory,Topic,or username missing");
				System.exit(1);
			}
			Chat chat = new Chat(args[0],args[1],args[2]);
			BufferedReader commandLine = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				String s = commandLine.readLine();
				if(s.equalsIgnoreCase("exit")) {
					chat.close();
					System.exit(0);
				}else{
					chat.writeMessage(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
