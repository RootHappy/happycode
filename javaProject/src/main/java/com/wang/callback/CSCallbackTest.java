package com.wang.callback;

public class CSCallbackTest {

	public static void main(String[] args) {
		Server server = new Server();
		Client client = new Client(server);
		client.sendMsg("王云龙");
	}

}
