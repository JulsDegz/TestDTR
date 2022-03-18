package inher;

import java.util.ArrayList;

public class Mym {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer>  ar = new ArrayList<>();
		int[][] aa = {
				{1,2,3},		
				{4,5,6},		
				{4,5,6},		
				{7,8,9}	
				};
		
		
		
	for(int i = 0 ; i < aa.length ; i++) {
		for(int j = 0; j < aa[i].length ; j++) {
			
			ar.add(aa[i][j]);
		}
	}
	
	
	ss(ar);
	
	
	//String sentence = "A self-starter, who's is eager on learning new things to improved myself as a developer. I have a very strong understanding of the core concept in JAVA language as well as PHP, JAVASCRIPT and JQUERY. I'm also confident with my logical and problem solving skills;";
	//long kk = sentence.chars().count();
	//ss(kk);
	
	
	
//	ss(aa.length);//ROWS
//	ss(aa[0].length); //COLUMN
	
//		Encaps en = new Encaps();
//		en.gettxt();
//		en.settext("juls");
//		Subm sm = new SubTwo("JULMAR","Degojas",27);
//		Subm sd = new Subm("JULMAE","DEGS");
//		sd.disp();
//		sm.disp();
		
//		abs ab = new Left();
//		abs ab1 = new Rright();
//		
//		abs[] aa = {ab,ab1};
//		aa[0].hw();
//		aa[1].hw();
		

		
		
	}
	
	static void ss(Object b) {
		System.out.println(b);
		
	}
	
	
	

}
