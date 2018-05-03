package com.example.demo;

import com.example.demo.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/send")
public class ModerController {

    @Autowired
    private Sender sender;

    @RequestMapping({ "/{message}/{age}" })
    public void index(@PathVariable("message") String message,
                      @PathVariable("age") int age) {
        Foo foo = new Foo("Spring Kafka", message, age);
        sender.send(foo);
    }
}
