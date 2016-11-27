/**
 * 
 */
package com.gojek.entity;

/**
 * @author sundaram
 *
 */
public abstract class Vehicle {

	protected String regNo;
	protected String color;
    
    public Vehicle(String regNo, String color) {
        this.regNo = regNo;
        this.color = color;
    }

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
