package controllers;

import play.mvc.*;

import views.html.*;

import play.api.Environment;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import models.*;
import models.users.*;
import models.products.*;


public class HomeController extends Controller {

    private FormFactory formFactory;

    @Inject
    public HomeController(FormFactory f) {
        this.formFactory = f;
}

    public Result projectList(Long cat) {
        List<Projects> itemList = null;
        List<Department> DepartmentList = Department.findAll();

        if(cat ==0){
            itemList = Projects.findAll();
        }else {
            itemList = Department.find.ref(cat).getItems();
        }
        return ok(projectList.render(itemList, DepartmentList,User.getUserById(session().get("email"))));

     }

    public Result index() {
        return ok(index.render(User.getUserById(session().get("email"))));
    }

    @Security.Authenticated(Secured.class)
    public Result addProjects() {
        Form<Projects> itemForm = formFactory.form(Projects.class);
        return ok(addProjects.render(itemForm,User.getUserById(session().get("email"))));
}
@Security.Authenticated(Secured.class)
@Transactional
public Result addProjectsSubmit() {
    Form<Projects> newItemForm = formFactory.form(Projects.class).bindFromRequest();

    if (newItemForm.hasErrors()) {
        return badRequest(addProjects.render(newItemForm,User.getUserById(session().get("email"))));
    } else {
        Projects newItem = newItemForm.get();

        List<Department> newCats = new ArrayList<Department>();
        for (Long cat : newItem.getCatSelect()) {
            newCats.add(Department.find.byId(cat));
        }
        newItem.setDepartment (newCats);
        
        if(newItem.getId()==null){
        newItem.save();
        }else{
            newItem.update();
        }
        flash("success", "Item " + newItem.getName() + " was added/updated.");
        return redirect(controllers.routes.HomeController.projectList(0));
    }
}
@Security.Authenticated(Secured.class)
@Transactional
@With(AuthAdmin.class)
public Result deleteItem(Long id) {

    Projects.find.ref(id).delete();

    flash("success", "Item has been deleted.");
    return redirect(controllers.routes.HomeController.projectList(0));
}
@Security.Authenticated(Secured.class)
public Result updateItem(Long id) {
    Projects i;
    Form<Projects> itemForm;

    try {
        i = Projects.find.byId(id);

        itemForm = formFactory.form(Projects.class).fill(i);
    } catch (Exception ex) {
        return badRequest("error");
    }

    return ok(addProjects.render(itemForm,User.getUserById(session().get("email"))));
}


}
