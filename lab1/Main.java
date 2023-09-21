import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задания: ");
         int zdn = in.nextInt();
         if (zdn==1){zadanie_1();}
         if (zdn==2){zadanie_2();}
         if (zdn==3){zadanie_3();}
         if (zdn==4){zadanie_4();}
         if (zdn==5){zadanie_5();}
    }
    public static void zadanie_1(){
        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        int num = in.nextInt();
        int k=0;
        while (num!=1){
            if (num%2==0){
                num=num/2;
                k+=1;
            } else if (num%2!=0){
                num=3*num+1;
                k+=1;
            }
        }
        System.out.println(k);
    }
    public static void zadanie_2(){
        Scanner in = new Scanner(System.in);
        System.out.println("введите набор чисел:");
        int num = in.nextInt();
        int[] mass = new int[num];
        int otvet = 0;

        for (int i = 0; i < num; i++) {
            int n = in.nextInt();
            if (i%2==0){
                otvet += n;
            } else {
                otvet -= n;
            }
        }
        System.out.println(otvet);
    }
    public static void zadanie_3(){
        Scanner in = new Scanner(System.in);
        int x_0 = 0;
        int y_0 = 0;
        int count = 0;
        int otvet = 0;
        int flag = 0;
        int n = 0;
        String k = "";
        System.out.println("Введите координаты клада: ");
        int x_1 = in.nextInt();
        int y_1 = in.nextInt();
        System.out.println("Вводите направление и количество шагов: ");

        while (true) {
            k = in.next();
            if (k.equals("стоп")) {
                break;
            }
            n = in.nextInt();
            in.nextLine();
            if (k.equals("север")) {
                y_0 += n;
                count += 1;
            } else if (k.equals("юг")) {
                y_0 -= n;
                count += 1;
            } else if (k.equals("запад")) {
                x_0 -= n;
                count += 1;
            } else if (k.equals("восток")) {
                x_0 += n;
                count += 1;
            }

            if (x_0 == x_1 && y_0 == y_1) {
                flag += 1;
                if (flag == 1) {
                    otvet = count;
                }
            }
        }
        System.out.println(otvet);

    }

    public static void zadanie_4(){
        Scanner in = new Scanner(System.in);
        int min_height = 10000;
        int flag = -1;
        int count = 0;
        int max_height = 0;
        System.out.println("Введите количество дорог: ");
        int dorogi = in.nextInt();
        int[] arr = new int[dorogi];
        for (int i = 0; i < dorogi; i++) {
            System.out.println("Введите количество туннелей в дороге номер " + (i+1) + ":");
            int tunelli = in.nextInt();
            min_height = 10000;
            flag += 1;
            System.out.println("Вводите высоту каждого туннеля по порядку: ");
            for (int j = 0; j < tunelli; j++) {
                int height_tunell = in.nextInt();
                min_height = Math.min(height_tunell, min_height);
            }
            arr[flag] = min_height;
        }

        for (int i = 0; i < dorogi; i ++) {
            if (max_height < arr[i]) {
                max_height = arr[i];
                count = i+1;
            }
        }
        System.out.println(count);
        System.out.println(max_height);

    }
    public static void zadanie_5(){
        Scanner in = new Scanner(System.in);
        System.out.println("введите число: ");
        int num = in.nextInt();
        int a=num/100, b=(num%100)/10, c=num%10;
        if ((a*b*c)%2==0 && (a+b+c)%2==0){
            System.out.println("Число является дважды четным");
        }
        else{
            System.out.println("Число не является дважды четным");
        }
    }
}
