package edu.iu.habahram.weathermonitoring.controllers;

import edu.iu.habahram.weathermonitoring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/displays")
public class DisplayController {
    private CurrentConditionDisplay currentConditionDisplay;

    public DisplayController(CurrentConditionDisplay currentConditionDisplay,StatisticsDisplay statisticsDisplay
                             , IndexDisplay indexDisplay
                             ) {
        this.currentConditionDisplay = currentConditionDisplay;
        this.statisticsDisplay=statisticsDisplay;
        this.indexDisplay=indexDisplay;
    }

    private StatisticsDisplay statisticsDisplay;

    private IndexDisplay indexDisplay;




    @GetMapping
    public ResponseEntity index() {
        String html =
                String.format("<h1>Available screens:</h1>");
        html += "<ul>";
        html += "<li>";
        html += String.format("<a href=/displays/%s>%s</a>", currentConditionDisplay.id(), currentConditionDisplay.name());
        html += "</li>";
        html += "<li>";
        html += String.format("<a href=/displays/%s>%s</a>", statisticsDisplay.id(), statisticsDisplay.name());
        html += "</li>";
        html += "<li>";
        html += String.format("<a href=/displays/%s>%s</a>", indexDisplay.id(), indexDisplay.name());
        html += "</li>";

        html += "</ul>";
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(html);
    }


    @GetMapping("/{id}")
    public ResponseEntity display(@PathVariable String id) {
        String html = "";
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            html = currentConditionDisplay.display();
            status = HttpStatus.FOUND;
        }
        else if(id.equalsIgnoreCase(statisticsDisplay.id())){
            html = statisticsDisplay.display();
            status = HttpStatus.FOUND;
        }
        else if(id.equalsIgnoreCase(indexDisplay.id())){
            html = indexDisplay.display();
            status = HttpStatus.FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }

    @GetMapping("/{id}/subscribe")
    public ResponseEntity subscribe(@PathVariable String id) {
        String html = "";
        HttpStatus status = null;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            currentConditionDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        }
        else if (id.equalsIgnoreCase(statisticsDisplay.id())){
            statisticsDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        }
        else if (id.equalsIgnoreCase(indexDisplay.id())){
            indexDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        }
        else {
            html = "The screen id is invalid.";
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }

    @GetMapping("/{id}/unsubscribe")
    public ResponseEntity unsubscribe(@PathVariable String id) {
        String html = "";
        HttpStatus status = null;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            currentConditionDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(statisticsDisplay.id())){
            statisticsDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        }
        else if (id.equalsIgnoreCase(indexDisplay.id())){
            indexDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        }

        else
        {
            html = "The screen id is invalid.";
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }
}
