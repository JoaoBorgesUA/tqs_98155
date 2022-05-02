package com.example.HW1.Controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.persistence.MapKeyColumn;

import com.example.HW1.Models.CovObject;
import com.example.HW1.Service.APIService;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class View {

    private static final Logger log = LoggerFactory.getLogger(View.class);

    @Autowired
    APIService service;

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/Country")
    public String statsOfACountry(@ModelAttribute("CovObject") CovObject covObject, Model model)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        log.info("Requesting Country statistics of {}", covObject.getCountry());

        CovObject c = service.serviceGetCountry(covObject.getCountry());

        log.info("Got Country statistics successfully");

        HashMap<String, Object> m = new HashMap<>();

        m.put("Country", c.getCountry());
        m.put("New cases", c.getNew_cases());
        m.put("Date", c.getDate());

        model.addAttribute("alldata", m);
        log.info("Ready to display the page");
        return "Country";
    }

}
