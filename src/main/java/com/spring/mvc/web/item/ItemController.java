package com.spring.mvc.web.item;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ItemController {

    @GetMapping("/item")
    public String itemList() {
        return "item";
    }

}
