package luuthuyvi.demo.controller;

import jakarta.validation.Valid;
import luuthuyvi.demo.entity.Book;
import luuthuyvi.demo.services.BookService;
import luuthuyvi.demo.services.CategoryService;
import luuthuyvi.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> book    = bookService.getAllBooks();
        model.addAttribute("books",book);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public  String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/book";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book editBook = bookService.getBookById(id);
        if (editBook != null) {
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook( @ModelAttribute("book") Book updatedBook) {
        bookService.updateBook(updatedBook);
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public  String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/book";
    }
}