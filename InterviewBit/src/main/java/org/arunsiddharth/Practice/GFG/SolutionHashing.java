package org.arunsiddharth.Practice.GFG;

import java.util.*;
import java.io.*;

public class SolutionHashing {
    public static int[] sortByRelativeOrder(int[] arr,int[] order){
        /*Map<Integer, Integer> hm = IntStream.range(0,order.length).boxed()
        .collect(Collectors.toMap(Function.identity(),i->order[i]));*/
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for(int i=0;i<order.length;i++){
            hm.put(order[i],i);
        }
        return Arrays.stream(arr).boxed().sorted((a,b)->{
            if(hm.containsKey(a) && hm.containsKey(b)){
                   return hm.get(a)<=hm.get(b)?-1:1;
               } else if(hm.containsKey(a) || hm.containsKey(b)) {
                   return hm.containsKey(a)?-1:1;
               } else {
                   return a.compareTo(b);
               }
        }).mapToInt(i->i).toArray();
        /*Arrays.sort(arr, new Comparator<Integer>(){
           public int compare(Integer a, Integer b){
               if(hm.containsKey(a) && hm.containsKey(b)){
                   return hm.get(a)<hm.get(b)?-1:1;
               } else if(hm.containsKey(a) || hm.containsKey(b)) {
                   return hm.containsKey(a)?-1:1;
               } else {
                   return a.compareTo(b);
               }
           } 
        });*/
    }


    public static int[] sortByFrequency(int[] arr){
        int count[] = new int[61];
        Arrays.stream(arr).forEach(x->count[x]++);
        return Arrays.stream(arr).boxed().sorted((a,b)->{
            return (count[a]==count[b])?(a<b?-1:1):(count[a]>count[b])?-1:1;
        }).mapToInt(i->i).toArray();
    }

    public static int maxLenSubArrayWithZeroSum(int arr[], int n){
        Map<Integer,Integer> hm = new HashMap<Integer, Integer>();
        int sum=0,length=0;
        hm.put(0,-1);
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            hm.putIfAbsent(sum,i);
            length = Math.max(length, i-hm.get(sum));
        }
        return length;
    }

    /**
     * Print common elements in sorted arrays
     */
    public static void printCommonElements(int[] a1, int [] a2, int[] a3){
        int i,j,k;
        i=j=k=0;
        while(i<a1.length && j<a2.length && k<a3.length){
            if(a1[i]==a2[j] && a2[j]==a3[k]){
                System.out.print(a1[i]);
                i++;
                j++;
                k++;
            } else {
                if(a1[i]<a2[j] && a1[i]<a3[k])i++;
                else if(a2[j]<a1[i] && a2[j]<a3[k])j++;
                else k++;
            }
        }
    }

    /**
     * Count distinct in wondow of size k
     */
    ArrayList<Integer> countDistinct(int A[], int n, int k){
        int count=0;
        Map<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        int i=0;
        for(i=0;i<k;i++){
            map.putIfAbsent(A[i],0);
            map.put(A[i],map.get(A[i])+1);
            if(map.get(A[i])==1)count++;
        }
        for(;i<A.length;i++){
            result.add(count);
            map.put(A[i-k],map.get(A[i-k])-1);
            if(map.get(A[i-k])==0)count--;
            map.putIfAbsent(A[i],0);
            map.put(A[i],map.get(A[i])+1);
            if(map.get(A[i])==1)count++;
        }
        result.add(count);
        return result;
    }

    public static boolean arrayPairSumDivisibility(int[] arr, int k){
        int n = arr.length;
        if(n%2==1)return false;
        int[] mod = new int[k];
        for(int i:arr){
            mod[i%k]++;
        }
        for(int i=1;i<=k/2;i++){
            if(mod[i]!=mod[k-i])return false;
        }
        if(k%2==0 &&mod[k/2]%2==1)return false;
        if(mod[0]%2==1)return false;
        return true;
    }

    public static int longestConsecutiveSubsequence(int[] arr) {
        int result=1;
        int len;
        Set<Integer> hm = new HashSet<>();
        for(int i:arr)hm.add(i);
        for(int i=0;i<arr.length;i++){
            if(!hm.contains(arr[i]-1)){
                int j=arr[i];
                len=0;
                while(hm.contains(j++))len++;
                result= Math.max(result,len);
            }
        }
        return result;
    }

    /**
     * Number of subarray += Number of subarray ending at sum x
     * Number of subarray ending at sum x ++
     * @param arr
     * @return
     */
    static int totalZeroSumSubarrays(int arr[]){
        Map<Integer,Integer> hm = new HashMap<Integer,Integer>();
        int sum=0,result=0;
        //if zero sum appears once we need to count it also
        hm.put(0,1);
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            hm.putIfAbsent(sum,0);
            result+=hm.get(sum);
            hm.put(sum,hm.get(sum)+1);
        }
        return result;
    }
	public static void main (String[] args) throws IOException{
		Reader.init();
		int t=Reader.nextInt();
		while(t--!=0){
		    int m = Reader.nextInt();
		    int n = Reader.nextInt();
		    int[] arr = new int[m];
		    int[] order = new int[n];
		    for(int i=0;i<m;i++)arr[i]=Reader.nextInt();
		    for(int i=0;i<n;i++)order[i]=Reader.nextInt();
		    int[] result = sortByRelativeOrder(arr, order);
		    for(int e:result){
		        System.out.print(e+" ");
		    }
		    System.out.println();
		}
	}
}