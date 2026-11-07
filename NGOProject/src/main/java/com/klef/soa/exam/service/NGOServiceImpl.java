package com.klef.soa.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.exam.entity.NGOEntity;
import com.klef.soa.exam.repository.NGORepository;
import com.klef.soa.exam.service.NGOService;

@Service
public class NGOServiceImpl implements NGOService
{
    @Autowired
    private NGORepository repository;

    @Override
    public NGOEntity addNGO(NGOEntity ngo)
    {
        return repository.save(ngo);
    }

    @Override
    public List<NGOEntity> displayAllNGOs()
    {
        return repository.findAll();
    }

    @Override
    public NGOEntity displayNGOById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    @Override
    public NGOEntity updateNGO(NGOEntity ngo)
    {
        Long id = ngo.getNgoId();

        NGOEntity existingNGO = repository.findById(id).orElse(null);

        if(existingNGO != null)
        {
            return repository.save(ngo);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String deleteNGOById(Long id)
    {
        NGOEntity ngo = repository.findById(id).orElse(null);

        if(ngo != null)
        {
            repository.deleteById(id);
            return "NGO Deleted Successfully";
        }
        else
        {
            return "NGO ID Not Found";
        }
    }

    @Override
    public List<NGOEntity> displayNGOsByName(String name)
    {
        return repository.findByName(name);
    }

    @Override
    public List<NGOEntity> displayNGOsByType(String type)
    {
        return repository.findByType(type);
    }
}