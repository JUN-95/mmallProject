package com.jun.test;

public class Test {
    public static void main(String[] args) {
        float f  = (float) 1;
        System.out.println(f);
/*        int i = 0;
        boolean res = (i++ == 0);
        System.out.println(res);*/

/*        for (int i = 0; i < 5; i++) {
            boolean res = (i++ == 0);   // 在对一次i 进行值更改时， 上次i++就会生效
            System.out.println("i=" + i + "==0" + " ---> " + res);    // true
            System.out.println("i===0" + " ---> " + res);
            System.out.println("i=" + i + "==0" + " ---> " + res);   // true
            System.out.println(i);

            System.out.println(i++);
            boolean res1 = (i++ == 1);   // 在对一次i 进行值更改时， 上次i++就会生效
            System.out.println("i=" + i + "==1" + " ---> " + res1);  // true
            System.out.println(i);
            System.out.println("------------");
        }*/
/*

        int a = 1;
        int b = 2;
        int c = 3;

        int res = (a == 4) ? b++ : c++;
        System.out.println(a + "-->" + b + "-->" + c);
        c++;
        System.out.println(c++);
        System.out.println(c++);
        System.out.println(c++);
        System.out.println(c);
*/

    }
/*        try {
            Test.test();
        } catch (Exception e) {
            System.out.println("test exception");
        }
        try {
            Test.test2();
        } catch (Exception e) {
            System.out.println("test2 exception");
        }
    }

    public static boolean test() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            throw new RuntimeException("exception..");
        }
        System.out.println("----");
        return true;
    }

    public static boolean test2() {
        System.out.println(1 / 0);
        throw new RuntimeException("exception2..");
    }*/


}
