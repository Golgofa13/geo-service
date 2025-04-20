package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTest {
    //Парметризованый тест метода определяющего локацию по ip
    public static Stream<Arguments> ParameterizedGeoServiceTest() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST,
                        new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP,
                        new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP,
                        new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.",
                        new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.",
                        new Location("New York", Country.USA, null,  0))
                );
    }

    @ParameterizedTest
    @MethodSource
    public void ParameterizedGeoServiceTest(String ip, Location expected){
        //act
        GeoService locationByIp = new GeoServiceImpl();
        Location location = locationByIp.byIp(ip);

        //assert
        assertEquals(expected, location);
    }

    @Test
    public void GeoServiceByCoordinates(){
        // arrange
        double latitude = 21.5;
        double longitude = 5.4;
        Location location = null;
        
        //act
        try {
            GeoService locationByCoordinates = new GeoServiceImpl();
            location = locationByCoordinates.byCoordinates(latitude,longitude);
        }
        catch (RuntimeException _){
        }

        //assert
        assertThrows(RuntimeException.class, (Executable) location);

    }

}
