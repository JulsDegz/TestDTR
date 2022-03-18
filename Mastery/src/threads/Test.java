package threads;

public class Test extends Thread {
	
	@Override
	public void run() {
		
		while(true) {
			System.out.println("sss");
			kl();
		try {
			Thread.sleep(1000);			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}}
	
	
	void kl() {
		Thread td = new Thread() {
			
			public void run() {
				
			while(true) {
				System.out.print("123");
				
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
				
				
			}
					
		};
		
		td.start();
	}
	
	

}
