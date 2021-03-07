package org.arunsiddharth.Domain;

public class Point {
    public double x;
    public double y;


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