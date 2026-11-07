package com.klef.soa.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.soa.exam.entity.NGOEntity;

public interface NGORepository extends JpaRepository<NGOEntity, Long>
{
    public List<NGOEntity> findByName(String name);

    public List<NGOEntity> findByType(String type);
}