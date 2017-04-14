package ru.sam.smlr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sam.smlr.service.KeyMapperService;

/**
 * Created by msamichev on 14.04.2017.
 */
@Controller
@RequestMapping("add")
public class AddController {

    @Autowired
    private KeyMapperService service;


    @RequestMapping(method = {RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<AddResponse> add(@RequestBody AddRequest request) {
        return ResponseEntity.ok(new AddResponse(request.getLink(), service.add(request.getLink())));
    }


    public static class AddRequest {

        private String link;

        public AddRequest() {
        }

        public AddRequest(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }
    }

    public static class AddResponse {
        private String link;
        private String key;


        public AddResponse() {
        }

        public AddResponse(String link, String key) {
            this.link = link;
            this.key = key;
        }

        public String getLink() {
            return link;
        }

        public String getKey() {
            return key;
        }
    }
}
