package aej.finalproject.ngojekkuy.location.entity
import com.fasterxml.jackson.annotation.JsonProperty

data class LocationHereResponse(
    @JsonProperty("items")
    var items: List<Item?>?
) {
    data class Item(
        @JsonProperty("access")
        var access: List<Acces?>?,
        @JsonProperty("address")
        var address: Address?,
        @JsonProperty("categories")
        var categories: List<Category?>?,
        @JsonProperty("chains")
        var chains: List<Chain?>?,
        @JsonProperty("contacts")
        var contacts: List<Contact?>?,
        @JsonProperty("distance")
        var distance: Int?,
        @JsonProperty("id")
        var id: String?,
        @JsonProperty("language")
        var language: String?,
        @JsonProperty("ontologyId")
        var ontologyId: String?,
        @JsonProperty("position")
        var position: Position?,
        @JsonProperty("references")
        var references: List<Reference?>?,
        @JsonProperty("resultType")
        var resultType: String?,
        @JsonProperty("title")
        var title: String?
    ) {
        data class Acces(
            @JsonProperty("lat")
            var lat: Double?,
            @JsonProperty("lng")
            var lng: Double?
        )

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

        data class Category(
            @JsonProperty("id")
            var id: String?,
            @JsonProperty("name")
            var name: String?,
            @JsonProperty("primary")
            var primary: Boolean?
        )

        data class Chain(
            @JsonProperty("id")
            var id: String?
        )

        data class Contact(
            @JsonProperty("phone")
            var phone: List<Phone?>?
        ) {
            data class Phone(
                @JsonProperty("value")
                var value: String?
            )
        }

        data class Position(
            @JsonProperty("lat")
            var lat: Double?,
            @JsonProperty("lng")
            var lng: Double?
        )

        data class Reference(
            @JsonProperty("id")
            var id: String?,
            @JsonProperty("supplier")
            var supplier: Supplier?
        ) {
            data class Supplier(
                @JsonProperty("id")
                var id: String?
            )
        }
    }
}