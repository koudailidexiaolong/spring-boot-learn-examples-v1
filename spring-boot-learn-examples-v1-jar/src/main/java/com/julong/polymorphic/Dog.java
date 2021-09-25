package com.julong.polymorphic;

/**
 * 宠物狗
 * @author julong
 * @date 2020年3月12日 下午10:01:35
 * @desc 
 */
public class Dog extends Pet{

	private String color;
	
	
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("狗吃狗粮");
	}


	public Dog() {
		super();
	}


	public Dog(String name, String color) {
		super(name);
		this.color = color;
	}



	
}
