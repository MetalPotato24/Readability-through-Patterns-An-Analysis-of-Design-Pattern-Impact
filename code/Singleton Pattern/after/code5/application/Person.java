package application;

public class Person {

	private String firstName;
	private String lastName;
	private int age;
	private int ID;
	private static int IDCount = 0; 
	
	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.IDCount++;
		this.ID = this.IDCount;
	}
	
	public String toString() {
		return (this.ID + " " + this.firstName + " " + this.lastName + " " + this.age);
	}

	// getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
}
