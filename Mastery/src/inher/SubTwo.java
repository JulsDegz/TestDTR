package inher;

public class SubTwo extends Subm{
	
	int age;
	
	SubTwo(String a, String b, int age){
		
		super(a,b);
		
		this.age = age;
	}
	
	
	@Override
	public void disp() {
		
		//super.disp();
		System.out.println(this.b );
		System.out.print("age : " + this.age);
	
	}
	

}
