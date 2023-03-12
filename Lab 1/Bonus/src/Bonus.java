 class Problema1 {
    public static void main(String[] args) {
        // primul punct
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
            int[][] m=new int[n][n];
            for(int i=0;i<n;i++)
            {
                if(i==0) {
                    m[i][1] = 1;
                    m[i][n-1]=1;
                } else if(i==n-1)
                {
                    m[i][0]=1;
                    m[i][n-2]=1;
                }
                else
                {
                    m[i][i-1]=1;
                    m[i][i+1]=1;
                }
            }

            int[][] result=new int[n][n];
            for(int i=0;i<n;i++)
                result[i]=m[i].clone();
            System.out.println("Matricea de adiacenta: ");
            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++)
                    System.out.print(result[i][j]+" ");
                System.out.println(" ");
            }
            for(int p=2;p<=n;p++)
            {
                int[][] c=new int[n][n];
                for(int i=0;i<n;i++)
                    for(int j=0;j<n;j++)
                    {
                        c[i][j]=0;
                        for(int k=0;k<n;k++)
                            c[i][j]=c[i][j]+result[i][k]*m[k][j];
                    }
                for(int i=0;i<n;i++)
                    result[i]=c[i].clone();
                System.out.println("Puterea a "+p+" a matricei de adiacenta:");
                for (int i = 0; i < n; i++) {

                    for (int j = 0; j < n; j++)
                        System.out.print(result[i][j]+" ");
                    System.out.println(" ");
                }

            }


        }
        //fiecare putere k a matricei ne arata cate mersuri  de lungime k exista intre fiecare doua noduri
        // conform liniei si coloanei, de aceea aceste matrici sunt simetrice



    }
}


class Problema2
{
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        if (((n * d) % 2) == 1) {
            System.out.println("Nu exista un astfel de graf.");

        } else if ((n * d) >= (n * (n - 1))) {
            System.out.println("Nu exista un astfel de graf.");
        } else {

            int[][] m = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        m[i][j] = 0;
                    } else if (((j - i + n) % n <= d / 2  || (i - j + n) % n <= d/2) && d%2==0) {
                        m[i][j] = 1;
                    } else if (((j - i + n) % n < (d+1)/ 2  || (i - j + n) % n <=(d+1)/2) && d%2==1) {
                        m[i][j] = 1;
                    } else {
                        m[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(m[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}