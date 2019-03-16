package models.users;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;



@Table(name = "user")

@DiscriminatorValue("c")

@Entity
public class Employee extends User{
    
    @Constraints.Required
    private String street1;
    @Constraints.Required
    private String street2;
    @Constraints.Required
    private String town;
    @Constraints.Required
    private String postCode;
    @Constraints.Required
    private String creditCard;
    

    public Employee(){

    }
	
    public Employee(String email, String role, String name, 
    String password, Date dateOfBirth,String street1, String street2, String town, String postCode, String creditCard)
	{
		super(email, role, name, password,dateOfBirth);
        this.street1 = street1;
        this.street2 = street2;
        this.town = town;
        this.postCode = postCode;
		this.creditCard = creditCard;
	}

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    public static final Finder<Long, Employee> find = new Finder<>(Employee.class);
			    
    public static final List<Employee> findAll() {
       return Employee.find.all();
    }
}