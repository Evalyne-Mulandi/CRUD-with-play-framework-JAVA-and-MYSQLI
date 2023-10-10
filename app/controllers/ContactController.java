package controllers;

import play.mvc.*;
public class ContactController extends Controller{

    public Result index() {
        String email = "eva.nthenya.mulandi@gmail.com";
//        System.out.println("Email Address: "+email);
        return ok(views.html.contact.render(email));
    }
}
