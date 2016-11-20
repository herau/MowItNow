package com.github.herau.service;

import com.github.herau.ApplicationProperties;
import com.github.herau.domain.Action;
import com.github.herau.domain.Mow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by n27 on 11/20/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FileParserServiceTest {

    private ApplicationProperties properties;

    private FileParserService service;

    @Before
    public void setUp() throws Exception {
        properties = new ApplicationProperties();
        String inputFilePath = this.getClass().getResource("/defaultInputFile").getFile();
        properties.setInputFile(Paths.get(inputFilePath));

        service = new FileParserService();
    }

    @Test
    public void parse() throws Exception {

        Action action = service.parse(properties.getInputFile());

        assertEquals(5, action.getGrass().getXMax());
        assertEquals(5, action.getGrass().getYMax());

        Map<Mow, List<Movement>> movementsByMow = action.getMovementsByMow();

        assertEquals(2, movementsByMow.entrySet().size());

//        TODO add more test
    }

}