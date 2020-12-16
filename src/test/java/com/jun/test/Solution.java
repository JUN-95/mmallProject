package com.jun.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {


    private List<String> tempSameList = new ArrayList<String>();
    private Integer cursor;
    private Integer i = 0;

    public int lengthOfLongestSubstring(String s) {
        List<String> tempList = new ArrayList<String>();
        List<String> temp2List = new ArrayList<>();
        if (s != null) {
            List<String> charList;
            String[] charArr = s.split("");
            charList = Arrays.asList(charArr);
            if (charList.size() == 1) return 1;
            for (; this.i < charList.size(); this.i++) {
                test(charList, tempList, temp2List);
            }
        }
        return tempList.size();
    }

    public Integer test(List<String> charList, List<String> tempList, List<String> temp2List) {
        String head = charList.get(this.i);
        String next = charList.get(++this.i);
        if (!tempList.contains(next)) {
            while (!head.equals(next)) {
                if (!tempList.contains(next)) {

                    tempList.add(head);
                    ++this.i;
                    head = charList.get(this.i);
                    next = charList.get(this.i+1);
                }
            }
        } else {
            test2(charList);

            if (!temp2List.contains(next)) {
                head = charList.get(this.i);
                next = charList.get(++this.i);
                while (!head.equals(next)) {
                    if (!temp2List.contains(next)) {
                        temp2List.add(head);
                        ++this.i;
                        head = charList.get(this.i);
                        next = charList.get(this.i+1);
                    }
                }
                if (tempList.size()>temp2List.size()){
                    temp2List = new ArrayList<>();
                }else if (tempList.size()<temp2List.size()){
                    tempList = temp2List;
                    temp2List = new ArrayList<>();
                }
                test2(charList);
            }
        }
        return Math.max(tempList.size(),temp2List.size());
    }



    public void test2(List<String> charList) {
        String head = charList.get(this.i);
        String next = charList.get(this.i + 1);
        cursor = this.i;
        while (charList.get(cursor).equals(charList.get(++cursor))) {
            tempSameList.add(head);
            tempSameList.add(charList.get(cursor));
            this.i += tempSameList.size() - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    }
}
