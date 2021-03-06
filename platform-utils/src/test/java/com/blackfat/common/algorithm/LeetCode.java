package com.blackfat.common.algorithm;


import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author wangfeiyang
 * @desc
 * @create 2019/2/15-11:29
 */
public class LeetCode {

    /**
     * 169
     * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素
     *
     * @param nums [3,2,3]->3
     * @return
     */
    public static int majorityElement(int[] nums) {
        int count = 1, maj = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (maj == nums[i])
                count++;
            else {
                count--;
                if (count == 0) {
                    maj = nums[i];
                    count = 1;
                }
            }
        }
        return maj;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,1,3,3,2,2,2};
//        System.out.println(majorityElement2(nums));
        System.out.println(mySqrt(8));
        System.out.println(3+5>>1);
        System.out.println(3+(5>>1));
    }

    /**
     * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素
     *
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement2(int[] nums) {
        List<Integer> result = Lists.newArrayList();
        if(nums == null || nums.length == 0){
            return result;
        }
        int maj1 = nums[0], maj2 = nums[0];
        int count1 = 0, count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == maj1) count1++;
            else if (nums[i] == maj2) count2++;
            else if (count1 == 0) {
                count1 = 1;
                maj1 = nums[i];
            } else if (count2 == 0) {
                count2 = 1;
                maj2 = nums[i];
            } else {
                --count1;
                --count2;
            }
        }
        count1=count2=0;

        for(int i=0;i<nums.length;i++)     //重新计算个数来确保次数超过n/3
        {
            if(nums[i]==maj1)count1++;
            if(nums[i]==maj2)count2++;
        }
        if(count1> nums.length/3){
           result.add(maj1);
        }
        if(count2> nums.length/3 && maj1 != maj2){
            result.add(maj2);
        }
        return result;
    }


    /**
     * 88
     * 合并两个有序数组
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n){
        int i=m-1,j=n-1;
        int index=m+n-1;
        while(index>=0){
            //前两个判断要放在前面，防止空指针异常
            if(i<0){
                nums1[index--]=nums2[j--];
            }else if(j<0){
                nums1[index--]=nums1[i--];
            }
            else if(nums1[i]>nums2[j] && i>=0){
                nums1[index--]=nums1[i--];
            }else if(nums1[i]<=nums2[j]&& j>=0){
                nums1[index--]=nums2[j--];
            }
        }

    }


    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    /** 21
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public Node mergeTwoLists(Node l1, Node l2) {
        // 类似归并排序中的合并过程
        // 哨兵节点
        Node dummyHead = new Node(0,null);
        Node cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }

    /**46
     * 全排列
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums == null || nums.length == 0){
              return  list;
        }
        int k = 0 , j = nums.length;
        fun(nums,0,nums.length-1,list);
        return list;
    }

    private static void fun(int[] arr,int k,int j,List<List<Integer>> list){
        if(k == j){
            list.add(print(arr));
        }else{
            for(int i=k;i<=j;i++){
                  if(isSwap(arr, k , i)){
                      swap(arr,k,i);
                      fun(arr,k+1,j,list);
                      swap(arr,k,i);
                  }
            }
        }
    }

    private static  List<Integer> print(int[] arr){
        List<Integer> arrange = new ArrayList();
        for(int i:arr){
            arrange.add(i);
        }
        return arrange;
    }

    private static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }


    //能不能交换，就是说交换数字中间不能出现与后面交换的重复数字如：122那个1只能和第一个2交换却不能和第二个2交换
    private static boolean isSwap(int[] num , int i ,int j)
    {
        for(int index = i;index<j;index++)
        {
            if(num[index]==num[j])
                return false;
        }
        return true;
    }

    /**
     * 69
     * x的平方根
     * @param x
     * @return
     */
    public static int mySqrt(int x){
         if(x <= 1){
             return x;
         }
         int begin = 1; int end = x; int middle = 0;
         while(begin <= end){
             // + 优先级高于>>
             middle = begin  + ((end - begin)>>1);
             if(middle == x /middle){
                   return middle;
             }else if(middle < x/middle){
                 begin = middle +1;
             }else{
                 end = middle -1;
             }
         }
         return end;
    }


    /**
     * 125
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s){
        if(s == null || s.length() == 0){
            return true;
        }
        int left =  0;
        int right = s.length() -1;
        while(left < right){
            if(!Character.isLetterOrDigit(s.charAt(left))){
                left ++;
            }else if(!Character.isLetterOrDigit(s.charAt(right))){
                right --;
            }else{
                if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                    return false;
                left++;
                right--;
            }
        }
      return  true;
    }


    /**
     *
     * @param str
     * @return
     */
    public static int StrToInt(String str){
        if (str == null || str.length() == 0)
            return 0;
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-'))  /* 符号判定 */
                continue;
            if (c < '0' || c > '9')                /* 非法输入 */
                return 0;
            ret = ret * 10 + (c - '0');
        }
        return isNegative ? -ret : ret;
    }


    /**
     * 239
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k){
        if (nums == null || nums.length < k || k == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        //双端队列
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //在尾部添加元素，并保证左边元素都比尾部大
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            //在头部移除元素
            if (deque.getFirst() == i - k) {
                deque.removeFirst();
            }
            //输出结果
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.getFirst()];
            }
        }
        return res;
    }

    /**
     * 3
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        int end=0,start=0;
        Set<Character> set=new HashSet<>();
        while(start<n && end<n){
            if(set.contains(s.charAt(end))){
                set.remove(s.charAt(start++));
            }else{
                set.add(s.charAt(end++));
                res=Math.max(res,end-start);
            }

        }
        return res;
    }


    /*
    * 219
    * */
    public static  boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int i = 0;
        while (i < k && i < nums.length) {//这个循环先往set里装k个元素
            if (!set.add(nums[i])) {//如果装的时候就重复了，说明前k个元素就有重复的，直接返回true
                return true;
            }
            i++;
        }
        while (i < nums.length) {//这个循环，对k~（nums.length-1）的元素逐个检查：是否和它前面的k个元素出现重复
            if (!set.add(nums[i])) {//如果添加的时候出现重复，直接返回true
                return true;
            }
            set.remove(nums[i - k]);//然后再移除一个索引为（i-k）的元素。
            i++;
        }
        return false;
    }

    /**
     * 209
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int l= 0,r = -1;    // nums[l...r]为我们的滑动窗口
        int sum = 0;
        int result = nums.length + 1;
        while (l < nums.length){ // 窗口的左边界在数组范围内,则循环继续

            if( r+1 <nums.length && sum < s){
                r++;
                sum += nums[r];
            }else { // r已经到头 或者 sum >= s
                sum -= nums[l];
                l++;
            }

            if(sum >= s){
                result = (r-l+1) < result ? (r-l+1) : result ;
            }
        }
        if(result==nums.length+1){
            return 0;
        }
        return result;
    }


    /**
     * 347
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
        {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Freq> queue = new PriorityQueue<>();
        for (Integer key:map.keySet()){
            if (queue.size() < k){
                queue.add(new Freq(key,map.get(key)));
            }else if (map.get(key).compareTo(queue.peek().freq) > 0){
                queue.poll();
                queue.add(new Freq(key,map.get(key)));
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()){
            list.add(queue.poll().e);
        }

        return list;
    }

    private class Freq implements Comparable<Freq> {
        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        // 升序
        public int compareTo(Freq o) {
            if (this.freq < o.freq) {
                return -1;
            } else if (this.freq > o.freq) {
                return 1;
            } else
                return 0;
        }
    }


    /**
     * 75
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int p1 = -1, p2 = nums.length;
        for (int i = 0; i < p2 ; i++) {
            if(nums[i] == 0){
                swap(nums, i, ++p1);
            }else if(nums[i] == 2){
                swap(nums, i--, --p2);
            }
        }
    }










}
