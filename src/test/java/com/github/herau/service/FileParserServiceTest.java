package com.github.herau.service;

import com.github.herau.configuration.ApplicationProperties;
import com.github.herau.domain.Action;
import com.github.herau.domain.Grass;
import com.github.herau.domain.Movement;
import com.github.herau.domain.Mow;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.herau.domain.Cardinal.E;
import static com.github.herau.domain.Cardinal.N;
import static com.github.herau.domain.Movement.A;
import static com.github.herau.domain.Movement.D;
import static com.github.herau.domain.Movement.G;
import static org.junit.Assert.assertEquals;

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
    public void fileParserSerice_parse_ok() throws Exception {
        Grass grass = new Grass(5, 5);
        Mow firstMow = new Mow(1, 2, N);
        Mow secondMow = new Mow(3, 3, E);

        Action action = service.parse(properties.getInputFile());

        assertEquals(0, action.getGrass().getXMin());
        assertEquals(0, action.getGrass().getYMin());
        assertEquals(grass.getXMax(), action.getGrass().getXMax());
        assertEquals(grass.getYMax(), action.getGrass().getYMax());

        Map<Mow, Stream<Movement>> movementsByMow = action.getMovementsByMow();

        assertEquals(2, movementsByMow.entrySet().size());

        assertEquals(Stream.of(G, A, G, A, G, A, G, A, A).collect(Collectors.toList()), movementsByMow.get(firstMow).collect(Collectors.toList()));
        assertEquals(Stream.of(A, A, D, A, A ,D ,A ,D ,D, A).collect(Collectors.toList()), movementsByMow.get(secondMow).collect(Collectors.toList()));
    }

    @Test(expected=IOException.class)
    public void fileParserSerice_parse_invalidInputFile() throws IOException {
        service.parse(Paths.get(""));
    }

}