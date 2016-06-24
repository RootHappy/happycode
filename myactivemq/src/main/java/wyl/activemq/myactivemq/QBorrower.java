package wyl.activemq.myactivemq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.MapMessage;
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

public class QBorrower {
	
	private QueueConnection qConnect = null;
	private QueueSession qSession = null;
	private Queue responseQ	= null;
	private Queue requestQ = null;
	
	public QBorrower(String queueCf, String requestQueue, String responseQueue){
		try {
			Context cxt = new InitialContext();
			QueueConnectionFactory qFactory = (QueueConnectionFactory) cxt.lookup(queueCf);
			qConnect = qFactory.createQueueConnection();
			qSession = qConnect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			requestQ = (Queue)cxt.lookupLink(requestQueue);
			responseQ = (Queue)cxt.lookup(responseQueue);
			qConnect.start();
		}catch(JMSException jmse) {
			jmse.printStackTrace();
			System.exit(1);
		}catch(NamingException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void sendLoanRequest(double salary, double loanAmt) {
		try {
			MapMessage msg = qSession.createMapMessage();
			msg.setDouble("Salary", salary);
			msg.setDouble("LoanAmount", loanAmt);
			msg.setJMSReplyTo(responseQ);
			
			QueueSender qSender = qSession.createSender(requestQ);
			qSender.send(msg);
			
			String filter = "JMSCorrelationID='" + msg.getJMSCorrelationID() + "'";
			
			QueueReceiver qReceiver = qSession.createReceiver(responseQ,filter);
			TextMessage tmsg = (TextMessage)qReceiver.receive(30000);
			if(tmsg == null) {
				System.out.println("QLender not responding.");
			}else {
				System.out.println("Loan request was " + tmsg.getText());
			}
		}catch(JMSException e) {
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
		String responseq = null;
		
		if(args.length  == 3) {
			queuecf = args[0];
			requestq = args[1];
			responseq = args[2];
		}else {
			System.out.println("Invalid arguments.Should be:");
			System.out.println("java QBorrower factory requestQueue responseQueue");
			System.exit(0);
		}
		QBorrower borrower = new QBorrower(queuecf, requestq, responseq);
		
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("QBorrower Application Starated.");
			System.out.println("Press enter to quit application.");
			System.out.println("Enter: Salary, Loan_Amount");
			System.out.println("\ne.g. 50000, 120000");
			while(true){
				System.out.println(">");
				String loanRequest = stdin.readLine();
				if(loanRequest == null || loanRequest.trim().length() <=0 ) {
					borrower.exit();
				}
				StringTokenizer st = new StringTokenizer(loanRequest, ",");
				double salary = Double.valueOf(st.nextToken().trim()).doubleValue();
				double loanAmt = Double.valueOf(st.nextToken().trim()).doubleValue();
				borrower.sendLoanRequest(salary, loanAmt);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
