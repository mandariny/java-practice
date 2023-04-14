package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
Vector가 thread safe하게 동작되는지 확인
 */
public class Main {

    public static class Person{
        String name;
        int age;

        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }
    }
    public static void main(String[] args) throws InterruptedException{
        List<Person> arrayList = new ArrayList<>();
        List<Person> vectorList = new Vector<>();

        Thread arrayThreadA = new Thread(){
            @Override
            public void run(){
                for(int i=1; i<=1000; i++){
                    arrayList.add(new Person("person"+i, i));
                }
            }
        };

        Thread arrayThreadB = new Thread(){
            @Override
            public void run(){
                for(int i=1; i<=1000; i++){
                    arrayList.add(new Person("person"+i, i));
                }
            }
        };

        Thread vectorThreadA = new Thread(){
            @Override
            public void run(){
                for(int i=1; i<=1000; i++){
                    vectorList.add(new Person("person"+i, i));
                }
            }
        };


        Thread vectorThreadB = new Thread(){
            @Override
            public void run(){
                for(int i=1; i<=1000; i++){
                    vectorList.add(new Person("person"+i, i));
                }
            }
        };


        arrayThreadA.start();
        arrayThreadB.start();
        vectorThreadA.start();
        vectorThreadB.start();

        arrayThreadA.join();
        arrayThreadB.join();
        vectorThreadA.join();
        vectorThreadB.join();

        System.out.println("ArrayList : " + arrayList.size());
        System.out.println("Vector : " + vectorList.size());
    }
    
}