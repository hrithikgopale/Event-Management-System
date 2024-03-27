package com.example.Event.Management.System.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Event.Management.System.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>{
	Page<Event> findByDateBetweenOrderByDateAsc(Date startDate, Date endDate, Pageable pageable);
}
