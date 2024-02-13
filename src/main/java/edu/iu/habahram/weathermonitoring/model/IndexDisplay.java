package edu.iu.habahram.weathermonitoring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;

     private float heatindex;




    private Subject weatherData;

    public IndexDisplay(Subject weatherData) {
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
        html +=" <h1>HeatIndex</h1>";

        html += String.format("<label>Index: %s</label>", heatindex);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.heatindex=computeHeatIndex(temperature,humidity);

    }

    private float computeHeatIndex(float t, float rh) {
        float index = (float)((16.923 + (0.185212 * t) + (5.37941 * rh) - (0.100254 * t * rh) +
                (0.00941695 * (t * t)) + (0.00728898 * (rh * rh)) +
                (0.000345372 * (t * t * rh)) - (0.000814971 * (t * rh * rh)) +
                (0.0000102102 * (t * t * rh * rh)) - (0.000038646 * (t * t * t)) + (0.0000291583 *
                (rh * rh * rh)) + (0.00000142721 * (t * t * t * rh)) +
                (0.000000197483 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh)) +
                0.000000000843296 * (t * t * rh * rh * rh)) -
                (0.0000000000481975 * (t * t * t * rh * rh * rh)));
        return index;}

    @Override
    public String name() {
        return "Index display";
    }

    @Override
    public String id() {
        return "index";
    }


    public void subscribe() {

        weatherData.registerObserver(this);

    }

    public void unsubscribe() {
        weatherData.removeObserver(this);

    }
}

