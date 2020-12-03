package com.yingbing.rabbitmq.Controller;

import com.yingbing.rabbitmq.Service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @RequestMapping("/send/{msg}")
    public void send(@PathVariable("msg") String msg){
        producerService.send(msg);
    }
}
