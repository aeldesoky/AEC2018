var map;
var firstPoint;
var firstMarker;
var secondMarker;
var secondPoint;
var rectangle;

/**
 * This function initializes the map at the html element with 'map' is the id.
 */
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 4,
        center: {lat: 55.658996, lng: -97.031250},
        mapTypeId: 'terrain'
    });

    // Create a <script> tag and set the USGS URL as the source.
    var script = document.createElement('script');
    map.addListener('click', addPoint);

    // This example uses a local copy of the GeoJSON stored at
    // http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.geojsonp
    monthChange()
}

/**
 * This is the event handler for a map click. It creates markers and provides new bounds to the map
 * if two markers have been created.
 * @param event The click event with a latLng property.
 */
function addPoint(event) {
    if(firstPoint === undefined || firstPoint === null){
        if(firstMarker !== undefined) {

            firstMarker.setMap(null);
            secondMarker.setMap(null);
        }

        firstPoint = event.latLng;

        firstMarker = new google.maps.Marker({
            position: event.latLng,
            map: map
        });

        renderGraphForCoordinate([event.latLng.lat(), event.latLng.lng()])
    } else {
        if(rectangle !== undefined) {
            rectangle.setMap(null);
        }

        secondPoint = event.latLng;
        secondMarker = new google.maps.Marker({
            position: event.latLng,
            map: map
        });

        north = max(firstPoint.lat(), secondPoint.lat());
        south = min(firstPoint.lat(), secondPoint.lat)();
        east = max(firstPoint.lng(), secondPoint.lng());
        west = min(firstPoint.lng(), secondPoint.lng());

        rectangle = new google.maps.Rectangle({
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: '#FF0000',
            fillOpacity: 0.35,
            map: map,
            bounds: {
                north: north,
                south: south,
                east: east,
                west: west
            }
        });


        firstPoint = null;
    }
}

/**
 * This method creates the heatmap given json results.
 * @param results The json object to render the heatmap.
 */
function eqfeed_callback(results) {
    console.log(results)
    var heatmapData = [];
    for (var i = 0; i < results.length; i++) {
        var renewable = results[i]
        var coords = renewable.geometry.coordinates;
        var mag = renewable.properties.mag/5;
        var latLng = new google.maps.LatLng(coords[0], coords[1]);
        var weighted = {
            location: latLng,
            weight: mag
        };
        heatmapData.push(weighted);
    }
    console.log(heatmapData);
    var heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatmapData,
        map: map,
        radius: 20
    });
}

/**
 * This function returns the minumum value of the two numbers.
 * @param The first number.
 * @param The second number.
 * @returns The smallest of the two numbers.
 */
function min(num1, num2) {
    if(num1 < num2){
        return num1;
    }

    return num2;
}

/**
 * This function returns the maximum value of the two numbers.
 * @param The first number.
 * @param The second number.
 * @returns The largest of the two numbers.
 */
function max(num1, num2) {
    if(num1 > num2){
        return num1;
    }

    return num2;
}

function getMonth() {
    return $('#month')[0].value;
}

function monthChange(selector) {
    populateHeatMap(getMonth())

}

function populateHeatMap(month) {
    $.ajax({
        url: '/'+type+'/'+month,
        success: function(googleObjects){
            console.log(googleObjects);
            eqfeed_callback(googleObjects)
        }
    });
}