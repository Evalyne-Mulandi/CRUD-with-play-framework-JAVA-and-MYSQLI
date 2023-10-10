package controllers;

import play.mvc.*;
public class AboutController extends Controller{

    public Result index() {
        return ok(views.html.about.render());
    }
}
