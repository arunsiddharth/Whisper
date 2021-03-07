package org.arunsiddharth.Arrays;
import java.util.Arrays;
import java.util.List;
import java.nio.charset.CoderResult;
import java.util.ArrayList;


class Point {
    double x;
    double y;


    public static Point subtract(Point a, Point b){
        Point temp = new Point();
        temp.x = a.x-b.x;
        temp.y = a.y-b.y;
        return temp;
    }

    public static double crossProduct(Point a, Point b){
        return a.x*b.y-a.y*b.x;
    }
}


public class Math {
    static boolean[] sieve;

    public static double areaOfPolygon(Point[] points){
        double area = 0;
        int n = points.length;
        for(int i=0;i<n;i++){
            area+=Point.crossProduct(points[i], points[(i+1)%n]);
        }
        return area;
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

    public static void main(String[] args){

    }
}
