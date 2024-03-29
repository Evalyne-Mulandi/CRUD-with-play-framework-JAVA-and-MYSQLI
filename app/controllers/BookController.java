package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.i18n.MessagesApi;
import javax.inject.Inject;
import play.mvc.Http;
import io.ebean.Ebean;
import play.mvc.*;
import play.mvc.Result;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBody;
import play.mvc.Http.MultipartFormData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import views.html.book.*;

public class BookController extends Controller {
    private final FormFactory formFactory;
    private final MessagesApi messages;
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Inject
    public BookController(FormFactory formFactory, MessagesApi messages) {
        this.formFactory = formFactory;
        this.messages = messages;
    }


    public Result index(Http.Request request) {

        return ok( request.flash().get("success").orElse("Welcome!"));
    }


    // To create a book
    public Result create(Http.Request request) {

        Form<Book> bookForm = formFactory.form(Book.class);
        logger.info("create page");
        return ok(views.html.create.render(bookForm, request, messages.preferred(request)));
    }

    // To save a book
    public Result save(Http.Request request) {
//        Form<Book> bookForm = ;
        Form<Book> boundForm = formFactory.form(Book.class).bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("create book errors: {}", boundForm.errors());
            return badRequest(views.html.create.render(boundForm, request, messages.preferred(request)));
        }

        Book book = boundForm.get();
        try {
            logger.info("saving book");
            Ebean.save(book);
        } catch (Exception e) {
            logger.error("Saving error", e.getMessage());
        }

        // Redirect to the index page or perform other actions
        return redirect(routes.BookController.listBooks()).flashing("success", "created successfully");
    }

    // For all books
    public Result listBooks() {
//        List<Book> books = Book.find.all(); // Fetch all books from the database

        List<Book> books = Book.find.query().orderBy().desc("id").findList();

        return ok(views.html.booklist.render(books));
    }

    public Result deleteBook(Long id) {
        Book book = Ebean.find(Book.class, id);
        if (book != null) {
            book.delete();
        }
        // Redirect to the book list page or perform other actions
        return redirect(routes.BookController.listBooks());
    }



    public Result editBook(Long id, Http.Request request) {
        Book book = getBookById(id);

        if (book == null) {
            return notFound("Book not found");
        }

        Form<Book> bookForm = formFactory.form(Book.class).fill(book);

        return ok(views.html.edit.render(bookForm, request, messages.preferred(request)));
    }
    public Book getBookById(Long id) {
        return Ebean.find(Book.class)
                .where()
                .eq("id", id)
                .findOne();
    }




    public Result updatedBook1(Http.Request request) {

        Form<Book> boundForm = formFactory.form(Book.class).bindFromRequest((request));

        if (boundForm.hasErrors()) {
            return badRequest(views.html.edit.render(boundForm, request, messages.preferred(request)));
        }
        Book updatedBook = boundForm.get();
        Book  existingBook = getBookById(updatedBook.getId());

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setDescription(updatedBook.getDescription());

//        updatedBook.update(); // Update the book in the database

            Ebean.update(existingBook);

            return redirect(routes.BookController.listBooks());// Redirect to the book list page
        }
    public Result saves(Http.Request request) {
//        Form<Book> bookForm = ;
        Form<Book> boundForm = formFactory.form(Book.class).bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("create book errors: {}", boundForm.errors());
            return badRequest(views.html.create.render(boundForm, request,  messages.preferred(request)));
        }

        Book updatedBook = boundForm.get();
        try {
            logger.info("saving book");
            Book  existingBook = getBookById(updatedBook.getId());


            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setDescription(updatedBook.getDescription());

//        updatedBook.update(); // Update the book in the database

            Ebean.update(existingBook);
        } catch (Exception e) {
            logger.error("Saving error", e.getMessage());
        }

        // Redirect to the index page or perform other actions
        return redirect(routes.BookController.listBooks());
    }
    }





