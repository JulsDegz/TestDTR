package inher;


import java.util.Scanner;

public class Encaps {

	private String str;
	private String st = "Kiko";
//	ArrayList<String> cars = new ArrayList<String>(); 
	String[] aa = {"12","15","10"};
	Scanner sc = new Scanner(System.in);
	
	
	public void gettxt() {
		
		System.out.println("NAME : ");
		settext(sc.nextLine());
	}
	
	public void settext(String obj) {
		this.st = obj;
		diss();
	}
	//from String number to Int array
	public int alist(){
		int k = 0;
		int [] bb = new int[aa.length];
		for(int i=0; i<aa.length; i++) {bb[i] = Integer.parseInt(aa[i]);}
		for(int cc : bb) {
			k = k + cc;
		}
		return k;
	}
	
	public void diss() {
		System.out.println("Your name is " + str);
		System.out.println(alist());
		
	}

	

	
	
	
	
	
	
	
	
	
	
	
}
