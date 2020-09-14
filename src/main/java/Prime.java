import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by caorl on 2020/9/14.
 */
public class Prime {
    // 判断整数 n 是否是素数
    public static boolean isPrime(int n) {

        //i*i时间复杂度降为 O(sqrt(N))
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    //粗暴循环方式
    public static int sumPrimes(int n) {
        long startTime = System.currentTimeMillis();
        List<Integer> primes = new ArrayList<Integer>() ;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        int sum = 0;
        for (Integer prime:primes){
            sum+=prime;
        }
        System.out.println("小于"+n+"的所有素数和（暴力循环）结果是："+sum);
        System.out.println("小于"+n+"的所有素数和（暴力循环）耗时："+(System.currentTimeMillis()-startTime));
        return sum;
    }

    public static int sumPrimes2(int n) {
        long startTime = System.currentTimeMillis();
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++){
            if (isPrim[i]){
                for (int j = i * i; j < n; j += i){
                    isPrim[j] = false;
                }
            }

        }
        int sum = 0;
        for (int i = 2; i < n; i++){
            if (isPrim[i]){
                sum+=i;
            }
        }
        System.out.println("小于"+n+"的所有素数和（优化）结果是："+sum);
        System.out.println("小于"+n+"的所有素数和（优化）耗时："+(System.currentTimeMillis()-startTime));
        return sum;
    }

    public static int userInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要大于0的数字：");
        String num = sc.nextLine();
        return Integer.valueOf(num);
    }



    public static void main(String[] args)  {
        sumPrimes(200000);
        sumPrimes2(200000);
        boolean hadSum = false;
        while (!hadSum){
            int num=0;
            try {
                num = userInput();
                if (num<=0){
                    throw new RuntimeException("数值必须大于0");
                }
            }catch (RuntimeException e){
                System.out.println("!!!输入结果有误，请重新输入!!!");
                num = userInput();
            }
            sumPrimes2(num);
            hadSum=false;
        }

    }
}
