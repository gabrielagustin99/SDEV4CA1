package models.products;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import models.users.*;

@Entity
public class Department extends Model {

   @Id
   private Long id;

   @Constraints.Required
   private String name;

   @ManyToMany(cascade=CascadeType.ALL)
   private List<Projects> items;

   @OneToMany(cascade=CascadeType.ALL)
   private List<User> employees;

   public  Department() {
   }
			    
   public  Department(Long id, String name, List<Projects> items, List<User> employees) {
      this.id = id;
      this.name = name;
      this.items = items;
      this.employees = employees;
   }
   public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public List<Projects> getItems() {
    return items;
}

public void setItems (List<Projects> items) {
    this.items = items;
}

public List<User> getEmployees() {
    return employees;
}
public static Finder<Long,Department> find = new Finder<Long,Department>(Department.class);

public static List<Department> findAll() {
   return Department.find.query().where().orderBy("name asc").findList();
}

public static Map<String,String> options() {
    LinkedHashMap<String,String> options = new LinkedHashMap();
 
    for (Department c: Department.findAll()) {
       options.put(c.getId().toString(), c.getName());
    }
    return options;
 }
 public static boolean inDepartment(Long Department, Long item) {
    return find.query().where().eq("items.id", item)
                       .eq("id", Department)
                       .findList().size() > 0;
}

public static boolean empDepartment(Long Department, Long employees) {
    return find.query().where().eq("employees.id", employees)
                       .eq("id", Department)
                       .findList().size() > 0;
}

}