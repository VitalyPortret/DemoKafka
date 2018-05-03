package com.example.demo;

import com.example.demo.consumer.Receiver;
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
    @Autowired
    private Receiver receiver;

    @RequestMapping({ "/{message}" })
    public void index(@PathVariable("message") String message) {
        String data = "Spring Kafka Producer and Consumer Example";
        sender.send(message);
    }

    @RequestMapping({ "/get" })
    public String all(){
        String s = receiver.listen("sd");
        return s;
    }
}
