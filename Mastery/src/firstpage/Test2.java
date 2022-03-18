package firstpage;


import java.util.ArrayList;
import java.util.Scanner;

import packed.*;


public class Test2 {

	
	public static void main(String[] args) {
		
				
				Scanner sc = new Scanner(System.in);
				prints("NAme : ");
				String n = sc.nextLine();
				prints("Age : ");
				String aa = sc.nextLine();
				prints("YOur  name is " + n + " and you are " + aa);
				
				
				Test2 t = new Test2();
				String[] ll = {"jessel","jesmar","baydo","juls","degojas"};
				ArrayList<String> kp = t.alist(ll);
				kp.add(n);
				System.out.print(kp);
				
				
				
				
				operationss op = new operationss("Julmar", "Male");			
	}
	
	
	
	public ArrayList<String> alist(String[] pp){
		ArrayList<String> listss = new ArrayList<>();
		for(String j: pp) {
			listss.add(j);
		}
		return listss;
		
	}
	
	
	
	public int numbs(int[] a) {
		int k = 0;
		for(int i = 0 ; i < a.length; i++) {
			k = k + a[i];
		}
		return k;
	}
	
	public String forEach(String[] oldval) {
		String k = " ";
		
		for(String newval : oldval) {
			
			k = k + " " + newval;
			
		}
		
		
		return k;
		
	}

	public static void prints(String vals) {
		
		System.out.print(vals);
	}
}


