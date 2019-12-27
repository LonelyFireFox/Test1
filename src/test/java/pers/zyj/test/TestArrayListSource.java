package pers.zyj.test;


import java.util.ArrayList;

public class TestArrayListSource {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        for (Object o : list) {
            System.out.println((Integer)o);
        }
    }
}
