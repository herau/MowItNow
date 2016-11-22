package com.github.herau;

import com.github.herau.configuration.ApplicationProperties;
import com.github.herau.domain.Action;
import com.github.herau.domain.Lawn;
import com.github.herau.observers.MowerPositionConsoleDisplay;
import com.github.herau.service.FileParserService;
import com.github.herau.service.MowerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Path;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MowItNowApplicationTests {

	@Autowired
	private ApplicationProperties properties;

	@Autowired
	private MowerService mowerService;

	@Autowired
	private FileParserService fileParser;

	@Test
	public void contextLoads() throws IOException {
		final Path inputFilePath = properties.getInputFile();

		Action action = fileParser.parse(inputFilePath);

		final Lawn lawn = action.getLawn();

		action.getMovementsByMow().forEach((mower, movements) -> {
			new MowerPositionConsoleDisplay(mower);
			mowerService.move(lawn, mower, movements);
		});
	}

}
