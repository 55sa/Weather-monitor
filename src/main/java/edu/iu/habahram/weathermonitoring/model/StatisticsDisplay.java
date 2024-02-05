package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component

public class StatisticsDisplay implements Observer, DisplayElement{

    private float Avgtemp;

    private float Maxtemp;

    private float Mintemp;

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
        html += String.format("<label>Avgtemp: %s</label><br />", Avgtemp);
        html += String.format("<label>Maxtemp: %s</label><br />", Maxtemp);
        html += String.format("<label>Mintemp: %s</label>", Mintemp);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float avgtemp, float maxtemp, float mintemp) {
        this.Avgtemp=avgtemp;
        this.Maxtemp=maxtemp;
        this.Mintemp=mintemp;

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
