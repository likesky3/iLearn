package org.work.math;

public class CountPrime {
    public int countPrimes2(int n) {
        n = n - 1; // count number less than n
        if (n < 2)
            return 0;
        boolean[] isPrime = new boolean[n + 1];
        isPrime[2] = true;
        for (int i = 3; i <= n; i += 2)
            isPrime[i] = true;
        for (int i = 3; i * i <= n; i = i + 2){
            if (!isPrime[i])
                continue;
            for (int j = i * i; j > 0 && j <= n; j += i) {
                isPrime[j] = false;
            }
        }
        int num = 0;
        for (int i = 2; i <= n; i++) {
            num += isPrime[i] ? 1 : 0;
        }
        return num;
    }
    
    // time limit exceed
    public int countPrimes1(int n) {
        if (n < 2)
            return 0;
        if (n == 2)
            return 1;
        int num = 1;
        for (int i = 3; i <= n; i += 2) {
            if (isPrime(i))
                num++;
        }
        return num;
    }
    
    private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n == 2)
            return true;
        int sqrtOfN = (int)(Math.sqrt(n) + 1);
        for (int i = 2; i <= sqrtOfN; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
