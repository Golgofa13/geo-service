package ru.netology.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    // Параметрезованые тесты с четырьмя параметрами
    public static Stream<Arguments> ParameterizedLocationTest() {
        return Stream.of(
                  Arguments.of("Moscow", Country.RUSSIA, "Lenina", 12,
                            new Location("Moscow", Country.RUSSIA, "Lenina", 12)),
                  Arguments.of("New York", Country.USA, "Wall street", 1,
                            new Location("New York", Country.USA, "Wall street", 1)
                  ));
    }

    @ParameterizedTest
    @MethodSource
    public void ParameterizedLocationTest(String city, Country country, String street, int building, Location expected) {
        // act
        Location location = new Location(city, country, street, building);

        // assert
        assertEquals(expected, location);
    }

}
