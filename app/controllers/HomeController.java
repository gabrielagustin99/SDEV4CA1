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

import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;

/*
import java.io.IOException;
import java.awt.image.*;
import javax.imageio.*;
import org.imgscalr.*;
*/

public class HomeController extends Controller {

    private FormFactory formFactory;

    @Inject
    public HomeController(FormFactory f) {
        this.formFactory = f;
}

public Result viewProject(Long id){
    Projects work = null;
    work = Projects.find.byId(id);

    return ok(viewProject.render(work,User.getUserById(session().get("username"))));
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
     /*
        MultipartFormData<File> data = request().body().asMultipartFormData();
       
        FilePart<File> image = data.getFile("upload");
        
        String saveImageMessage = saveFile(newItem.getId(), image);

*/

        flash("success", "Item " + newItem.getName() + " was added/updated.");
        return redirect(controllers.routes.HomeController.projectList(0));
    }
}

/*
public String saveFile(Long id, FilePart<File> uploaded) {
    // Make sure that the file exists.
    if (uploaded != null) {
        // Make sure that the content is actually an image.
        String mimeType = uploaded.getContentType();
        if (mimeType.startsWith("image/")) {
            // Get the file name.
            String fileName = uploaded.getFilename();
            // Extract the extension from the file name.
            String extension = "";
            int i = fileName.lastIndexOf('.');
            if (i >= 0) {
                extension = fileName.substring(i + 1);
            }
            // Now we save the file (not that if the file is saved without
            // a path specified, it is saved to a default location,
            // usually the temp or tmp directory).
            // 1) Create a file object from the uploaded file part.
            File file = uploaded.getFile();
            // 2) Make sure that our destination directory exists and if 
            //    not create it.
            File dir = new File("public/images/productImages");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 3) Actually save the file.
            File newFile = new File("public/images/productImages/", id + "." + extension);
            if (file.renameTo(newFile)) {
                try {
                    BufferedImage img = ImageIO.read(newFile); 
                    BufferedImage scaledImg = Scalr.resize(img, 90);
                    
                    if (ImageIO.write(scaledImg, extension, new File("public/images/productImages/", id + "thumb.jpg"))) {
                        return "/ file uploaded and thumbnail created.";
                    } else {
                        return "/ file uploaded but thumbnail creation failed.";
                    }
                } catch (IOException e) {
                    return "/ file uploaded but thumbnail creation failed.";
                }
            } else {
                return "/ file upload failed.";
            }

        }
    }
    return "/ no image file.";
}
*/

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
