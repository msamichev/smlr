package ru.sam.smlr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by msamichev on 30.03.2017.
 */
@Controller
@RequestMapping("/{key}")
public class RedirectController {

    private static final String HEADER_NAME = "Location";
    private static final String HEADER_VALUE = "http://www.google.com";

    @RequestMapping
    public void redirect(@PathVariable("key") String key, HttpServletResponse response) {

        if ("aAbBcCdD".equals(key)) {
            response.setHeader(HEADER_NAME, HEADER_VALUE);
            response.setStatus(302);
        } else {
            response.setStatus(404);
        }

    }

}
