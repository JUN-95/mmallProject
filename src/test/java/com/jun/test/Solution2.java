package com.jun.test;

import java.util.Arrays;

public class Solution2 {
    public static void main(String[] args) {
        System.out.println(sortString("faggdfdas"));
    }

    public static String sortString(String s) {
        char temp = 0;
        char[] charArr = s.toCharArray();
        for (int i = 1; i < charArr.length; i++) {
            for (int j = 0; j < charArr.length - i; j++) {
                if (charArr[j] > charArr[j + 1]) {
                    temp = charArr[j + 1];
                    charArr[j + 1] = charArr[j];
                    charArr[j] = temp;
                }
            }
        }

        return Arrays.toString(charArr);
    }
}
