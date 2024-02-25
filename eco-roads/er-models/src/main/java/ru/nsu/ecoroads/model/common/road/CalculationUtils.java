package ru.nsu.ecoroads.model.common.road;

import ru.nsu.ecoroads.model.common.base.Coordinates;

/**
 * Можно использовать запросы к routing сервисам, но запросов будет сильно много.
 * Поэтому для приблизительной оценки длины можем использовать формулу Хаверсина
 */
class CalculationUtils {

    private static final double EARTH_RADIUS = 6371; // Approx Earth radius in KM
    private static final double METERS_IN_KM = 1000; // Approx Earth radius in KM

    static double getDistanceBetweenCoordinatesInMeters(Coordinates coordinatesA, Coordinates coordinatesB) {
        double dLat = Math.toRadians(coordinatesB.latitude() - coordinatesA.latitude());
        double dLong = Math.toRadians(coordinatesB.longitude() - coordinatesA.longitude());

        double startLat = Math.toRadians(coordinatesA.latitude());
        double endLat = Math.toRadians(coordinatesB.latitude());

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c * METERS_IN_KM;
    }

    private static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

}
