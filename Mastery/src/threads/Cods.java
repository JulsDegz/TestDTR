package threads;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Cods {
	
	
	
	
	public void oo() {
		
		Thread th = new Thread(){
			public void run() {					 
					 try {
						 for(int i=0; i < 10; i++) {
							 
							 for(int k=1; k < i + 1; k++) {
								 
								 System.out.print("a");
							 }

							 System.out.println("a");

							 sleep(1000);
						 }
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		};
		th.start();
		
	}
	
	void vec() {
		
		Vector<String> vtor = new Vector<>();

		vtor.add("Hello");
		vtor.add("Hello");
		vtor.add("Hello");
		vtor.add("Hello");
		vtor.add("Hello");
		vtor.add("Hello");
		vtor.add("Hello");
		vtor.add("Hello");
		vtor.add("Hello");

		System.out.println(vtor.capacity());
		System.out.print(vtor);
		Enumeration<String> enums = vtor.elements();
		
		while(enums.hasMoreElements()) {
			System.out.println(enums.nextElement());
		}
		
	}
	
	void exp() {
		String[] k = {"a","b","c"};
		int m = k.length - 1;
		
		
		try{
			System.out.print(k[4]);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(k[m]);
		}
		
	}
	
	

	public void op() {
		
		int aa = Integer.parseInt("ABC");
		
		System.out.print(aa);
		
	}
	
	public void alist() {
		
		ArrayList<String> a = new ArrayList<>();
		a.add("one");
		a.add("two");
		a.add("one");
		
		ArrayList<String> bb = new ArrayList<>();
		bb.add("two");
		bb.add("one");
		bb.add("tres");
		
		@SuppressWarnings("unchecked")
		ArrayList<String> cc = (ArrayList<String>)a.clone();
		
		cc.addAll(bb);
		
		Iterator<String> itr = cc.iterator();
		
		while(itr.hasNext()){
			
			System.out.println(itr.next());
			
		}
		
		
		//String b = "one";
		
//		ArrayList<String> aa = new ArrayList<>();
//		for(int c = 0; c < bb.size(); c++) {
//		for(String aaa: a) {
//			aa.add(aaa.contains(bb.get(c)) ? "yes" : "no");
//		}
//		System.out.println(aa);
//		aa.removeAll(aa); }
		
		
	
		
	
	}
	public void dd() {

		Scanner s = new Scanner(System.in);
	
		
		
		try {
		Tmain.dis("Number of Students: ");
		int a = s.nextInt();
		Emp[] em = new Emp[a];
		
		s.nextLine();
		String[] fns = new String[a];
		
		if(a <= 0) {Tmain.main(null);}
		
		for(int i=0; i < a; i++) {
			Tmain.dis("Student #" + (i + 1));
			Tmain.dis("First Name :");
			
			fns[i] = s.nextLine();
			
			em[i] = new Emp(fns[i]);
		}
		
		for(int o=0; o < a; o++) {
			
			em[o].emp();
		}
		
		
		}catch(InputMismatchException kk) {
			
			Tmain.dis("Invalid value");
			Tmain.main(null);
			
		}
	}
	
	
	public void toup() {

		String as = "asdfghjkl";
		System.out.print(as.toUpperCase().charAt(0));
	}
	
	public void th() {
		
		Thread tt = new Thread() {
			
			public void run() {
				
				while(true) {
					exp();
					try {
						sleep(2000);
						}
						catch(InterruptedException e) {
							System.out.print(e);
						}
				}
				
			}
			
			
		};
		
	tt.start();
	}

}
