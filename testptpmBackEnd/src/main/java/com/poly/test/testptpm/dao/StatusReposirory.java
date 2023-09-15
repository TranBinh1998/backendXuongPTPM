package com.poly.test.testptpm.dao;

import com.poly.test.testptpm.enties.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusReposirory extends JpaRepository<Status, Long> {
}
