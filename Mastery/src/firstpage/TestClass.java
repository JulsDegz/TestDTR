package firstpage;

public class TestClass {
	
	public static void main(String[] args) {
		// byte short int long
		//float double
		
		int a = 9;
		int p = 1;
		for(int i = 1; i <= a ; i++) {
			
			for(int y = a; y >= i; y--) {
				System.out.print(" ");
			}
			
			for(int j = 1; j < i; j++) {
				System.out.print(j);
		}
			System.out.print(i);
			
			for(int o = i; o >= p; o--) {
				System.out.print(o);
			}
			
			
			
			
			
			System.out.println("");
			
			
		}
		
		int b = 0;
		
		for(int k = 9; b < k ; k--) {
			
			for(int o = 9; o >= k; o --) {
				System.out.print(" ");
			}
			
			
			
			for(int j = 1; j < k ; j++) {
				
				System.out.print(j);
			}
			
			System.out.print(k);

			
			for(int l = k; b < l ; l--) {
				System.out.print(l);
			}
			
			System.out.println("");
			
			
		}
	
		
		
	}
}
