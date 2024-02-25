package ru.nsu.ecoroads.model.common.base;


public record Coordinates(double latitude, double longitude) {

    public static Coordinates of(double latitude, double longitude){
        return new Coordinates(latitude, longitude);
    }

    public static double getDistBtwCoords(Coordinates coordinatesA, Coordinates coordinatesB) {
        return Math.pow(Math.abs(coordinatesA.latitude - coordinatesB.latitude), 2)
                + Math.pow(Math.abs(coordinatesA.longitude - coordinatesB.longitude), 2);
    }

}
