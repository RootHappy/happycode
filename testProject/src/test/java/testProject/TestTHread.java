package testProject;

public class TestTHread {

	public static void main(String[] args) {
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++) {
					System.out.println("A");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++) {
					System.out.println("B");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++) {
					System.out.println("C");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		threadA.start();
		threadB.start();
		threadC.start();
	}

}
