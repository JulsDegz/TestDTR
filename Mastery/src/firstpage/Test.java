package firstpage;

import packed.operationss;

public class Test {

	public static void main(String[] args) {
		Test t = new Test();
		operationss op1 = new operationss("Julmar", "Male");
		operationss op2 = new operationss("Jessel", "FEmale");
		
		// TODO Auto-generated method stub

		//op1.pp(op2);
		t.res(op1,op2);
	}
	
	
	
	
	public void res(operationss op, operationss op2) {
		
		op.pp(op2);
		
	}

}
