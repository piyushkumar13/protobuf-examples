/*
 *  Copyright (c) 2022 GoTo
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO GOTO
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.protobuf.example5;


import com.google.protobuf.ListValue;
import com.google.protobuf.Value;
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
public class Example5Test {

    public static void main(String[] args) throws Exception {


        List<String> allSubjects = new ArrayList<>();
        allSubjects.add("Data Structures and Algorithms");
        allSubjects.add("Computer Architecture");
        allSubjects.add("DBMS");


        List<String> coreSubjects = List.of("Data Structures and Algorithms", "DBMS");
        List<String> hardwareSubjects = List.of("Computer Architecture");

        /* Approach 1 */

        System.out.println(":::::::::::::: Approach 1 started :::::::::::::::");
        ListOfSubject coreSubjectsList = ListOfSubject.newBuilder().addAllSubjects(coreSubjects).build();
        ListOfSubject hardwareSubjectsList = ListOfSubject.newBuilder().addAllSubjects(hardwareSubjects).build();

        Map<String, ListOfSubject> subjectsByCategory = new HashMap<>();
        subjectsByCategory.put("CORE", coreSubjectsList);
        subjectsByCategory.put("HARDWARE", hardwareSubjectsList);


        Student1 student1 = Student1.newBuilder()
                .setId(1)
                .setName("Piyush Kumar")
                .setAddress("Bengaluru")
                .setCollege("DCE")
                .addAllSubjects(allSubjects)
                .putAllSubjectsByCategory(subjectsByCategory)
                .build();

        System.out.println("The object is ::: \n" + student1);


        System.out.println("::::: Serializing object started :::: ");
        try (FileOutputStream outputStream = new FileOutputStream("example5file-approach-1");) {

            student1.writeTo(outputStream);

        }
        System.out.println("::::: Serializing object done :::: ");


        System.out.println("::::: Deserializing object started using parseFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example5file-approach-1")) {

            Student1 studentObj = Student1.parseFrom(inputStream);

            System.out.println("Object is  ::: \n" + studentObj);
        }
        System.out.println("::::: Deserializing object done using parseFrom method :::: ");


        System.out.println("::::: Deserializing object started using mergeFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example5file-approach-1")) {


            Student1.Builder studentBuilder = Student1.newBuilder();
            Student1.Builder studentBuilderObj = studentBuilder.mergeFrom(inputStream);

            System.out.println("Object is  ::: \n" + studentBuilderObj.build());
        }
        System.out.println("::::: Deserializing object done using mergeFrom method :::: ");

        System.out.println(":::::::::::::: Approach 1 ended :::::::::::::::");



        /* Approach 2 */

        System.out.println(":::::::::::::: Approach 2 started :::::::::::::::");

        List<MapFieldEntry> entryList = List.of(MapFieldEntry.newBuilder().setKey("CORE").addAllSubjects(coreSubjects).build(),
                MapFieldEntry.newBuilder().setKey("HARDWARE").addAllSubjects(hardwareSubjects).build()
        );

        Student2 student2 = Student2.newBuilder()
                .setId(1)
                .setName("Piyush Kumar")
                .setAddress("Bengaluru")
                .setCollege("DCE")
                .addAllSubjects(allSubjects)
                .addAllSubjectsByCategory(entryList)
                .build();

        System.out.println("The object is ::: \n" + student2);


        System.out.println("::::: Serializing object started :::: ");
        try (FileOutputStream outputStream = new FileOutputStream("example5file-approach-2");) {

            student2.writeTo(outputStream);

        }
        System.out.println("::::: Serializing object done :::: ");


        System.out.println("::::: Deserializing object started using parseFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example5file-approach-2")) {

            Student2 studentObj = student2.parseFrom(inputStream);

            System.out.println("Object is  ::: \n" + studentObj);
        }
        System.out.println("::::: Deserializing object done using parseFrom method :::: ");


        System.out.println("::::: Deserializing object started using mergeFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example5file-approach-2")) {


            Student2.Builder studentBuilder = Student2.newBuilder();
            Student2.Builder studentBuilderObj = studentBuilder.mergeFrom(inputStream);

            System.out.println("Object is  ::: \n" + studentBuilderObj.build());
        }
        System.out.println("::::: Deserializing object done using mergeFrom method :::: ");

        System.out.println(":::::::::::::: Approach 2 ended :::::::::::::::");



        /* Approach 3 */

        System.out.println(":::::::::::::: Approach 3 started :::::::::::::::");

        ListValue coreSubjectsValueList = ListValue.newBuilder()
                .addValues(Value.newBuilder().setStringValue("Data Structures and Algorithms").build())
                .addValues(Value.newBuilder().setStringValue("DBMS").build())
                .build();

        ListValue hardcoreSubjectsValueList = ListValue.newBuilder()
                .addValues(Value.newBuilder().setStringValue("DBMS").build())
                .build();

        Map<String, ListValue> subjectsByCategoryMap = new HashMap<>();
        subjectsByCategoryMap.put("CORE", coreSubjectsValueList);
        subjectsByCategoryMap.put("HARDCORE", hardcoreSubjectsValueList);

        Student3 student3 = Student3.newBuilder()
                .setId(1)
                .setName("Piyush Kumar")
                .setAddress("Bengaluru")
                .setCollege("DCE")
                .addAllSubjects(allSubjects)
                .putAllSubjectsByCategory(subjectsByCategoryMap)
                .build();

        System.out.println("The object is ::: \n" + student3);


        System.out.println("::::: Serializing object started :::: ");
        try (FileOutputStream outputStream = new FileOutputStream("example5file-approach-3");) {

            student3.writeTo(outputStream);

        }
        System.out.println("::::: Serializing object done :::: ");


        System.out.println("::::: Deserializing object started using parseFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example5file-approach-3")) {

            Student3 studentObj = Student3.parseFrom(inputStream);

            System.out.println("Object is  ::: \n" + studentObj);
        }
        System.out.println("::::: Deserializing object done using parseFrom method :::: ");


        System.out.println("::::: Deserializing object started using mergeFrom method :::: ");
        try (FileInputStream inputStream = new FileInputStream("example5file-approach-3")) {


            Student3.Builder studentBuilder = Student3.newBuilder();
            Student3.Builder studentBuilderObj = studentBuilder.mergeFrom(inputStream);

            System.out.println("Object is  ::: \n" + studentBuilderObj.build());
        }
        System.out.println("::::: Deserializing object done using mergeFrom method :::: ");

        System.out.println(":::::::::::::: Approach 3 ended :::::::::::::::");

    }
}
