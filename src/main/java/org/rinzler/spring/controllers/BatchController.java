package org.rinzler.spring.controllers;

import org.rinzler.spring.DAO.BatchDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/batch")
public class BatchController {

    private final BatchDAO batchDAO;

    public BatchController(BatchDAO batchDAO) {
        this.batchDAO = batchDAO;
    }

    @GetMapping()
    public String index(){
        return "/batch/index";
    }

    @GetMapping("/wbatch")
    public String batch(){
        batchDAO.batch();
        return "redirect: /people";
    }


    @GetMapping("wobatch")
    public String notBatch(){
        batchDAO.multi();
        return "redirect: /people";
    }
}
