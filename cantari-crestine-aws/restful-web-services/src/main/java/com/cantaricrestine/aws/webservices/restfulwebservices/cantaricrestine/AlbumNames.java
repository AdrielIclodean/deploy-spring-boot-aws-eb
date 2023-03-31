package com.cantaricrestine.aws.webservices.restfulwebservices.cantaricrestine;

public enum AlbumNames {
    BBSO("BBSO"),
    CARTEA_DE_CANTARI("Cartea de cantari"),
    COLINDE("Colinde"),
    COPII("Copii"),
    PUIU_CHIBICI("Puiu Chibici"),
    TINERI_BARTOLOMEU("Tineri Bartolomeu"),
    TINERI_NOUA_GENERATIE("Tineri noua generație"),
    TINERI("Tineri");

    String value;

    AlbumNames(String albumName) {
        this.value = albumName;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AlbumNames{" +
                "value='" + value + '\'' +
                '}';
    }
}
