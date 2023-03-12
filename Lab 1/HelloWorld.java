public class HelloWorld {
    public static void main(String args[]) {
         System.out.println("Hello world!");
         String[] languages=new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
         for (String i : languages) {
             System.out.print(i + " ");
         }
         System.out.println();
         int n = (int) (Math.random() * 1_000_000);
         System.out.println(n);
         n=n*3;
         n=n+0b10101;
         n=n+0xFF;
         n=n*6;
         System.out.println(n);
         int x=n;
         while(x>=10)
         {
            n=0;
            while(x!=0)
                {
                    n=n+x%10;
                    x=x/10;
                }
            x=n;    
         }
         System.out.println(n);
         System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
     }
   
    }