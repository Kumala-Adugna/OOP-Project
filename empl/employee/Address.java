
package employee;

public class Address {
    private String city, subCity, country;

    public Address(String city, String subCity, String country) {
        this.city = city;
        this.subCity = subCity;
        this.country = country;
    }

    @Override
    public String toString() {
        String c1 = (city == null || city.isBlank()) ? "?" : city;
        String c2 = (subCity == null || subCity.isBlank()) ? "?" : subCity;
        String c3 = (country == null || country.isBlank()) ? "?" : country;
        return c1 + ", " + c2 + ", " + c3;
    }
}