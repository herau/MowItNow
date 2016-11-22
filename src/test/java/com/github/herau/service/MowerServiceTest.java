package com.github.herau.service;

import com.github.herau.domain.Lawn;
import com.github.herau.domain.Mower;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.herau.domain.Cardinal.N;
import static com.github.herau.domain.Movement.A;
import static com.github.herau.domain.Movement.D;
import static com.github.herau.domain.Movement.G;
import static org.junit.Assert.assertEquals;

public class MowerServiceTest {

    private MowerService service;
    private Lawn lawn;
    private Mower mower;

    @Before
    public void setUp() throws Exception {
        service = new MowerService();
        lawn = new Lawn(5, 5);
        mower = new Mower(1, 2, N);
    }

    @Test
    public void move_12N_D_12E() throws Exception {
        String position = service.move(lawn, mower, Stream.of(D));
        assertEquals("1 2 E", position);
    }

    @Test
    public void move_12N_G_12W() throws Exception {
        String position = service.move(lawn, mower, Stream.of(G));
        assertEquals("1 2 W", position);
    }

    @Test
    public void move_12N_A_13N() throws Exception {
        String position = service.move(lawn, mower, Stream.of(A));
        assertEquals("1 3 N", position);
    }

    @Test
    public void move_12N_DA_22E() throws Exception {
        String position = service.move(lawn, mower, Stream.of(D, A));
        assertEquals("2 2 E", position);
    }

    @Test
    public void move_12N_GA_02W() throws Exception {
        String position = service.move(lawn, mower, Stream.of(G, A));
        assertEquals("0 2 W", position);
    }

    @Test
    public void move_12N_DDA_11S() throws Exception {
        String position = service.move(lawn, mower, Stream.of(D, D, A));
        assertEquals("1 1 S", position);
    }

    @Test
    public void move_12N_AA_14N() throws Exception {
        String position = service.move(lawn, mower, Stream.of(A, A));
        assertEquals("1 4 N", position);
    }

}