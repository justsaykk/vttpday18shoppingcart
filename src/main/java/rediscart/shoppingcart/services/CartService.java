package rediscart.shoppingcart.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CartService {

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
}
