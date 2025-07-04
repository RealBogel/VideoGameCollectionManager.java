// Tristan Suwito
// 6 June 2025
// CSE 123
// TA: Rushil Arun & Chris Ma
// C3: B(e)ST of the B(e)ST
// A non-trivial test for every public method that you write in your CollectionManager class.
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.io.*;

public class Testing {
    // TODO: Write your tests here!
    private CollectionManager manage;
    private VideoGame minecraft;
    private VideoGame fortnite;
    private VideoGame stardew;
    private VideoGame celeste;
    private VideoGame smashBros;

    @BeforeEach
    public void setUp() {
        this.minecraft = new VideoGame("Minecraft", 140000000, 2011,
                         List.of("PC", "Xbox", "PS4", "Switch", "Mobile"));
        this.fortnite = new VideoGame("Fortnite", 250000000, 2017, 
                         List.of("PC", "Xbox", "PS4", "Switch", "Mobile"));
        this.stardew = new VideoGame("Stardew Valley", 20000000, 2016,
                         List.of("PC", "PS4", "Xbox", "Switch", "Mobile"));
        this.celeste = new VideoGame("Celeste", 1000000, 2018, 
                         List.of("PC", "Switch", "PS4", "Xbox"));
        this.smashBros = new VideoGame("Super Smash Bros. Ultimate", 32000000, 2018, 
                         List.of("Switch"));
        manage = new CollectionManager();
        manage.add(minecraft);
        manage.add(fortnite);
        manage.add(stardew);
        manage.add(celeste);
        manage.add(smashBros);
    }

    @Test 
    public void testConstructorScanner() {
        String input = "Celeste\n1000000\n2018\nPC Switch PlayStation Xbox\n" +
                       "Super Smash Bros. Ultimate\n32000000\n2018\nSwitch\n";
        Scanner scan = new Scanner(input);
        CollectionManager load = new CollectionManager(scan);
        assertTrue(load.contains(celeste));
        assertTrue(load.contains(smashBros));
    }

    @Test
    public void testAddAndContains() {
        VideoGame amongUs = new VideoGame("Among Us", 500000, 2018,
                        List.of("PC", "Mobile", "Switch"));
        assertFalse(manage.contains(amongUs));
        manage.add(amongUs);
        assertTrue(manage.contains(amongUs));
    }

    @Test
    public void testContainsExisting() {
        assertTrue(manage.contains(minecraft));
        assertTrue(manage.contains(fortnite));
    }

    @Test
    public void testToString() {
        String result = manage.toString();
        String expected = 
            "Video Game: Celeste | Player Count: 1000000 |" +
            " Release Year: 2018 | Platforms: [PC, Switch, PS4, Xbox]\n" +
            "Video Game: Fortnite | Player Count: 250000000 |" +
            " Release Year: 2017 | Platforms: [PC, Xbox, PS4, Switch, Mobile]\n" +
            "Video Game: Minecraft | Player Count: 140000000 |" +
            " Release Year: 2011 | Platforms: [PC, Xbox, PS4, Switch, Mobile]\n" +
            "Video Game: Stardew Valley | Player Count: 20000000 |" +
            " Release Year: 2016 | Platforms: [PC, PS4, Xbox, Switch, Mobile]\n" +
            "Video Game: Super Smash Bros. Ultimate | Player Count: 32000000 |" +
            " Release Year: 2018 | Platforms: [Switch]\n";
            assertEquals(expected, result);
    }

    @Test
    public void testSaveFormat() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(output);
        manage.save(print);
        String expected =
                "Minecraft\n140000000\n2011\nPC Xbox PS4 Switch Mobile\n" +
                "Fortnite\n250000000\n2017\nPC Xbox PS4 Switch Mobile\n" +
                "Celeste\n1000000\n2018\nPC Switch PS4 Xbox\n" +
                "Stardew Valley\n20000000\n2016\nPC PS4 Xbox Switch Mobile\n" +
                "Super Smash Bros. Ultimate\n32000000\n2018\nSwitch\n";
        assertEquals(expected, output.toString());
    }

    @Test
    public void testFilter() {
        List<VideoGame> results = manage.filter("Switch");
        assertTrue(results.contains(minecraft));
        assertTrue(results.contains(stardew));
        assertTrue(results.contains(smashBros));
        assertEquals(5, results.size());
    }

    @Test
    public void testFilterNone() {
        List<VideoGame> results = manage.filter("NES");
        assertTrue(results.isEmpty());
    }
}
