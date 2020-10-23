package morning;

class Person {
	String name;
	int age;
	String[] hobbies = new String[5];
	String[] pets = new String[5];

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public void addHobby(String hobby) {
		for (int i = 0; i < hobbies.length; i++) {
			if (hobbies[i] == null) {
				hobbies[i] = hobby;
				break;
			}
		}
	}

	public String getHobbies() {
		String hob = "";
		for (String hobby : hobbies) {
			if (hobby != null)
				hob += hobby + " ";
		}
		return hob;
	}

	public void addPet(String pet) {
		for (int i = 0; i < pets.length; i++) {
			if (pets[i] == null) {
				pets[i] = pet;
				break;
			}
		}
	}

	public String getPets() {
		String pet = "";
		for (String p : pets) {
			if (p != null)
				pet += p + " ";
		}
		return pet;
	}

}

public class Morning2013 {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setName("Hong");
		p1.setAge(25);
		p1.addHobby("reading");
		p1.addPet("mew");
		p1.addHobby("sleeping");
		p1.addPet("song");

		System.out.println(
				"name:" + p1.getName() + ",age:" + p1.getAge() + ",hobby:" + p1.getHobbies() + ",pet:" + p1.getPets());
	}

}
