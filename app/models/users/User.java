package models.users;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import models.products.*;

@Entity

@Table(name = "user")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name = "type")

@DiscriminatorValue("u")

public class User extends Model {
    @Id
    private String email;

    @Constraints.Required
    private String name;   
    
    @Constraints.Required
    private String password;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private List<Department> department;

    /*@OneToOne(cascade=CascadeType.ALL)
    private List<Address> address;*/

    private String role;

    public static final Finder<Long, User> find = new Finder<>(User.class);



    public static User authenticate(String email, String password) {
        return find.query().where().eq("email", email).eq("password", password).findUnique();
    }
    public static User getUserById(String id) {
        if (id == null) {
            return null;
        } else {
            return find.query().where().eq("email", id).findUnique();
        }
    }


    public User() {

    }

    public User(String email, String name, String role, 
    String password) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.password = password;
    }
    public static final Finder<Long, User> findUser = new Finder<>(User.class);
			    
    public static final List<User> findAll() {
        return User.findUser.all();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public List<Department> getDepartment() {
        return department;
    }
    public void setDepartment(List <Department> Department) {
        this.department = department;
    }
    
    /*public List<Address> getAddress() {
        return address;
    }
    public void setAddress(List <Address> address) {
        this.address = address;
    }*/
}