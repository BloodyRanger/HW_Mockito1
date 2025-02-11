package geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTest {

    @ParameterizedTest
    @MethodSource("source")
    public void testByIP(String ip, Country expected) {
        Location location = new GeoServiceImpl().byIp(ip);
        Country result = location.getCountry();

        assertEquals(expected, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(GeoServiceImpl.MOSCOW_IP, Country.RUSSIA),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, Country.USA));
    }

    @Test
    public void testByCoordinates() {
        double a = 1.0, b = 1.0;
        var expected = RuntimeException.class;
        assertThrows(expected,
                () -> new GeoServiceImpl().byCoordinates(a, b));
    }
}
