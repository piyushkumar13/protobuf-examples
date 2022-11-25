/*
 *  Copyright (c) 2022 GoTo
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO GOTO
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.protobuf.example1;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Piyush Kumar.
 * @since 23/11/22.
 */
public class Example1Test {

    public static void main(String[] args) throws Exception {
        Employee employee = Employee.newBuilder()
                .setId(1)
                .setName("Piyush")
                .setAddress("Bangalore")
                .setEmail("piyush@jedix.com")
                .setOrgName("Goto")
                .setDepartment("Engineering")
                .build();

        System.out.println("The object is ::: \n" + employee);


        System.out.println("::::: Serializing object started :::: ");
        try (FileOutputStream outputStream = new FileOutputStream("example1file");) {

            employee.writeTo(outputStream);

        }
        System.out.println("::::: Serializing object done :::: ");


        System.out.println("::::: Deserializing object started using parseFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example1file")) {

            Employee employeeObj = Employee.parseFrom(inputStream);

            System.out.println("Object is  ::: \n" + employeeObj);
        }
        System.out.println("::::: Deserializing object done using parseFrom method :::: ");


        System.out.println("::::: Deserializing object started using mergeFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example1file")) {


            Employee.Builder employeeBuilder = Employee.newBuilder();
            Employee.Builder empBuilderObj = employeeBuilder.mergeFrom(inputStream);

            System.out.println("Object is  ::: \n" + empBuilderObj.build());
        }
        System.out.println("::::: Deserializing object done using mergeFrom method :::: ");
    }

}
