package com.lib.mylib;

import java.io.File;

public class MyClass {

    public static void main(String[] args) {
        System.out.println("Hello FileName " + args.length);
        if (args.length == 0) {
            System.out.println("need path");
            return;
        }

        int i = 0;
        String prefix = args[0];
        File file = new File("");
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.isFile()){

                }
            }
        }
    }
}
