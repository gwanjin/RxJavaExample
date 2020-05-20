package chapter2;

public class Person {
	private String name;
	private int age;
	
	public Person( String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Name : " + getName() + ", Age : " + getAge();
	}
}
