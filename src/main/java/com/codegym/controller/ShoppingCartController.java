package com.codegym.controller;

import com.codegym.model.Items;
import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {
    @Autowired
    private ProductService productService;
    @GetMapping("/ordernow/{id}")
    public ModelAndView order(@PathVariable long id, HttpSession session){
        if (session.getAttribute("cart") == null) {
            List<Items> cart = new ArrayList<Items>();
            Product product =  productService.findById(id);
            cart.add(new Items(product, 1));
            session.setAttribute("cart", cart);
        } else {
            List<Items> cart = (List<Items>) session.getAttribute("cart");

            // using method isExisting here
            int index = isExisting(id, session);
            if (index == -1)
                cart.add(new Items(productService.findById(id), 1));
            else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }

            session.setAttribute("cart", cart);

        }
        ModelAndView modelAndView = new ModelAndView("/shoppingCart/cart");
        return modelAndView;
    }

    private int isExisting(long id, HttpSession session) {
        List<Items> cart = (List<Items>) session.getAttribute("cart");

        for (int i = 0; i < cart.size(); i++)

            if (cart.get(i).getProduct().getId() == id)
                return i;

        return -1;
    }

    @GetMapping("/ordernow")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/shoppingCart/cart");
        List<Items> cart = (List<Items>) session.getAttribute("cart");
        session.setAttribute("cart", cart);
        return modelAndView;
    }
}
