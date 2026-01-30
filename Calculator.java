import java.util.Scanner;

public class Calculator {
    public int num1,num2,op;
    public int addNums(int n1,int n2){
        return n1+n2;
    }
    public int subNums(int n1,int n2){
        return n1-n2;
    }
    public long mulNums(int n1,int n2){
        return n1*n2;
    }
    public float divNums(int n1,int n2){
        return n1/n2;
    }
    public int modNums(int n1,int n2){
        return n1%n2;
    }

    
    public static void main(String[] args){
        Calculator c = new Calculator();
        Scanner scan = new Scanner(System.in);
        int flag=1;
        while(flag == 1){
        System.out.println( "Choose one of the followiung:");
        System.out.println("1. Addition.");
        System.out.println("2. Subtraction. ");
        System.out.println("3. Multiplication.");
        System.out.println("4. Division.");
        System.out.println("5. Modulus.");
        System.out.println("0. Exit");
        System.out.println(" Enter choice:");
        c.op = scan.nextInt();

        if(c.op==0){
            flag = 0;
            break;
        }
        System.out.println( "Enter first number:");
        c.num1 = scan.nextInt();
        System.out.println( "Enter second number:");
        c.num2 = scan.nextInt();
        switch(c.op){
            case 1:
                int a = c.addNums(c.num1,c.num2);
                System.out.println(a);
                break;
            case 2:
                int b = c.subNums(c.num1,c.num2);
                System.out.println(b);
                break;
            case 3:
                long m = c.mulNums(c.num1,c.num2);
                System.out.println(m);
                break;
            case 4:
                float d = c.divNums(c.num1,c.num2);
                System.out.println(d);
                break;
            case 5:
                int mod = c.modNums(c.num1,c.num2);
                System.out.println(mod);
                break;     
            default:
                System.out.println("Invalid Choice.");  
        }
    }
    scan.close();
    }
        
}
