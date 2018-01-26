var map;
var firstPoint;
var firstMarker;
var secondMarker;

/**
 * This function initializes the map at the html element with 'map' is the id.
 */
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 3,
        center: {lat: 55.658996, lng: -97.031250},
        mapTypeId: 'terrain'
    });

    // Create a <script> tag and set the USGS URL as the source.
    var script = document.createElement('script');
    map.addListener('click', addPoint);

    // This example uses a local copy of the GeoJSON stored at
    // http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.geojsonp
    script.src = 'https://developers.google.com/maps/documentation/javascript/examples/json/earthquake_GeoJSONP.js';
    document.getElementsByTagName('head')[0].appendChild(script);

}

/**
 * This is the event handler for a map click. It creates markers and provides new bounds to the map
 * if two markers have been created.
 * @param event The click event with a latLng property.
 */
function addPoint(event) {
    console.log(event)
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
    } else {
        secondPoint = event.latLng;
        secondMarker = new google.maps.Marker({
            position: event.latLng,
            map: map
        });
        var newBounds = new google.maps.LatLngBounds(
            firstPoint,
            secondPoint
        );
        map.fitBounds(newBounds);

        firstPoint = null;
    }
}

/**
 * This method creates the heatmap given json results.
 * @param results The json object to render the heatmap.
 */
function eqfeed_callback(results) {
    var heatmapData = [];
    for (var i = 0; i < results.features.length; i++) {
        var coords = results.features[i].geometry.coordinates;
        var latLng = new google.maps.LatLng(coords[1], coords[0]);
        heatmapData.push(latLng);
    }
    var heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatmapData,
        dissipating: false,
        map: map
    });
}