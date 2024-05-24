package org.example;

import com.sun.jdi.IntegerValue;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.stream.IntStream.*;

public class jan {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 10, 20, 30, 43, 57, 60, 60, 40, 30, 23);
        List<Double> decimals = Arrays.asList(10.01, 20.02, 30.03, 43.04, 57.05);
        List<String> strings = Arrays.asList("aba", "bfb", "abc", "bfb", "rsaf", "rsrd");
/*
//Even and odd numbers
Map<Boolean, List<Integer>> evenOddNumbers=numbers.stream().distinct().collect(Collectors.partitioningBy(x->x%2==0));
System.out.println("evenNumbers"+evenOddNumbers.get(true));
System.out.println("OddNumbers"+evenOddNumbers.get(false));
//remove duplicate elements
List<Integer> removeDup=numbers.stream().distinct().collect(Collectors.toList());
System.out.println(removeDup);
//frequency of each element in an array or list
Map<String,Long>counting=strings.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
System.out.println(counting);

//print elements in revere order
numbers.stream().distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);
//print decimals in reverse order
decimals.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
//join the list of strings  prefix delimeter ands suffix
String joinedString=strings.stream().collect(Collectors.joining("::","","]"));
System.out.println(joinedString);
//print the list of numbers divisible by 5
numbers.stream().distinct().filter(x->x%5==0).forEach(System.out::println);
//find max and min from the list
Integer maxNumber=numbers.stream().max(Comparator.naturalOrder()).get();
Integer minNumber=numbers.stream().min(Comparator.naturalOrder()).get();
System.out.println("maxNumber :"+maxNumber +" minNumber :"+minNumber );
//merge two unsorted arrays into single sorted array
 int[] a={10,40,20,50};
 int[] b={70,20,1,3,40,5,90};
 int[] c=IntStream.concat(Arrays.stream(a),Arrays.stream(b)).distinct().sorted().toArray();
 System.out.println(Arrays.toString(c));
 //find three min numbers and three max numbers
        System.out.println("minimum numbers=============");
numbers.stream().distinct().sorted().limit(3).forEach(System.out::println);
        System.out.println("maximum numbers=============");
numbers.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).forEach(System.out::println);

//two strings are anagrams or not
 String str1="race";
 String str2="care";
 str1=Stream.of(str1.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
 str2=Stream.of(str2.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
  if(str1.equals(str2)){
     System.out.println("anagram");
 }else{
     System.out.println("not an anagram");
 }
//Sum of all digits in a Integer
 int num=2345;
 Integer sum=Stream.of(String.valueOf(num).split("")).collect(Collectors.summingInt(Integer::parseInt));
 System.out.println("Total sum of all digits: "+sum);
//largest second number in a list
 Integer seclargstNum=numbers.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
 System.out.println("second largest number from a list: "+seclargstNum);

// list of strings, sort them according to increasing order of their length

strings.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);

        int[] a1 = new int[] {45, 12, 56, 15, 24, 75, 31, 89};
int sum1=Arrays.stream(a1).sum();
Double avg=Arrays.stream(a1).average().getAsDouble();
System.out.println("sum of integers "+sum1+" average of integers "+avg);
//common elements between two lists
List<Integer>list1=Arrays.asList(10,20,30,50,60);
List<Integer>list2=Arrays.asList(20,30,60,1,3,4);
list1.stream().filter(list2::contains).forEach(System.out::println);

//reverse each word of a string
 //sum of 10 natural numbers
 int sum2=IntStream.range(1,11).sum();
 double average=IntStream.range(1,11).average().getAsDouble();
 System.out.println("sum :"+sum2+" average :"+average);
//Print first 10 even number
  IntStream.rangeClosed(1,10).map(i->i*2).forEach(System.out::println);

  //palindrome using java8

   //find list of strings starts with digit
  List<String> listOfStrings = Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
listOfStrings.stream().filter(str4->Character.isDigit(str4.charAt(0))).forEach(System.out::println);

//find duplicates from a list
        Set<Integer>uniqueElements=new HashSet<>();
        numbers.stream().filter(i->!uniqueElements.add(i)).forEach(System.out::println);

//find duplicate characters in a string
String inputString = "Javja Concept Of The Day";
Set<String> uniquechar=new HashSet<>();
Set<String> dupchar=Stream.of(inputString.split("")).map(String::toLowerCase).filter(ch->!uniquechar.add(ch)).collect(Collectors.toSet());
System.out.println(dupchar);

//Find first repeated character in a string?

  Set<String>firstrepeated=new HashSet<>();
  Stream.of(inputString.split("")).map(String::toLowerCase).filter(ch->!firstrepeated.add(ch)).limit(1).forEach(System.out::println);

//Fibonacci series
Stream.iterate(new int[]{0,1},fib->new int[]{fib[1],fib[0]+fib[1]}).limit(10).map(fib->fib[0]).forEach(System.out::println);

//find last element from a list
String lastelement=strings.stream().skip(strings.size()-1).findFirst().get();
 System.out.println(lastelement);
//find age if they give date of birth
        LocalDate birthday=LocalDate.of(2000,01,20);
        LocalDate today=  LocalDate.now();
System.out.println(ChronoUnit.YEARS.between(birthday,today));


       // 27.String ends with  first character
       List<String> endsWith=strings.stream().filter(i->i.endsWith(String.valueOf(i.charAt(0)))).collect(Collectors.toList());
       System.out.println(endsWith);

       // 28.Given a list of integers, filter out the even numbers and square the remaining ones.

        numbers.stream().filter(i->i%2!=0).map(i->i*i).forEach(System.out::println);

        //29.Given a list of words, group them by their lengths and count the number of words in each group.
        Map<Integer,Long> grpByLen=strings.stream().collect(Collectors.groupingBy(String::length,Collectors.counting()));
        System.out.println(grpByLen);

        //30.Given a list of lists of integers, flatten it into a single list..
        List<List<Integer>> lists=Arrays.asList(Arrays.asList(1,3,6,2,0),Arrays.asList(4,6,9,10,45,32,21));
        List<Integer> flattenList=lists.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flattenList);

        //31.Sort a list of strings in descending order of their lengths.
        List<String> desc=strings.stream().sorted((s2,s1)->Integer.compare(s1.length(),s2.length())).collect(Collectors.toList());
        System.out.println(desc);

       // 32.Calculate the average of a list of integers using Java 8 features.
        OptionalDouble average=numbers.stream().mapToDouble(Integer::doubleValue).average();
        System.out.println(average);

        // 33.Generate a list of prime numbers within a given range using Java 8 features.
       List<Integer>primeNumbers=   IntStream.rangeClosed(1,10).filter(i->i>1 && IntStream.rangeClosed(2, (int) Math.sqrt(i)).noneMatch(n->n%i==0)).boxed().collect(Collectors.toList());
       System.out.println(primeNumbers);
*/


//        1.Even and odd numbers

        Map<Boolean, List<Integer>> evenAndOdd = numbers.stream().distinct().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println(evenAndOdd.get(true));
        System.out.println(evenAndOdd.get(false));

//        2. remove duplicate elements

//        3.frequency of each element in an array or list
        Map<Integer, Long> frequencyOfElement = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Integer, Long> greaterThanTwo = frequencyOfElement.entrySet().stream().filter(entry -> entry.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//Map<Integer,Long>greaterThanTwo=frequencyOfElement.entrySet().stream().filter(entry->entry.getValue()>1).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        System.out.println(greaterThanTwo);
//        4.print elements in revere order
        String str = "Hello world";
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        List<String> reversedStr = Arrays.asList(str.split(" "));
        Collections.reverse(reversedStr);
        reversedStr.stream().collect(Collectors.joining(" "));
        System.out.println(reversedStr);
//        5. print decimals in reverse order
        numbers.stream().map(Integer::doubleValue).forEach(System.out::println);
//        6.convert or join the list of strings as a string with prefix delimeter and suffix

        String joinedString = strings.stream().collect(Collectors.joining(" ", "{", "}"));
        System.out.println(joinedString);
//        7.print the list of numbers divisible by 5


//        8.find max and min from the list
        Optional<Integer> max = numbers.stream().max(Comparator.naturalOrder());
        System.out.println(max);
        Optional<Integer> min = numbers.stream().min(Comparator.naturalOrder());
        System.out.println(min);

//        9.merge two unsorted arrays into single sorted array

        int[] arr = {10, 30, 12, 3, 23};
        int[] arr1 = {34, 32, 21, 56, 33};
        int[] arr3 = concat(Arrays.stream(arr), Arrays.stream(arr1)).distinct().sorted().toArray();
        System.out.println(Arrays.toString(arr3));
//        10.find three min numbers and three max numbers
//
//        11.two strings are anagrams or not
        String str1 = "race";
        String str2 = "cares";
        str1 = Stream.of(str1.split("")).sorted().collect(Collectors.joining());
        str2 = Stream.of(str2.split("")).sorted().collect(Collectors.joining());
        if (str1.equals(str2)) {
            System.out.println("anagrams");
        } else {
            System.out.println("not anagrams");
        }

//        12.Sum of all digits in a Integer
        int num = 1234;
        Integer sum = Arrays.stream(valueOf(num).split("")).collect(Collectors.summingInt(Integer::parseInt));
        System.out.println(sum);
//        13.largest second number in a list
//
//        14.list of strings, sort them according to increasing order of their length
        List<String> descOrder = strings.stream().sorted(Comparator.comparing((String::length)).reversed()).collect(Collectors.toList());
        System.out.println(descOrder);
//        15.common elements between two lists

//        16.reverse each word of a string
        List<StringBuilder> listOfreversed = strings.stream().map(i -> new StringBuilder(i).reverse()).collect(Collectors.toList());
        System.out.println(listOfreversed);
//        17.sum of 10 natural numbers
        int sumOfNatural = rangeClosed(1, 10).sum();
        System.out.println("sum of natural numbers");
//        18.Print first 10 even number
        IntStream.rangeClosed(1, 10).map(i -> i * 2).forEach(System.out::println);

//        19.palindrome or not


//        20.find list of strings starts with digit
    List<String> startsWithDigit=Arrays.asList("1one","2two","three","four");
    List<String> result2=  startsWithDigit.stream().filter(i->Character.isDigit(i.charAt(0))).collect(Collectors.toList());
        System.out.println(result2);
//        21.find duplicates from a list
     HashSet<Integer> dupElements= new HashSet();
    List<Integer> dupresult=numbers.stream().filter(i->!dupElements.add(i)).collect(Collectors.toList());
        System.out.println(dupresult);
//        22.find duplicate characters in a string
     HashSet dupChars= new HashSet();
     String str5="java is a java language";
     List<String> list=Arrays.asList(str5.split("[,\\s]"));
    List<String> result6= list.stream().filter(i->!dupChars.add(i)).toList();
    for(String in:result6){
        System.out.println(in);
    }

//        23.Find first repeated character in a string?
        String str7="java is java programming language";
        HashSet<String> firstRepeated= new HashSet<>();
        Stream.of(str7.split("[,\\s]")).filter(i->!firstRepeated.add(i));
        System.out.println(firstRepeated);

//          24.Fibonacci series

        int n1=0,n2=1;
        int sums=0;
        for(int i=0;i<=10;i++){
            sums=n1+n2;
           n1=n2;
           n2=sums;
            System.out.print(sums);
        }


//        25.find last element from a list

//        26.find age if they give date of birth
LocalDate today=LocalDate.now();
        LocalDate dateofBirth= LocalDate.of(2000,04,29);
        System.out.println(ChronoUnit.YEARS.between(today,dateofBirth));
//        27.String ends with same first character
strings.stream().filter(i->i.endsWith(String.valueOf(i.charAt(0)))).forEach(System.out::println);
//        28.Given a list of integers, filter out the even numbers and square the remaining ones.
numbers.stream().filter(i->i%2!=0).map(number->number*number).forEach(System.out::println);
//        29.Given a list of words, group them by their lengths and count the number of words in each group.
Map<Integer,Long> listWithLength=strings.stream().collect(Collectors.groupingBy(String::length,Collectors.counting()));
        System.out.println(listWithLength);
//        30.Given a list of lists of integers, flatten it into a single list..
List<List<Integer>>listOfLists= Arrays.asList(Arrays.asList(10,30,40,50),Arrays.asList(40,50,60,70,80));
List<Integer>flatten=listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flatten);
//        31.Sort a list of strings in descending order of their lengths.
List<String> descendingOflength=strings.stream().sorted((s1,s2)->Integer.compare(s2.length(),s1.length())).collect(Collectors.toList());
        System.out.println(descendingOflength);
//        32.Calculate the average of a list of integers using Java 8 features.
      OptionalDouble value=  numbers.stream().mapToDouble(Integer::doubleValue).average();
        System.out.println(value);

//        33.Generate a list of prime numbers within a given range using Java 8 features.
IntStream.rangeClosed(1,10).filter(i->i>1&& IntStream.rangeClosed(2,(int)Math.sqrt(i)).noneMatch(n->i%n==0)).forEach(System.out::println);
//
//        34)find the occurance of given string in a sentence or a word.
//
String[] strarr={"str","str","str","abc","abc","dcf","edf"};
HashSet<String> dupstr=new HashSet<>();
List<String> listOfdup=Arrays.asList(strarr).stream().filter(i->!dupstr.add(i)).toList();
Map<String,Long> countof=listOfdup.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(countof);
        }

    }



