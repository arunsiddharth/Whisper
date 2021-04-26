package org.arunsiddharth.BinarySearch;

public class Practice {
    
    public int binarySearch(int[] array, int key){
        int low = 0;
        int high = array.length-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(array[mid] == key){
                return mid;
            }else if(array[mid] < key){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return -1;
    }

    public int binarySearchRecursive(int[] array, int key, int low, int high){
        if(low>high)return -1;
        int mid = low + (high-low)/2;
        if(array[mid]==key){
            return mid;
        }
        else if(array[mid]<key){
            return binarySearchRecursive(array, key, mid+1, high);
        }
        else{
            return binarySearchRecursive(array, key, low, mid-1);
        }
    }

    /**
     * smallest value which is >= key
     * @param array
     * @param key
     * @return
     */
    public int ceiling(int[] array, int key){
        int low = 0;
        int high = array.length-1;
        int result = array.length;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(array[mid]>=key){
                result = mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return result;
    }

    /**
     * largest value which is <= key
     * @param array
     * @param key
     * @return
     */
    public int floor(int[] array, int key){
        int low = 0, high = array.length-1, result = -1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(array[mid]<=key){
                result = mid;
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return result;
    }

    /**
     * smallest value > than key
     * @param array
     * @param key
     * @return
     */
    public int higher(int[] array, int key){
        int low = 0, high = array.length-1, result=array.length;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(array[mid]>key){
                result = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return result;
    }

    /**
     * greatest value < than key
     * @param array
     * @param key
     * @return
     */
    public int lower(int[] array, int key){
        int low = 0, high = array.length-1, result = -1;
        while(low<=high){
            int mid = low+(high-low)/2;
            if(array[mid]<key){
                result = mid;
                low = mid+1;
            }else{
                high=mid-1;
            }
        }
        return result;
    }

    public static int findOccurrence(int[] array, int key, boolean firstOccurrence){
        int low = 0;
        int high = array.length-1;
        int result = -1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(array[mid]==key){
                result = mid;
                if(firstOccurrence){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }else if(array[mid]<key){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return result;
    }


    public static int findRotationCount(int[] array){
        int n = array.length;
        int low = 0, high = n-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            int left = (mid-1+n)%n;
            int right = (mid+1)%n;
            if(array[mid]<=array[right] && array[mid]<=array[left]){
                return mid;
            }else if(array[low]<=array[high]){
                return low;
            }else if(array[mid]>=array[low] && array[mid]>=array[high]){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return -1;
    }

    public static int circularArraySearch(int[] array, int key){
        int n = array.length;
        int low = 0, high = n-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(array[mid]==key)return mid;
            else if(array[mid]<=array[high]){
                //right part sorted
                if(array[mid]<key && key<=array[high]){
                    low = mid+1;
                }else{
                    high=mid-1;
                }
            }else{
                //left part sorted
                if(key>=array[low] && array[mid]>key){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {5,6,7,1,2,3,4};
        System.out.println(circularArraySearch(array, 6));
    }
}
