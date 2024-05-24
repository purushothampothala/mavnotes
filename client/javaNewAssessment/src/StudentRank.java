import java.util.*;

//import java.util.*;
//    class Student {
//        private String name;
//        private Map<String, Integer> subjectMarks;
//
//        public Student(String name) {
//            this.name = name;
//            this.subjectMarks = new HashMap<>();
//        }
//
//        public void setMarks(String subject, int marks) {
//            subjectMarks.put(subject, marks);
//        }
//
//        public int getTotalMarks() {
//            int total = 0;
//            for (int marks : subjectMarks.values()) {
//                total += marks;
//            }
//            return total;
//        }
//
//        public String getName() {
//            return name;
//        }
//    }
//
//    class RankCalculator {
//        public static List<Student> calculateRank(List<Student> students) {
//            List<Student> rankedStudents = new ArrayList<>(students);
//            rankedStudents.sort(Comparator.comparingInt(Student::getTotalMarks).reversed());
//            return rankedStudents;
//        }
//    }
//
//    public class StudentRank{
//        public static void main(String[] args) {
//            Student student1 = new Student("purushotham");
//            student1.setMarks("Maths", 85);
//            student1.setMarks("Science", 90);
//            student1.setMarks("English", 80);
//
//            Student student2 = new Student("madhu");
//            student2.setMarks("Maths", 75);
//            student2.setMarks("Science", 85);
//            student2.setMarks("English", 90);
//
//            Student student3 = new Student("ganga");
//            student3.setMarks("Maths", 95);
//            student3.setMarks("Science", 80);
//            student3.setMarks("English", 85);
//
//            List<Student> students = new ArrayList<>();
//            students.add(student1);
//            students.add(student2);
//            students.add(student3);
//
//            List<Student> rankedStudents = RankCalculator.calculateRank(students);
//
//            System.out.println("Rank\tStudent\tTotal");
//            for (int i = 0; i < rankedStudents.size(); i++) {
//                Student student = rankedStudents.get(i);
//                System.out.println((i + 1) + "\t" + student.getName() + "\t\t" + student.getTotalMarks());
//            }
//        }
//    }
//
//


class StudentRank {
    public static void main(String[] args) {
Rank rank = new Rank();
        List<Student> listofStudents = new ArrayList<>();
        Student obj = new Student("purushotham");
        obj.setMarks("maths", 80);
        obj.setMarks("english", 90);
        Student obj1 = new Student("ganga");
        obj1.setMarks("maths", 30);
        obj1.setMarks("english", 10);


        listofStudents.add(obj);
        listofStudents.add(obj1);
        rank.setStudents(listofStudents);

    }

}

class Student {
    public Student() {
    }

    private String name;
    private Map<String, Integer> subjectMarks;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjectMarks(Map<String, Integer> subjectMarks) {
        this.subjectMarks = subjectMarks;
    }

    public Student(String name) {
        this.name = name;
        this.subjectMarks = subjectMarks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", subjectMarks=" + subjectMarks +
                '}';
    }

    public void setMarks(String subject, int marks) {
        subjectMarks.put(subject, marks);
    }


}

class Rank {

    List<Student> students = new ArrayList<>();

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}