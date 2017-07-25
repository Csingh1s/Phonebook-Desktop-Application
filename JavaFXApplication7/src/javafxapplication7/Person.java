/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

/**
 *
 * @author chand
 */
public class Person {
   private String  firstname;
   private int Id;
	private String  lastname;
	private String phonenumber;
	private String city;
	private String postcode;

	public Person(int id ,String firstname, String lastname, String phonenumber, String city, String postcode) {
		this.firstname=  firstname;
		this.lastname= lastname;
		this.phonenumber= phonenumber;
		this.city= city;
                this.Id=id;
		this.postcode= postcode;
		 
		
	}

    Person() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
        public void setId(int Id) {
		this.Id = Id;
	}
       public int getId() {
		return Id;
	}
}
