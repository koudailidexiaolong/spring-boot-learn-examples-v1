package com.julong.polymorphic;

/**
 * 宠物猫
 * @author julong
 * @date 2020年3月12日 下午10:00:54
 * @desc 
 */
public class Cat extends Pet{

	private String strain;//品种
	
	public String getStrain() {
		return strain;
	}

	public void setStrain(String strain) {
		this.strain = strain;
	}


	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("猫要吃猫粮");
	}

	public Cat() {
		super();
	}

	public Cat(String name, String strain) {
		super(name);
		this.strain = strain;
	}


	
}
