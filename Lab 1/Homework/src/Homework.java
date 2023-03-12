public class Homework {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        boolean success=false;
        int n=0;
        try
        {
            n=Integer.parseInt(args[0]);
            success=true;
            System.out.println(args[0] + " is an integer.");
        }
        catch (NumberFormatException e)
        {
            System.out.println(args[0] + " is not an integer.");

        }
        if(success)
        {

                int[][] m = new int[n][n];
                for (int i = 0; i < n; i++) {
                    m[0][i] = i + 1;
                }
                for (int i = 1; i < n; i++) {
                    m[i][0] = m[i - 1][n - 1];
                    for (int j = 1; j < n; j++)
                        m[i][j] = m[i - 1][j - 1];
                }
                if(n<500)
                {
                    for (int i = 0; i < n; i++) {

                        for (int j = 0; j < n; j++)
                            System.out.print(m[i][j]+" ");
                        System.out.println(" ");
                    }
                }
                if(n<500)
                    System.out.println(" ");
                for(int i=0;i<n;i++) {
                    String s=new String("");

                    for (int j = 0; j < n; j++) {
                        s = s + m[i][j];
                    }
                    if(n<500)
                    System.out.println("Linia "+i+": "+s);
                }
                if(n<500)
                    System.out.println(" ");
                for(int i=0;i<n;i++) {
                    String s=new String("");

                    for (int j = 0; j < n; j++) {
                        s = s + m[j][i];
                    }
                    if(n<500)
                        System.out.println("Coloana "+i+": "+s);
                }

            long endTime=System.currentTimeMillis();
             System.out.println();
             System.out.print("Timpul este:");
            System.out.println(endTime-startTime);
        }

    }
}