package models.users;
/*
import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import models.products.*;

@Entity

@Table(name = "address")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name = "addr")

@DiscriminatorValue("b")

public class Address extends Model {
    @Id
    private String email;

    @Constraints.Required
    private String address;   

    @OneToOne(cascade=CascadeType.ALL)
    private List<User> user;
    
    public static final Finder<Long, Address> find = new Finder<>(Address.class);

    public static Address getAddressById(String id) {
        if (id == null) {
            return null;
        } else {
            return find.query().where().eq("email", id).findUnique();
        }
    }
    public Address(){

    }   
    
    public Address(String email, String address){
        this.email = email;
        this.address = address;
        
    }

    public static final List<Address> findAll() {
        return Address.find.all();
    }
    
    
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

   
    public void setAddress(String address) {
        this.address = address;
    }

}*/