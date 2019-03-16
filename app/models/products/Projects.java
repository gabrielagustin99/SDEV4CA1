package models.products;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Projects extends Model {

    // Properties
    @Id
    private Long id;
    @Constraints.Required
    private String name;
    @Constraints.Required
    private String description;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy = "items")
    private List<Department> Department;

    private List<Long> catSelect = new ArrayList<Long>();

    // Default Constructor
    public Projects() {
    }

    public Projects(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static final Finder<Long, Projects> find = new Finder<>(Projects.class);
			    
    public static final List<Projects> findAll() {
        return Projects.find.all();
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


public List<Department> getDepartment() {
    return Department;
}
public void setDepartment(List <Department> Department) {
    this.Department = Department;
}
public List<Long> getCatSelect() {
    return catSelect;
}
public void setCatSelect(List<Long> catSelect) {
    this.catSelect = catSelect;
}
}