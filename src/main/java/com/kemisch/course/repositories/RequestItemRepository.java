package com.kemisch.course.repositories;

import com.kemisch.course.domain.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Integer> {


}
