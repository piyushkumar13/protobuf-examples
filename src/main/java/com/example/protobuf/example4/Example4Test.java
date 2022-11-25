/*
 *  Copyright (c) 2022 GoTo
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO GOTO
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.protobuf.example4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Piyush Kumar.
 * @since 23/11/22.
 */
public class Example4Test {

    public static void main(String[] args) throws Exception {

        List<String> subjects = new ArrayList<>();
        subjects.add("Data Structures and Algorithms");
        subjects.add("Computer Architecture");
        subjects.add("DBMS");

        Map<String, Integer> marksBySubject = new HashMap<>();
        marksBySubject.put("Data Structures and Algorithms", 90);
        marksBySubject.put("Computer Architecture", 85);
        marksBySubject.put("DBMS", 95);

        Student student = Student.newBuilder()
                .setId(1)
                .setName("Piyush Kumar")
                .setAddress("Bengaluru")
                .setCollege("DCE")
                .addAllSubjects(subjects)
                .putAllMarksBySubjectMap(marksBySubject)
                .build();

        System.out.println("The object is ::: \n" + student);


        System.out.println("::::: Serializing object started :::: ");
        try (FileOutputStream outputStream = new FileOutputStream("example4file");) {

            student.writeTo(outputStream);

        }
        System.out.println("::::: Serializing object done :::: ");


        System.out.println("::::: Deserializing object started using parseFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example4file")) {

            Student studentObj = Student.parseFrom(inputStream);

            System.out.println("Object is  ::: \n" + studentObj);
        }
        System.out.println("::::: Deserializing object done using parseFrom method :::: ");


        System.out.println("::::: Deserializing object started using mergeFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example4file")) {


            Student.Builder studentBuilder = Student.newBuilder();
            Student.Builder studentBuilderObj = studentBuilder.mergeFrom(inputStream);

            System.out.println("Object is  ::: \n" + studentBuilderObj.build());
        }
        System.out.println("::::: Deserializing object done using mergeFrom method :::: ");
    }

}
