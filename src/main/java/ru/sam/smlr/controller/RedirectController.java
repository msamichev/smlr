package ru.sam.smlr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sam.smlr.service.KeyMapperService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by msamichev on 30.03.2017.
 */
@Controller
@RequestMapping("/{key}")
public class RedirectController {

    private static final String HEADER_NAME = "Location";

    @Autowired
    private KeyMapperService service;

    @RequestMapping
    public void redirect(@PathVariable("key") String key, HttpServletResponse response) {
        KeyMapperService.Get res = service.getLink(key);
        if(res instanceof KeyMapperService.Get.Link){
            response.setHeader(HEADER_NAME, ((KeyMapperService.Get.Link)res).getLink());
            response.setStatus(302);

        }else if(res instanceof KeyMapperService.Get.NotFound){
            response.setStatus(404);
        }

    }

}
