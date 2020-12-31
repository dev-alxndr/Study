package io.alxndr.jpashop.controller;

import io.alxndr.jpashop.domain.item.Book;
import io.alxndr.jpashop.domain.item.Item;
import io.alxndr.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }


    @PostMapping("/items/new")
    public String create(BookForm bookForm) {
        Book book = Book.createBook(bookForm);

        itemService.saveItem(book);

        return "redirect:/";
    }


    @GetMapping("/items/{id}/edit")
    public String updateItemForm(@PathVariable("id") Long itemId, Model model) {
        Book book = (Book) itemService.findOne(itemId);

        BookForm bookForm = new BookForm();
        bookForm.setId(book.getId());
        bookForm.setName(book.getName());
        bookForm.setPrice(book.getPrice());
        bookForm.setStockQuantity(book.getStockQuantity());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setIsbn(book.getIsbn());

        model.addAttribute("form", bookForm);

        return "items/updateItemForm";
    }

    @PostMapping("items/{id}/edit")
    public String updateItem(@ModelAttribute("form") BookForm bookForm) {
        Book book = Book.updateBook(bookForm);

        itemService.saveItem(book);

        return "redirect:/items";
    }


}
