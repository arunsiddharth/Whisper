package org.arunsiddharth.Practice.Codejam;

import java.util.*;

public class Reversort {
    public static void reverse(int[] arr, int i, int j){
        while(i<j){
            arr[i]+=arr[j]-(arr[j]=arr[i]);
            i++;
            j--;
        }
    }
    public static int findMinIndex(int[] arr, int idx){
        int n = arr.length;
        int result = idx;
        for(int i=idx;i<n;i++){
            if(arr[i]<arr[result]){
                result = i;
            }
        }
        return result;
    }
    public static int reversort(int[] arr){
        int result = 0;
        int n = arr.length;
        for(int i=0;i<n-1;i++){
            int index = findMinIndex(arr, i);
            result+=index-i+1;
            reverse(arr, i, index);
        }
        return result;
    }
    List<String> ipAddresses;

    boolean isOctetValid(String octet){
        if(octet.isEmpty())return false;
        Integer o = Integer.parseInt(octet);
        return o>=0 && o<=255;
    }
    
    String transformToIP(List<String> stb){
        String result = "";
        for(String token:stb){
            result+=token+".";
        }
        return result.substring(0, result.length()-2);
    }
    
    void solve(int currentOctet, int index, int[] nums, String co, List<String> stb){
        if((currentOctet==3 && index>nums.length) || currentOctet==4){
            stb.add(co);
            ipAddresses.add(transformToIP(stb));
            stb.remove(stb.size()-1);
            return;
        }
        if(index>=nums.length){
            return;
        }
        String newOctet = co+String.valueOf(nums[index]);
        if(isOctetValid(newOctet)){
            //append
            solve(currentOctet, index+1, nums, newOctet, stb);
            //new octet
            stb.add(newOctet);
            solve(currentOctet+1, index+1, nums, "", stb);
            stb.remove(stb.size()-1);
        }
        if(isOctetValid(co)){
            //new octet
            stb.add(co);
            solve(currentOctet+1, index, nums, "", stb);
            stb.remove(stb.size()-1);
        }
    }
    
    List<String> giveAllIPAddress(int[] nums){
        List<String> stb = new ArrayList<String>();
        ipAddresses = new ArrayList<>();
        if(nums.length<4)return ipAddresses;
        int n = nums.length;
        //[1,2,3,0]
        for(int i=0;i<=n-4;i++){
            String currentOctet = "";
            solve(0,i, nums, currentOctet, stb);
        }
        return ipAddresses;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int ct=1;ct<=t;ct++){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++)arr[i] = sc.nextInt();
            System.out.println("Case #"+ct+": "+reversort(arr));
        }
    }
}
