package com.poly.test.testptpm.service;

import com.poly.test.testptpm.enties.Status;

import java.util.List;

public interface StatusService {
    List<Status> getstatusList();

    Status getStatusById(Long id);
}
