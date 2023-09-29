import java.util.Scanner;
import java.util.Arrays;
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
        if (zdn==6){zadanie_6();}
        if (zdn==7){zadanie_7();}
        if (zdn==8){zadanie_8();}



    }
    public static void zadanie_1(){
        Scanner in = new Scanner(System.in);
        String stroka = in.nextLine();
        int maxLength = 0;
        int start = 0;
        int end = 0;
        int n = stroka.length();
        boolean[] visited = new boolean[256];

        while (start < n && end < n) {
            char currentChar = stroka.charAt(end);
            if (!visited[currentChar]) {
                visited[currentChar] = true;
                end++;
                maxLength = Math.max(maxLength, end - start);
            }
            else {
                visited[stroka.charAt(start)] = false;
                start++;
            }
        }
        System.out.println(maxLength);

    }
    public static void zadanie_2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элементы первого массива через пробел: ");
        String input1 = scanner.nextLine();
        int[] arr1 = Arrays.stream(input1.split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.print("Введите элементы второго массива через пробел: ");
        String input2 = scanner.nextLine();
        int[] arr2 = Arrays.stream(input2.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] mergedArray = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                mergedArray[k++] = arr1[i++];
            } else {
                mergedArray[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            mergedArray[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            mergedArray[k++] = arr2[j++];
        }
        System.out.println(Arrays.toString(mergedArray));
    }

    public static void zadanie_3(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элементы массива через пробел: ");
        String input1 = scanner.nextLine();
        int[] nums = Arrays.stream(input1.split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        System.out.println(maxSum);


    }




    public static void zadanie_4(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        int[][] array = new int[rows][cols];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Изначальный массив:");
        for (int[] row : array) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        int rows1 = array.length;
        int cols1 = array[0].length;

        int[][] rotatedArray = new int[cols1][rows1];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                rotatedArray[j][rows1 - i - 1] = array[i][j];
            }
        }
        System.out.println("Повернутый массив:");
        for (int[] row : rotatedArray) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void zadanie_5(){
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        int[] array = Arrays.stream(input1.split(" ")).mapToInt(Integer::parseInt).toArray();
        int target = scanner.nextInt();

        int[] pair = null;
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] + array[j] == target) {
                    pair = new int[]{array[i], array[j]};
                    break;
                }
            }
            if (pair != null) {
                break;
            }
        }

        if (pair != null) {
            System.out.println("Найдена пара элементов: " + pair[0] + ", " + pair[1]);
        }
        else {
            System.out.println("Пара элементов не найдена.");
        }

    }

    public static void zadanie_6(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        int[][] array = new int[rows][cols];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum += array[i][j];
            }
        }
        System.out.println(sum);

    }

    public static void zadanie_7(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        int[][] array = new int[rows][cols];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = scanner.nextInt();
            }

        }





        int[] maxElements = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int max = array[i][0];
            for (int j = 1; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
            maxElements[i] = max;
        }



        System.out.println("Максимальные элементы в каждой строке:");
        for (int i = 0; i < maxElements.length; i++) {
            System.out.println("Строка " + (i + 1) + ": " + maxElements[i]);
        }


    }
    public static void zadanie_8() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        int[][] array = new int[rows][cols];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Изначальный массив:");
        for (int[] row : array) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        int rows1 = array.length;
        int cols1 = array[0].length;

        int[][] rotatedArray = new int[cols1][rows1];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                rotatedArray[cols1 - j - 1][i] = array[i][j];
            }
        }
        System.out.println("Повернутый массив:");
        for (int[] row : rotatedArray) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }


}
