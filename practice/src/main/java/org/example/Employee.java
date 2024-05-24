package org.example;


import java.util.*;
import java.util.stream.Collectors;


       public class Employee {
           private int id;
           private String name;
           private String city;
           private String gender;
           private String department;
           private int yearOfJoining;

           public double getSalary() {
               return salary;
           }

           public void setSalary(double salary) {
               this.salary = salary;
           }

           private double salary;


           public Employee(int id, String name, String city, String gender, String department, int yearOfJoining,double salary) {
               this.id = id;
               this.name = name;
               this.city = city;
               this.gender = gender;
               this.department = department;
               this.yearOfJoining = yearOfJoining;
               this.salary=salary;
           }

           public int getId() {
               return id;
           }

           public void setId(int id) {
               this.id = id;
           }

           public String getName() {
               return name;
           }

           public void setName(String name) {
               this.name = name;
           }

           public String getCity() {
               return city;
           }

           public void setCity(String city) {
               this.city = city;
           }

           public String getGender() {
               return gender;
           }

           public void setGender(String gender) {
               this.gender = gender;
           }

           public String getDepartment() {
               return department;
           }

           public void setDepartment(String department) {
               this.department = department;
           }

           public int getYearOfJoining() {
               return yearOfJoining;
           }

           public void setYearOfJoining(int yearOfJoining) {
               this.yearOfJoining = yearOfJoining;
           }

           @Override
           public String toString() {
               return "Employee{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", city='" + city + '\'' +
                       ", gender='" + gender + '\'' +
                       ", department='" + department + '\'' +
                       ", yearOfJoining=" + yearOfJoining +'\''+
                       ", salary=" + salary +
                       '}';
           }
       }

