package aej.finalproject.ngojekkuy.location.entity


import com.fasterxml.jackson.annotation.JsonProperty

data class LocationHereReserveResponse(
    @JsonProperty("items")
    var items: List<Item?>?
) {
    data class Item(
        @JsonProperty("address")
        var address: Address?,
        @JsonProperty("distance")
        var distance: Int?,
        @JsonProperty("id")
        var id: String?,
        @JsonProperty("mapView")
        var mapView: MapView?,
        @JsonProperty("position")
        var position: Position?,
        @JsonProperty("resultType")
        var resultType: String?,
        @JsonProperty("title")
        var title: String?
    ) {
        data class Address(
            @JsonProperty("city")
            var city: String?,
            @JsonProperty("countryCode")
            var countryCode: String?,
            @JsonProperty("countryName")
            var countryName: String?,
            @JsonProperty("county")
            var county: String?,
            @JsonProperty("district")
            var district: String?,
            @JsonProperty("label")
            var label: String?,
            @JsonProperty("postalCode")
            var postalCode: String?,
            @JsonProperty("street")
            var street: String?,
            @JsonProperty("subdistrict")
            var subdistrict: String?
        )

        data class MapView(
            @JsonProperty("east")
            var east: Double?,
            @JsonProperty("north")
            var north: Double?,
            @JsonProperty("south")
            var south: Double?,
            @JsonProperty("west")
            var west: Double?
        )

        data class Position(
            @JsonProperty("lat")
            var lat: Double?,
            @JsonProperty("lng")
            var lng: Double?
        )
    }
}