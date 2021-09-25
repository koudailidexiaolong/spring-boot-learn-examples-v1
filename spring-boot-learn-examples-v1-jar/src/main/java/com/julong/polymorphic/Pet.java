package com.julong.polymorphic;

/**
 * 宠物类
 * @author julong
 * @date 2020年3月12日 下午9:59:32
 * @desc 
 */
public abstract class Pet {

	protected String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void eat(){
		System.out.println("吃东西");
	}

	public Pet(String name) {
		super();
		this.name = name;
	}

	public Pet() {
		super();
	}
	
	
	
}
