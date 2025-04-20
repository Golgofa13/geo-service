package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageSenderTest {
    @Test
    public void testSendMessageForRussianUser() {
        // Arrange
        String expected = "Добро пожаловать";

        // Создаем моки сервисов
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("MOSCOW_IP"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        // Создаем тестируемый объект
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<>();

        // Act
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, "MOSCOW_IP");
        String result = messageSender.send(header);

        // Assert
        assertEquals(expected, result);
    }
}