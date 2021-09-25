package com.julong.polymorphic;

import java.util.Vector;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pet pet = new Dog("小猫", "波斯猫");
		pet.eat();
		
		if(pet instanceof Cat){
			Cat cat = new Cat();
			cat.eat();
		}else if(pet instanceof Dog){
			Dog dog = new Dog();
			dog.eat();
		}
		
		
		Vector<String> vector = new Vector<String>();
		vector.add("111");
		vector.add("222");
		vector.add("3333");
		vector.add("4444");
		vector.add("5555");
		vector.add("6666");
		
		for (int i = 0; i < vector.size(); i++) {
			System.out.println(vector.get(i));
		}
		
	}

}
