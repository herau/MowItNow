package com.github.herau.web;

import com.github.herau.domain.MowingAction;
import com.github.herau.observers.MowerStepsObserver;
import com.github.herau.service.FileParserService;
import com.github.herau.service.MowerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@RestController
public class UploadController {

    private final MowerService mowerService;

    private final FileParserService fileParser;

    public UploadController(MowerService mowerService, FileParserService fileParser) {
        this.mowerService = mowerService;
        this.fileParser = fileParser;
    }

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile(file.getName(), UUID.randomUUID().toString());
        file.transferTo(tempFile.toFile());

        MowingAction mowingAction = fileParser.parse(tempFile);

        MowerStepsObserver observer = new MowerStepsObserver(mowingAction.getLawn());

        mowingAction.getMovementsByMow().forEach((mower, movements) -> {
            observer.addObservable(mower);
            mowerService.move(mowingAction.getLawn(), mower, movements);
        });

        return observer.getDto();
    }
}
