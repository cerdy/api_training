package fr.esiea.ex4A.user;

public class NameCountry {
    public final String name;
    public final String country;
    public NameCountry(String name, String country) {
        this.name = name;
        this.country = country;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)return true;
        if (!(o instanceof NameCountry)) return false;
        NameCountry nc = (NameCountry) o;
        String nameCountry1 = "";
        String nameCountry2 = "";
        if(this.name != null) nameCountry1 += this.name;
        if(nc.name != null) nameCountry2 += nc.name;
        if(this.country != null) nameCountry1 += this.country;
        if(nc.country != null) nameCountry2 += nc.country;
        return nameCountry1.equalsIgnoreCase(nameCountry2);
    }
    @Override
    public int hashCode() {
        int hc = 0;
        if(name != null) {
            hc += name.length();
        }
        if(country != null) {
            hc += country.length();
        }
        return hc;
    }
}
