package morning;

enum Gender {
	MALE, FEMALE;
}

class Student {
	String name;
	int age;
	Gender gender;
	double score;

}

public class MorningCode {
	public static void main(String[] args) {
		
		int[] intAry = {2,5,3,8,4};
		

		Student s1 = new Student();
		s1.name = "Hong";
		s1.age = 20;
		s1.gender = Gender.FEMALE;
		s1.score = 56.9;

		Student s2 = new Student();
		s2.name = "Hong";
		s2.age = 20;
		s2.gender = Gender.MALE;
		s2.score = 56.9;

		Student s3 = new Student();
		s3.name = "Hong";
		s3.age = 20;
		s3.gender = Gender.FEMALE;
		s3.score = 56.9;

		Student s4 = new Student();
		s4.name = "Hong";
		s4.age = 20;
		s4.gender = Gender.MALE;
		s4.score = 56.9;

		Student[] students = { s1, s2, s3 };
		double sum = 0;
		for (Student student : students) {
			if (student.gender == Gender.FEMALE) {
				sum += student.score;
			}
		}
		System.out.println("Summary: " + sum);

	}
}
