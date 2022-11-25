/*
 *  Copyright (c) 2022 GoTo
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO GOTO
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.protobuf.example3;

import static com.example.protobuf.example3.EmpType.FULL_TIME;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Piyush Kumar.
 * @since 23/11/22.
 */
public class Example3Test {

    public static void main(String[] args) throws Exception {

        Address address = Address.newBuilder()
                .setCity("Bengaluru")
                .setStreet("SomethingStreet")
                .setArea("SomethingArea")
                .setState("Karnataka")
                .setCountry("India")
                .setPin(110063)
                .build();

        Employee employee = Employee.newBuilder()
                .setId(1)
                .setName("Piyush")
                .setAddress(address)
                .setEmail("piyush@jedix.com")
                .setDept("Engineering")
                .setEmpType(FULL_TIME)
                .build();

        System.out.println("The object is ::: \n" + employee);


        System.out.println("::::: Serializing object started :::: ");
        try (FileOutputStream outputStream = new FileOutputStream("example3file");) {

            employee.writeTo(outputStream);

        }
        System.out.println("::::: Serializing object done :::: ");


        System.out.println("::::: Deserializing object started using parseFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example3file")) {

            Employee employeeObj = Employee.parseFrom(inputStream);

            System.out.println("Object is  ::: \n" + employeeObj);
            System.out.println("EmpType ::: " + employeeObj.getEmpType()); // explicitly printing enum here since toString was not printing enums in the employee object
        }
        System.out.println("::::: Deserializing object done using parseFrom method :::: ");


        System.out.println("::::: Deserializing object started using mergeFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example3file")) {


            Employee.Builder employeeBuilder = Employee.newBuilder();
            Employee.Builder empBuilderObj = employeeBuilder.mergeFrom(inputStream);
            Employee empObj = empBuilderObj.build();

            System.out.println("Object is  ::: \n" + empObj);
            System.out.println("EmpType ::: " + empObj.getEmpType()); // explicitly printing enum here since toString was not printing enums in the employee object

        }
        System.out.println("::::: Deserializing object done using mergeFrom method :::: ");
    }

}
