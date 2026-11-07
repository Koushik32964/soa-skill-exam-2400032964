package com.klef.soa.exam.service;

import java.util.List;

import com.klef.soa.exam.entity.NGOEntity;

public interface NGOService
{
    public NGOEntity addNGO(NGOEntity ngo);

    public List<NGOEntity> displayAllNGOs();

    public NGOEntity displayNGOById(Long id);

    public NGOEntity updateNGO(NGOEntity ngo);

    public String deleteNGOById(Long id);

    public List<NGOEntity> displayNGOsByName(String name);

    public List<NGOEntity> displayNGOsByType(String type);
}