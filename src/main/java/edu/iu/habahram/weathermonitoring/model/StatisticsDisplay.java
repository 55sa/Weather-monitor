package edu.iu.habahram.weathermonitoring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class StatisticsDisplay implements Observer, DisplayElement{

    private float temperature;
    private float humidity;
    private float pressure;

    private List<Float> ts = new ArrayList<>();



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
        Float max= (float) 0;
        Float avg=(float) 0;
        Float min=(float) 0;
        if(ts.size()!=0){
        max=ts.get(0);
        avg=ts.get(0);
         min=ts.get(0);
        Float total= (float) 0;
        for(int i=0;i<ts.size();i++){
            max=Math.max(max,ts.get(i));
            min=Math.min(min,ts.get(i));
            total +=ts.get(i);

        }
        avg=total/ts.size();

        }

        html += String.format("<label>Avg.temp: %s</label><br />", avg);
        html += String.format("<label>Max.temp: %s</label><br />", max);
        html += String.format("<label>Min.temp: %s</label>", min);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

       ts.add(temperature);

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
