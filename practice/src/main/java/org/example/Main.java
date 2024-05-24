package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Employee> employeesList= new ArrayList<>();
        employeesList.add(new Employee(1,"purushotham","banglore","male","java",2023,20000.0));
        employeesList.add(new Employee(2,"dibakar","pune","male","java",2021,30000.0));
        employeesList.add(new Employee(3,"sneha","pune","male","development",2024,40000.0));
        employeesList.add(new Employee(4,"manohar","mumbai","female","development",2024,50000.0));
        employeesList.add(new Employee(5,"rashmi","mumbai","female","qe",2022,60000.0));
        employeesList.add(new Employee(6,"kalyan","banglore","male","qe",2022,70000.0));




      Long  totalcount=employeesList.stream().count();
        System.out.println(totalcount);

        List<Employee> basedOnSalaryAsc=employeesList.stream().sorted((e1,e2)->(int)(e1.getSalary()-e2.getSalary())).collect(Collectors.toList());
        System.out.println(basedOnSalaryAsc);
        List<Employee>basedOnSalaryDesc=employeesList.stream().sorted((e1,e2)->(int)(e2.getSalary()-e1.getSalary())).collect(Collectors.toList());
        System.out.println(basedOnSalaryDesc);

      employeesList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).forEach(System.out::println);
      employeesList.stream().sorted(Comparator.comparing(Employee::getSalary)).forEach(System.out::println);

      Map<String,Long>basedOnGender=employeesList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
      System.out.println(basedOnGender);
      Map<String,Long> groupByDepName=employeesList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
      System.out.println(groupByDepName);

      List<String> department=employeesList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());

      for(String dep:department){
          System.out.println("-------------");
          System.out.println(dep);
      }

      Map<String,Double> salaryBasedOnGender=employeesList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(salaryBasedOnGender);


        Map<String, Optional<Employee>> highestSalaryBasedOngender=employeesList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        Map<String,Optional<Employee>> lowestPaidEmployee=employeesList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.minBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(lowestPaidEmployee);
        System.out.println(highestSalaryBasedOngender);

        Map<String,Optional<Employee>> highestPaidEmployeeinDep=employeesList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

     for(Map.Entry<String,Optional<Employee>> emp:highestPaidEmployeeinDep.entrySet()){
         System.out.println("----------------------------------");
         System.out.println(emp.getKey()+"    :"+ emp.getValue());
     }


        Optional<Employee> highestpaidEmployee=employeesList.stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
        System.out.println("Highest paid Employee  :"+highestpaidEmployee);

        Map<String,Double> avgSalaryOfEachDep=employeesList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        for(Map.Entry<String,Double> avgsalary:avgSalaryOfEachDep.entrySet()){
            System.out.println(avgsalary.getKey()+"  ::  "+avgsalary.getValue());
        }


 Optional<Employee> youngestEmployee=employeesList.stream().filter(emp->emp.getGender()=="male"&& emp.getDepartment()=="java").min(Comparator.comparing(Employee::getSalary));
        System.out.println("Youngest employee in the company  :"+youngestEmployee);

Optional<Employee>mostExperiencedEmployee= employeesList.stream().min(Comparator.comparing(Employee::getYearOfJoining));
        System.out.println("Most Experienced employee : "+mostExperiencedEmployee);

        Optional<Employee> lessExperiencedEmployee=employeesList.stream().max(Comparator.comparing(Employee::getYearOfJoining));
        System.out.println("Less Experienced Employee"+lessExperiencedEmployee);

        Double averageSalary=employeesList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(averageSalary);
        Double totalSalary=employeesList.stream().collect(Collectors.summarizingDouble(Employee::getSalary)).getSum();
        System.out.println(totalSalary);

        Map<String,List<Employee>> employeesFromDepartments=employeesList.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        Set<Map.Entry<String,List<Employee>>> emps=employeesFromDepartments.entrySet();
        for(Map.Entry<String,List<Employee>> empswithName:emps){
            System.out.println(empswithName.getKey()+" "+empswithName.getValue());

            for(Map.Entry<String,List<Employee>> empNames:emps){
                System.out.println(empNames.getValue());

            }
        }
        Map<Boolean,List<Employee>> basedONGivenAge=employeesList.stream().collect(Collectors.partitioningBy(e->e.getYearOfJoining()>=2022));
        Set<Map.Entry<Boolean,List<Employee>>> entrySets=basedONGivenAge.entrySet();
        for(Map.Entry<Boolean,List<Employee>> empwithAge:entrySets){
    System.out.println("======================================");
    System.out.println(empwithAge.getKey());
    System.out.println("===================================");
    for(Map.Entry<Boolean,List<Employee>> actualEmp:entrySets){
        System.out.println(actualEmp.getValue());

    }


        }
        }


    }



