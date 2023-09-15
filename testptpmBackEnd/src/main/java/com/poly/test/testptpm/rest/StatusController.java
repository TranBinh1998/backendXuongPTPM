package com.poly.test.testptpm.rest;

import com.poly.test.testptpm.enties.Brand;
import com.poly.test.testptpm.enties.Status;
import com.poly.test.testptpm.service.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBrands () {
        List<Status> statusList = statusService.getstatusList();
        return ResponseEntity.ok(statusList);
    }


}
