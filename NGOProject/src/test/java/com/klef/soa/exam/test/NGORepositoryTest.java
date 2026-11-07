
package com.klef.soa.exam.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.exam.entity.NGOEntity;
import com.klef.soa.exam.repository.NGORepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NGORepositoryTest
{
    @Autowired
    private NGORepository repository;

    private Long ngoId;

    @BeforeEach
    void setUp()
    {
        repository.deleteAll();

        NGOEntity ngo1 = new NGOEntity();
        ngo1.setName("Helping Hands");
        ngo1.setType("Education");
        ngo1.setStatus("Active");
        ngo1.setLocation("Vijayawada");
        ngo1.setCreatedAt(LocalDateTime.now());
        ngo1.setUpdatedAt(LocalDateTime.now());

        NGOEntity savedNGO = repository.save(ngo1);
        ngoId = savedNGO.getNgoId();

        NGOEntity ngo2 = new NGOEntity();
        ngo2.setName("Green Earth");
        ngo2.setType("Environment");
        ngo2.setStatus("Active");
        ngo2.setLocation("Guntur");
        ngo2.setCreatedAt(LocalDateTime.now());
        ngo2.setUpdatedAt(LocalDateTime.now());

        repository.save(ngo2);
    }

   
    @Test
    void testFindAllNGOs()
    {
        List<NGOEntity> ngos = repository.findAll();

        assertNotNull(ngos);
        assertEquals(2, ngos.size());

        System.out.println("Total NGOs: " + ngos.size());
    }

   
    @Test
    void testFindNGOById()
    {
        Optional<NGOEntity> ngo = repository.findById(ngoId);

        assertTrue(ngo.isPresent());
        assertEquals(ngoId, ngo.get().getNgoId());

        System.out.println("NGO ID: " + ngo.get().getNgoId());

        Optional<NGOEntity> invalidNGO =
                repository.findById(999999L);

        assertFalse(invalidNGO.isPresent());
    }

    
    @Test
    void testFindNGOByName()
    {
        List<NGOEntity> ngos =
                repository.findByName("Helping Hands");

        assertNotNull(ngos);
        assertFalse(ngos.isEmpty());
        assertEquals("Helping Hands", ngos.get(0).getName());

        System.out.println("NGO Name: " + ngos.get(0).getName());
    }

  
    @Test
    void testTotalNGOCount()
    {
        long count = repository.count();

        assertEquals(2L, count);

        System.out.println("Total NGOs in Database: " + count);
    }

   
    @Test
    void testNGODetails()
    {

        Optional<NGOEntity> ngo =
                repository.findById(ngoId);

        assertTrue(ngo.isPresent());

        NGOEntity foundNGO = ngo.get();

        assertNotNull(foundNGO.getNgoId());
        assertNotNull(foundNGO.getName());
        assertNotNull(foundNGO.getType());
        assertNotNull(foundNGO.getStatus());
        assertNotNull(foundNGO.getLocation());

        System.out.println("Name: " + foundNGO.getName());
        System.out.println("Type: " + foundNGO.getType());
        System.out.println("Status: " + foundNGO.getStatus());
        System.out.println("Location: " + foundNGO.getLocation());
    }
}
