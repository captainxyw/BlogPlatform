package com.itshidu.web.dao;

import com.itshidu.web.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupDao extends JpaRepository<Forum,Long> {

    List<Forum> findByStatus(boolean status);
	
}
