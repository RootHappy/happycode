package wyl.activemq.myactivemq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QLender implements MessageListener{
	
	private QueueConnection qConnect = null;
	private QueueSession qSession = null;
	private Queue requestQ = null;
	
	public QLender(String queueCf, String requestQueue) {
		try {
			Context cxt = new InitialContext();
			QueueConnectionFactory connFactory = (QueueConnectionFactory)cxt.lookup(queueCf);
			qConnect = connFactory.createQueueConnection();
			qSession = qConnect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			requestQ = (Queue)cxt.lookup(requestQueue);
			
			qConnect.start();
			
			QueueReceiver receiver = qSession.createReceiver(requestQ);
			receiver.setMessageListener(this);
			System.out.println("Waiting for loan requests...");
		}catch(JMSException jmse) {
			jmse.printStackTrace();
			System.exit(1);
		}catch(NamingException ne) {
			ne.printStackTrace();
			System.exit(1);
		}		
	}

	public void onMessage(Message message) {
		try {
			boolean accepted = false;
			MapMessage msg = (MapMessage)message;
			double salary = msg.getDouble("Salary");
			double loanAmt = msg.getDouble("LoanAmount");	
			if(loanAmt < 200000) {
				accepted = (salary / loanAmt ) > .25;
			}else{
				accepted = (salary / loanAmt) > .33;
			}
			System.out.println("% = " + (salary / loanAmt) +",loan is " + (accepted ?"Accepted":"Declined" ));
			TextMessage tmsg = qSession.createTextMessage();
			tmsg.setText(accepted ?"Accepted":"Declined" );
			tmsg.setJMSCorrelationID(message.getJMSMessageID());
			
			QueueSender sender = qSession.createSender((Queue)message.getJMSReplyTo());
			sender.send(tmsg);
			System.out.println("\nWaiting for loan requests...");
		}catch(JMSException jmse) {
			jmse.printStackTrace();
			System.exit(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}	
	}
	
	private void exit() {
		try {
			qConnect.close();
		}catch(JMSException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public static void main(String[] args) {
		String queuecf = null;
		String requestq = null;
		if(args.length == 2) {
			queuecf = args[0];
			requestq = args[1];
		}else{
			System.out.println("Invalid arguments. Should be: ");
			System.out.println("Java QLender factory request_queue");
		}		
		QLender lender = new QLender(queuecf, requestq);
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("QLender application started.");
			System.out.println("Press enter to quit application.");
			stdin.readLine();
			lender.exit();	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
