package com.wang.zookeeperTest;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperBasicAPITest {
	private final static String ZK_PORT = "2181";

	public static void main(String[] args) throws IOException {
		ZooKeeper zk = new ZooKeeper("localhost:"+ ZK_PORT, 3000, new Watcher(){
			@Override
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！");
			}
		});
		try {
			zk.create("/testRootPath", "TestRootPath".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/testRootPath/testChildPathOne", "TestChildDataOne".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(new String(zk.getData("/testRootPath", false, null)));
			System.out.println(zk.getChildren("/testRootPath", true));
			zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
			System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");
			zk.create("/testRootPath/testChildPathTwo", "TestChildDataTwo".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true,null)));
			zk.delete("/testRootPath/testChildPathOne", -1);
			zk.delete("/testRootPath/testChildPathTwo", -1);
			zk.delete("/testRootPath", -1);
			zk.close();
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
