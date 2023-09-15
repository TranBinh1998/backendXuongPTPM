package com.poly.test.testptpm.service.impl;

import com.poly.test.testptpm.dao.StatusReposirory;
import com.poly.test.testptpm.enties.Status;
import com.poly.test.testptpm.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {


    StatusReposirory statusReposirory;

    public StatusServiceImpl(StatusReposirory statusReposirory) {
        this.statusReposirory = statusReposirory;
    }

    @Override
    public List<Status> getstatusList() {
        return statusReposirory.findAll();
    }

    @Override
    public Status getStatusById(Long id) {
        Optional<Status> status = statusReposirory.findById(id);
        if (status.isPresent()) {
            return status.get();
        }else {
            return null;
        }

    }
}
