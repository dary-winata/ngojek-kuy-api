package aej.finalproject.ngojekkuy.location.entity


import com.fasterxml.jackson.annotation.JsonProperty

data class LocationHereRouteResponse(
    @JsonProperty("routes")
    var routes: List<Route?>?
) {
    data class Route(
        @JsonProperty("id")
        var id: String?,
        @JsonProperty("sections")
        var sections: List<Section?>?
    ) {
        data class Section(
            @JsonProperty("arrival")
            var arrival: Arrival?,
            @JsonProperty("departure")
            var departure: Departure?,
            @JsonProperty("id")
            var id: String?,
            @JsonProperty("polyline")
            var polyline: String?,
            @JsonProperty("transport")
            var transport: Transport?,
            @JsonProperty("type")
            var type: String?
        ) {
            data class Arrival(
                @JsonProperty("place")
                var place: Place?,
                @JsonProperty("time")
                var time: String?
            ) {
                data class Place(
                    @JsonProperty("location")
                    var location: Location?,
                    @JsonProperty("originalLocation")
                    var originalLocation: OriginalLocation?,
                    @JsonProperty("type")
                    var type: String?
                ) {
                    data class Location(
                        @JsonProperty("lat")
                        var lat: Double?,
                        @JsonProperty("lng")
                        var lng: Double?
                    )

                    data class OriginalLocation(
                        @JsonProperty("lat")
                        var lat: Double?,
                        @JsonProperty("lng")
                        var lng: Double?
                    )
                }
            }

            data class Departure(
                @JsonProperty("place")
                var place: Place?,
                @JsonProperty("time")
                var time: String?
            ) {
                data class Place(
                    @JsonProperty("location")
                    var location: Location?,
                    @JsonProperty("originalLocation")
                    var originalLocation: OriginalLocation?,
                    @JsonProperty("type")
                    var type: String?
                ) {
                    data class Location(
                        @JsonProperty("lat")
                        var lat: Double?,
                        @JsonProperty("lng")
                        var lng: Double?
                    )

                    data class OriginalLocation(
                        @JsonProperty("lat")
                        var lat: Double?,
                        @JsonProperty("lng")
                        var lng: Double?
                    )
                }
            }

            data class Transport(
                @JsonProperty("mode")
                var mode: String?
            )
        }
    }
}