package org.arunsiddharth.BinarySearch;

public class BinarySearch {
    
    public static <T extends Comparable<T>> int binarySearch(T[] array, T key){
        int low = 0;
        int high = array.length-1;
        while(low<=high){
            int mid = low+(high-low)/2;
            if(array[mid].compareTo(key)==0){
                return mid;
            }else if(array[mid].compareTo(key)<0){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearchRecursive(T[] array, T key, int low, int high){
        if(low>high)return -1;
        int mid = low + (high-low)/2;
        if(array[mid].compareTo(key)==0){
            return mid;
        }else if(array[mid].compareTo(key)<0){
            return binarySearchRecursive(array, key, mid+1, high);
        }else{
            return binarySearchRecursive(array, key, low, mid-1);
        }
    }

    /**
     * Code for lower bound or ceiling (>=)
     * @param <T>
     * @param array
     * @param key
     * @return
     */
    public static <T extends Comparable<T>> int ceiling(T[] array, T key){
        int low = 0;
        int high = array.length-1;
        int result = array.length;
        while(low<=high){
            int mid = low + (high - low)/2;
            if(array[mid].compareTo(key)>=0){
                result=mid;
                high=mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return result;
    }


    /**
     * Code for (<=)
     * @param <T>
     * @param array
     * @param key
     * @return
     */
    public static <T extends Comparable<T>> int floor(T[] array, T key){
        int low = 0;
        int high = array.length-1;
        int result = -1;
        while(low<=high){
            int mid = low + (high - low)/2;
            if(array[mid].compareTo(key)<=0){
                result=mid;
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return result;
    }


    /**
     * uppper bound or upper(>)
     * @param <T>
     * @param array
     * @param key
     * @return
     */
    public static <T extends Comparable<T>> int higher(T[] array, T key){
        int low = 0;
        int high = array.length-1;
        int result=array.length;
        while(low<=high){
            int mid = low + (high - low)/2;
            if(array[mid].compareTo(key)<=0){
                low = mid+1;
            }else{
                result=mid;
                high = mid-1;
            }
        }
        return result;
    }

    /**
     * Find value < than given value
     * @param <T>
     * @param array
     * @param key
     * @return
     */
    public static <T extends Comparable<T>> int lower(T[] array, T key){
        int low = 0;
        int high = array.length-1;
        int result=-1;
        while(low<=high){
            int mid = low + (high - low)/2;
            if(array[mid].compareTo(key)>=0){
                high=mid-1;
            }else{
                result=mid;
                low=mid+1;
            }
        }
        return result;
    }

    public static <T extends Comparable<T>> int upperBound(T[] array, T key){
        int low = 0;
        int high = array.length;
        while(low<high){
            int mid = low + (high-low)/2;
            if(array[mid].compareTo(key)<=0){
                low=mid+1;
            }else{
                high=mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int lowerBound(T[] array, T key){
        int low = 0;
        int high = array.length;
        while(low<high){
            int mid = low + (high - low)/2;
            if(array[mid].compareTo(key)<0){
                low = mid+1;
            }else{
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int findOccurrence(T[] array, T key, boolean firstOccurrence){
        int low = 0;
        int high = array.length-1;
        int result=-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(array[mid].compareTo(key)==0){
                result=mid;
                if(firstOccurrence){
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }else if(array[mid].compareTo(key)<0){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return result;
    }


    public static <T extends Comparable<T>> int findRotationCount(T[] array){
        int low = 0;
        int high = array.length-1;
        while(low<=high){
            int mid = low + (high - low)/2;
            T left = array[(array.length+mid-1)%array.length];
            T right = array[(array.length+mid+1)%array.length];
            if(array[low].compareTo(array[high])<=0){
                return low;//Case 1 segment is sorted
            }
            else if(array[mid].compareTo(left)<=0 && array[mid].compareTo(right)<=0){
                return mid;//case 2 pivot property
            }else if(array[mid].compareTo(array[high])<=0){
                high=mid-1;//case 3 right side of segment or little segment
            }else{
                low=mid+1;//case 4 left side of segment or high segment
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> int findInCircularSorted(T[] array, T key){
        int low = 0;
        int high = array.length-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(array[mid].compareTo(key)==0){//Case 1: we find the key
                return mid;
            }else if(array[mid].compareTo(array[high])<=0){//Case 2: Right side of segment (Unsorted+Mid+Sorted)
                if(key.compareTo(array[mid])>0 && key.compareTo(array[high])<=0){//Case 2A: Go to Sorted 
                    low = mid+1;
                }else{//Case 2B: Go to Unsorted
                    high = mid-1;
                }
            }else if(array[mid].compareTo(array[low])>=0){//Case 3: Left side of segment (Sorted+mid+Unsorted)
                if(key.compareTo(array[low])>=0 && key.compareTo(array[mid])<0){
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }
        }
        return -1;
    }

    public static double findMedianTwoSortedArrays(int[] a, int[] b){
        if(a.length>b.length){
            return findMedianTwoSortedArrays(b, a);
        }
        int x = a.length;
        int y = b.length;
        int low, high;
        low = 0;
        high = a.length;
        while(low<=high){
            int partitionX = low + (high-low)/2;
            int partitionY = (x+y+1)/2-partitionX;
            int leftX = partitionX==0?Integer.MIN_VALUE:a[partitionX-1];
            int rightX = partitionX==x?Integer.MAX_VALUE:a[partitionX];
            int leftY = partitionY==0?Integer.MAX_VALUE:b[partitionY-1];
            int rightY = partitionY==y?Integer.MAX_VALUE:b[partitionY];
            if(leftX<=rightY && leftY<=rightX){
                if((x+y)%2==0){
                    return ((double)Math.max(leftX, leftY)+Math.min(rightX, rightY))/2;
                }else{
                    return Math.max(leftX, leftY);
                }
            }else if(leftX>rightY){
                high = partitionX-1;
            }else{
                low = partitionX+1;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args){
        int[] a1 = {23, 26, 31, 35};
        int[] a2 = {3, 5, 7, 9, 11, 16};
        System.out.println(findMedianTwoSortedArrays(a1, a2));
    }
}
