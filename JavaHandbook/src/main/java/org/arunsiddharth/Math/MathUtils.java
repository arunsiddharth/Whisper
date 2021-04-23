package org.arunsiddharth.Math;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.arunsiddharth.Domain.Point;


public class MathUtils {
    static boolean[] sieve;

    public static long pow(long a, long b, long mod){
        long result = 1;
        while(b>0){
            if(b%2==1)result = (result*a)%mod;
            a = (a*a)%mod;
            b/=2;
        }
        return result;
    }

    public static double areaOfPolygon(Point[] points){
        double area = 0;
        int n = points.length;
        for(int i=0;i<n;i++){
            area+=Point.crossProduct(points[i], points[(i+1)%n]);
        }
        return Math.abs(area/2.0);
    }    

    public static int directionOfPoint(Point a, Point b, Point p){
        b = Point.subtract(b, a);
        p = Point.subtract(p, a);
        double cp = Point.crossProduct(b, p);
        if(cp==0){
            return 0;//ON THE LINE
        }else if(cp>0){
            return 1;//LEFT OF LINE
        }else{
            return -1;//RIGHT OF LINE
        }
    }

    public static boolean isPrime(int num){
        if(num<2)return false;
        for(int i=2;i*i<=num;i++){
            if(num%i==0)return false;
        }
        return true;
    }

    public static void initSieve(int num){
        sieve = new boolean[num+1];
        Arrays.fill(sieve, true);
        sieve[0]=sieve[1]=false;
        for(int i=2;i*i<=num;i++){
            if(sieve[i]){
                for(int j=2;i*j<=num;j++){
                    sieve[i*j]=false;
                }
            }
        }
    }


    public static List<Integer> getPrimeFactors(int num){
        List<Integer> factors = new ArrayList<>();
        for(int i=2;i*i<=num;i++){
            if(num%i==0){
                while(num%i==0){
                    factors.add(i);
                    num/=i;
                }
            }
        }
        if(num!=1)factors.add(num);
        return factors;
    }

    public static List<Integer> getDivisors(int num){
        List<Integer> divisors = new ArrayList<>();
        for(int i=1;i*i<=num;i++){
            if(num%i==0){
                divisors.add(i);
                if(num/i!=i)divisors.add(num/i);
            }
        }
        return divisors;
    }


    public static int gcdRecursive(int dividend, int divisor){
        return (divisor==0)?dividend:gcdRecursive(divisor, dividend%divisor);
    }

    public static int gcd(int dividend, int divisor){
        while(divisor!=0){
            int remainder = dividend%divisor;
            dividend = divisor;
            divisor = remainder;
        }
        return dividend;
    }

    public static int[][] mul(int[][] base){
    	int[][] result = new int[2][2];
    	result[0][0] = base[0][0]*base[0][0]+base[0][1]*base[1][0];
    	result[0][1] = base[0][0]*base[0][1]+base[0][1]*base[1][1];
    	result[1][0] = base[1][0]*base[0][0]+base[1][1]*base[1][0];
    	result[1][1] = base[1][0]*base[0][1]+base[1][1]*base[1][1];
    	return result;
    }

    public static int[][] mul2(int[][] base2, int[][] base){
    	int[][] result = new int[2][1];
    	result[0][0] = base[0][0]*base2[0][0]+base[0][1]*base2[1][0];
    	result[1][0] = base[1][0]*base2[0][0]+base[1][1]*base2[1][0];
    	return result;
    }


    public static int nThFibonacci(int A) {
    	if(A==1)return 0;
    	int[][] base = {{1,1},{1,0}};
    	int[][] result = {{1},{0}};
    	while(A>0){
    	    if((A&1)!=0){
    			result = mul2(result, base);
            }
            base = mul(base);
            A>>=1;
        }
        return result[1][0];
    }

    public static void main(String[] args){
        System.out.println(nThFibonacci(6));
    }
}
