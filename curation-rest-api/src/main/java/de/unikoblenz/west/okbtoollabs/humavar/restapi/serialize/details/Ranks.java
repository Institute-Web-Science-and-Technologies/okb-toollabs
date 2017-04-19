package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 15:34.
 */
public enum Ranks {
    preferred("preferred"),
    normal("normal"),
    deprecated("deprecated");

    private String rank;

    Ranks(String rank) {this.rank = rank;}

    public String getRank() {return rank;}

}
