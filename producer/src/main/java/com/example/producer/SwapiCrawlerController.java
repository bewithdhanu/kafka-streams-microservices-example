package com.example.producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swapi")
public class SwapiCrawlerController {


    private final SwapiCrawlerService swapiCrawlerService;

    public SwapiCrawlerController(SwapiCrawlerService swapiCrawlerService) {
        this.swapiCrawlerService = swapiCrawlerService;
    }

    @GetMapping("/films")
    public String lookup() {
        swapiCrawlerService.crawl("");
        return "All movies will scrap now";
    }

    @GetMapping("/films/{name}")
    public String lookupByName(@PathVariable(name = "name", required = false) final String name) {
        swapiCrawlerService.crawl(name);
        return "Movies by name %s will scrap now".formatted(name);
    }

}
