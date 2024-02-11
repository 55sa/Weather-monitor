package edu.iu.habahram.weathermonitoring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class StatisticsDisplay implements Observer, DisplayElement{

    private float temperature;
    private float humidity;
    private float pressure;



    @Autowired
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }



    @Override
    public String display() {
        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html +=" <h1>Weather</h1>";
        html +=" <h1>Static</h1>";
        html += String.format("<label>Avg.temp: %s</label><br />", temperature);
        html += String.format("<label>Max.temp: %s</label><br />", temperature);
        html += String.format("<label>Min.temp: %s</label>", temperature);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

    }


    @Override
    public String name() {
        return "Statistics display";
    }

    @Override
    public String id() {
        return "statistics";
    }


    public void subscribe() {

        weatherData.registerObserver(this);

    }

    public void unsubscribe() {
        weatherData.removeObserver(this);

    }
}
