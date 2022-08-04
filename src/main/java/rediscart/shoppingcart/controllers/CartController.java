package rediscart.shoppingcart.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rediscart.shoppingcart.services.CartService;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private CartService cartSvc;

    @PostMapping
    public String redirectToCart(@RequestBody MultiValueMap<String, String> form, Model model) {
        // Null Check
        String user = form.getFirst("user");
        if (isNull(user)) {
            user = "annonymous";
        }
        model.addAttribute("user", user);
        return "cart";
    }

    @PostMapping(path = "/addItem")
    public String addItems(
            @RequestBody MultiValueMap<String, String> form, Model model) {
        String user = form.getFirst("user");
        String cart = form.getFirst("contents");
        String item = form.getFirst("item");
        String newCart = "";

        if (!isNull(cart)) {
            List<String> list = new LinkedList<>();
            list = cartSvc.makeList(cart);
            list.add(item);
            newCart = cartSvc.makeString(list);
        } else {
            newCart = item;
        }

        // Populate the user via hidden form fields
        model.addAttribute("cart", newCart);
        model.addAttribute("user", user);
        return "cart";
    }

    private Boolean isNull(String s) {
        return ((null == s) || (s.trim().length() <= 0));
    }
}
