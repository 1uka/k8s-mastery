package com.sa.web;

import com.sa.web.dto.SentenceDto;
import com.sa.web.dto.SentenceRepository;
import com.sa.web.dto.SentimentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
public class SentimentController {

    @Value("${sa.logic.api.url}")
    private String saLogicApiUrl;

    @Autowired
    private SentenceRepository sentenceRepository;

    @PostMapping("/sentiment")
    public SentimentDto sentimentAnalysis(@RequestBody SentenceDto sentenceDto) {
        sentenceRepository.save(sentenceDto);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForEntity(saLogicApiUrl + "/analyse/sentiment", sentenceDto, SentimentDto.class)
                .getBody();
    }

    @GetMapping("/testHealth")
    public @ResponseBody String testHealth() {
        return "Healthy";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<SentenceDto> getAllSentences() {
        return sentenceRepository.findAll();
    }
}
