/*
 *  Copyright (c) 2022 GoTo
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO GOTO
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.protobuf.example7;

import com.google.protobuf.Any;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Piyush Kumar.
 * @since 23/11/22.
 */
public class Example7Test {

    public static void main(String[] args) throws Exception {

        Employee employee = Employee.newBuilder()
                .setId(1)
                .setName("Piyush")
                .setAddress("Bangalore")
                .setEmail("piyush@jedix.com")
                .setOrgName("Goto")
                .setDepartment("Engineering")
                .addAllMetadata(List.of(Any.pack(Address.newBuilder().setState("Karnataka").build()), Any.pack(Technology.newBuilder().addAllTechs(List.of("Java","Spring")).build())))
//                .addMetadata(Any.pack(Address.newBuilder().setState("Karnataka").build())) // we can also add one by one item in metadata list
//                .addMetadata(Any.pack(Technology.newBuilder().addAllTechs(List.of("Java","Spring")).build())) // we can also add one by one item in metadata list
                .build();

        System.out.println("The object is ::: \n" + employee);


        System.out.println("::::: Serializing object started :::: ");
        try (FileOutputStream outputStream = new FileOutputStream("example7file");) {

            employee.writeTo(outputStream);

        }
        System.out.println("::::: Serializing object done :::: ");


        System.out.println("::::: Deserializing object started using parseFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example7file")) {

            Employee employeeObj = Employee.parseFrom(inputStream);

            System.out.println("Object is  ::: \n" + employeeObj);
        }
        System.out.println("::::: Deserializing object done using parseFrom method :::: ");


        System.out.println("::::: Deserializing object started using mergeFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example7file")) {


            Employee.Builder employeeBuilder = Employee.newBuilder();
            Employee.Builder empBuilderObj = employeeBuilder.mergeFrom(inputStream);

            System.out.println("Object is  ::: \n" + empBuilderObj.build());
        }
        System.out.println("::::: Deserializing object done using mergeFrom method :::: ");
    }

}
