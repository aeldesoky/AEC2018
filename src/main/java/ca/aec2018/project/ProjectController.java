package ca.aec2018.project;

import ca.aec2018.project.model.RenewableService;
import ca.aec2018.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


@EnableAutoConfiguration
@Controller
public class ProjectController {

    @Autowired
    RenewableService renewableService;


    CoordinateUtil coordinateUtil = new CoordinateUtil();

    /**
     * The controller method for the index page.
     * @return The twig file name.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * The controller method for the solar page.
     * @return The twig file name.
     */
    @GetMapping("/solar")
    public String solar() {
        return "solar";
    }

    /**
     * The controller method for the wind page.
     * @return The twig file name.
     */
    @GetMapping("/wind")
    public String wind() {
        return "wind";
    }

    /**
     * The controller method for the documentation page.
     * @return The twig file name.
     */
    @GetMapping("/docs")
    public String docs() {
        return "docs";
    }

    /**
     * The REST controller method to get the wind data.
     * @return The Google JSON objects to render the wind heat map.
     */
    @GetMapping("/wind/{month}")
    @ResponseBody
    public List<GoogleRenewable> getWindData(@PathVariable int month) {
        return renewableService.getRenewablesByMonth(false, month);
    }

    /**
     * The REST controller method to get the solar data.
     * @return The Google JSON objects to render the solar heat map.
     */
    @GetMapping("/solar/{month}")
    @ResponseBody
    public List<GoogleRenewable> getSolarData(@PathVariable int month) {
        return renewableService.getRenewablesByMonth(false, month);
    }

    /**
     * The REST controller method to get the wind data for a specific coordinate.
     * @return The Google JSON objects to render the wind heat map.
     */
    @GetMapping("/wind/coordinate/{lat}/{lng}")
    @ResponseBody
    public ArrayList<Double> getWindDataForCoordinate(@PathVariable Double lat, @PathVariable Double lng) {
        Coordinate coord = new Coordinate(lat, lng);
        return coordinateUtil.getCoordRenewables(false, coord).getMonthValues();
    }

    /**
     * The REST controller method to get the solar data for a specific coordinate.
     * @return The Google JSON objects to render the solar heat map.
     */
    @GetMapping("/solar/coordinate/{lat}/{lng}")
    @ResponseBody
    public ArrayList<Double>  getSolarDataForCoordinate(@PathVariable Double lat, @PathVariable Double lng) {
        Coordinate coord = new Coordinate(lat, lng);
        return coordinateUtil.getCoordRenewables(true, coord).getMonthValues();
    }

    /**
     * The REST controller method to get the wind data for a region enclosed within a rectangle created by two coordinates.
     * @return The Google JSON objects to render the wind heat map.
     */
    @GetMapping("/wind/coordinates")
    @ResponseBody
    public Wind getWindDataForRegion(@RequestBody List<Double> coordinateX, @RequestBody List<Double> coordinateY) {
        List<Renewable> windRanges = coordinateUtil.renewablesInRange(new Coordinate(coordinateX.get(0), coordinateX.get(1)), new Coordinate(coordinateY.get(0), coordinateY.get(1)), false);

        Wind aggregatedWind = new Wind();

        for(Renewable wind : windRanges) {
            aggregatedWind.setJanuary(aggregatedWind.getJanuary() + wind.getJanuary());
            aggregatedWind.setFebruary(aggregatedWind.getFebruary() + wind.getFebruary());
            aggregatedWind.setMarch(aggregatedWind.getMarch() + wind.getMarch());
            aggregatedWind.setApril(aggregatedWind.getApril() + wind.getApril());
            aggregatedWind.setMay(aggregatedWind.getMay() + wind.getMay());
            aggregatedWind.setJune(aggregatedWind.getJune() + wind.getJune());
            aggregatedWind.setJuly(aggregatedWind.getJuly() + wind.getJuly());
            aggregatedWind.setAugust(aggregatedWind.getAugust() + wind.getAugust());
            aggregatedWind.setSeptember(aggregatedWind.getSeptember() + wind.getSeptember());
            aggregatedWind.setOctober(aggregatedWind.getOctober() + wind.getOctober());
            aggregatedWind.setNovember(aggregatedWind.getNovember() + wind.getNovember());
            aggregatedWind.setDecember(aggregatedWind.getDecember() + wind.getDecember());
            aggregatedWind.setAnnualAverage(aggregatedWind.getAnnualAverage() + wind.getAnnualAverage());
        }

        aggregatedWind.setJanuary(aggregatedWind.getJanuary() / windRanges.size());
        aggregatedWind.setFebruary(aggregatedWind.getFebruary() / windRanges.size());
        aggregatedWind.setMarch(aggregatedWind.getMarch() / windRanges.size());
        aggregatedWind.setApril(aggregatedWind.getApril() / windRanges.size());
        aggregatedWind.setMay(aggregatedWind.getMay() / windRanges.size());
        aggregatedWind.setJune(aggregatedWind.getJune() / windRanges.size());
        aggregatedWind.setJuly(aggregatedWind.getJuly() / windRanges.size());
        aggregatedWind.setAugust(aggregatedWind.getAugust() / windRanges.size());
        aggregatedWind.setSeptember(aggregatedWind.getSeptember() / windRanges.size());
        aggregatedWind.setOctober(aggregatedWind.getOctober() / windRanges.size());
        aggregatedWind.setNovember(aggregatedWind.getNovember() / windRanges.size());
        aggregatedWind.setDecember(aggregatedWind.getDecember() / windRanges.size());
        aggregatedWind.setAnnualAverage(aggregatedWind.getAnnualAverage() / windRanges.size());

        return aggregatedWind;
    }

    /**
     * The REST controller method to get the solar data for a region enclosed within a rectangle created by two coordinates.
     * @return The Google JSON objects to render the solar heat map.
     */
    @GetMapping("/solar/coordinates")
    @ResponseBody
    public Solar getSolarDataForRegion(@RequestBody List<Double> coordinateX, @RequestBody List<Integer> coordinateY) {
        List<Renewable> solarRanges = coordinateUtil.renewablesInRange(new Coordinate(coordinateX.get(0), coordinateX.get(1)), new Coordinate(coordinateY.get(0), coordinateY.get(1)), false);

        Solar aggregatedSolar = new Solar();

        for(Renewable solar : solarRanges) {
            aggregatedSolar.setJanuary(aggregatedSolar.getJanuary() + solar.getJanuary());
            aggregatedSolar.setFebruary(aggregatedSolar.getFebruary() + solar.getFebruary());
            aggregatedSolar.setMarch(aggregatedSolar.getMarch() + solar.getMarch());
            aggregatedSolar.setApril(aggregatedSolar.getApril() + solar.getApril());
            aggregatedSolar.setMay(aggregatedSolar.getMay() + solar.getMay());
            aggregatedSolar.setJune(aggregatedSolar.getJune() + solar.getJune());
            aggregatedSolar.setJuly(aggregatedSolar.getJuly() + solar.getJuly());
            aggregatedSolar.setAugust(aggregatedSolar.getAugust() + solar.getAugust());
            aggregatedSolar.setSeptember(aggregatedSolar.getSeptember() + solar.getSeptember());
            aggregatedSolar.setOctober(aggregatedSolar.getOctober() + solar.getOctober());
            aggregatedSolar.setNovember(aggregatedSolar.getNovember() + solar.getNovember());
            aggregatedSolar.setDecember(aggregatedSolar.getDecember() + solar.getDecember());
            aggregatedSolar.setAnnualAverage(aggregatedSolar.getAnnualAverage() + solar.getAnnualAverage());
        }

        aggregatedSolar.setJanuary(aggregatedSolar.getJanuary() / solarRanges.size());
        aggregatedSolar.setFebruary(aggregatedSolar.getFebruary() / solarRanges.size());
        aggregatedSolar.setMarch(aggregatedSolar.getMarch() / solarRanges.size());
        aggregatedSolar.setApril(aggregatedSolar.getApril() / solarRanges.size());
        aggregatedSolar.setMay(aggregatedSolar.getMay() / solarRanges.size());
        aggregatedSolar.setJune(aggregatedSolar.getJune() / solarRanges.size());
        aggregatedSolar.setJuly(aggregatedSolar.getJuly() / solarRanges.size());
        aggregatedSolar.setAugust(aggregatedSolar.getAugust() / solarRanges.size());
        aggregatedSolar.setSeptember(aggregatedSolar.getSeptember() / solarRanges.size());
        aggregatedSolar.setOctober(aggregatedSolar.getOctober() / solarRanges.size());
        aggregatedSolar.setNovember(aggregatedSolar.getNovember() / solarRanges.size());
        aggregatedSolar.setDecember(aggregatedSolar.getDecember() / solarRanges.size());
        aggregatedSolar.setAnnualAverage(aggregatedSolar.getAnnualAverage() / solarRanges.size());

        return aggregatedSolar;
    }
}
