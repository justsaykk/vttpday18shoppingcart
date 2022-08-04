package rediscart.shoppingcart.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rediscart.shoppingcart.repository.CartRepo;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    public List<String> makeList(String s) {
        String[] items = s.split(",");
        List<String> contents = new LinkedList<>();
        for (String i : items) {
            contents.add(i);
        }
        return contents;
    }

    public String makeString(List<String> list) {
        return String.join(",", list);
    }

    public Boolean cartCheck(String name) {
        return cartRepo.check(name);
    }

    public void addItem(String name, String item) {
        cartRepo.addItem(name, item);
    }

    public List<String> getCart(String name) {
        return cartRepo.getCart(name);
    }
}
