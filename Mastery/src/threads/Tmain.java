package threads;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Tmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Test ts = new Test();
		//Cods cd = new Cods();
		//ll();	
		kkk();
		
		
	}
	public static void dis(Object aa) {
		System.out.println(aa);
	}
	
	public static void ll() {
		String[] aa = {"ELD","ARJAY","JESS","JULs"};
		Emp[] emp = new Emp[aa.length];
		for(int i=0; i < aa.length; i++) {
			emp[i] = new Emp(aa[i]);
		}
	}
	
	public static void kkk() {
		
		String[] as = {"a","b","c","d","e"};
		List<String> aaa = new ArrayList<>();
		
		for(String ko: as){
			aaa.add(ko);
			
		}
		
		aaa.forEach(System.out::print);
		
		dis(aaa);
		dis(Arrays.deepToString(as));
		
		
	}
	
	

}
