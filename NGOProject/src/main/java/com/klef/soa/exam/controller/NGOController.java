
package com.klef.soa.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.exam.entity.NGOEntity;
import com.klef.soa.exam.service.NGOService;

@RestController
@RequestMapping("/ngo")
public class NGOController
{
    @Autowired
    private NGOService service;

    @GetMapping("/")
    public String home()
    {
        return "SOA Programming & MicroServices NGO Entity";
    }

    @PostMapping("/add")
    public ResponseEntity<NGOEntity> addNGO(@RequestBody NGOEntity ngo)
    {
        NGOEntity savedNGO = service.addNGO(ngo);
        return new ResponseEntity<NGOEntity>(savedNGO, HttpStatus.CREATED);
    }

    @GetMapping("/displayall")
    public ResponseEntity<List<NGOEntity>> displayAllNGOs()
    {
        List<NGOEntity> list = service.displayAllNGOs();
        return new ResponseEntity<List<NGOEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<NGOEntity> displayNGOById(@RequestParam Long id)
    {
        NGOEntity ngo = service.displayNGOById(id);

        if(ngo != null)
        {
            return new ResponseEntity<NGOEntity>(ngo, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<NGOEntity>((NGOEntity) null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<NGOEntity> updateNGO(@RequestBody NGOEntity ngo)
    {
        NGOEntity updatedNGO = service.updateNGO(ngo);

        if(updatedNGO != null)
        {
            return new ResponseEntity<NGOEntity>(updatedNGO, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<NGOEntity>((NGOEntity) null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNGOById(@PathVariable Long id)
    {
        String message = service.deleteNGOById(id);

        if(message.equals("NGO Deleted Successfully"))
        {
            return new ResponseEntity<String>(message, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/displaybyname/{name}")
    public ResponseEntity<List<NGOEntity>> displayNGOsByName(@PathVariable String name)
    {
        List<NGOEntity> list = service.displayNGOsByName(name);
        return new ResponseEntity<List<NGOEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/displaybytype/{type}")
    public ResponseEntity<List<NGOEntity>> displayNGOsByType(@PathVariable String type)
    {
        List<NGOEntity> list = service.displayNGOsByType(type);
        return new ResponseEntity<List<NGOEntity>>(list, HttpStatus.OK);
    }
}
