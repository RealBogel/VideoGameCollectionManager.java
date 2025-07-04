// Tristan Suwito
// 6 June 2025
// CSE 123
// TA: Rushil Arun & Chris Ma
// C3: B(e)ST of the B(e)ST
// This class represents a video game, storing information about it like title, player count,
// release year, and it's support platforms. This class provides methods to access game details,
// compare games(for sorting), and create instances from user or file input. 
import java.util.*;
public class VideoGame implements Comparable<VideoGame> {
    private String title;
    private int playerCount;
    private int releaseYear;
    private List<String> platforms;
    // Behavior:
    //   - This method constructs a VideoGame instance with the given title, player count, 
    //     release year, and supported platforms. Throws an IllegalArgumentException if
    //     title is null, player count is less than 0, release year is less than 0, or 
    //     the given platforms is empty.
    // Parameters:
    //   - title: String representing the title of the VideoGame.
    //   - playerCount: int representing the player count of the VideoGame.
    //   - releaseYear: int representing the year the VideoGame was released.
    //   - platforms: List of Strings representing the platforms the VideoGame is supported on.
    // Returns:
    // Exceptions:
    //   - Throw an IllegalArgumentException if:
    //          title is null
    //          player count is less than 0
    //          release year is less than 0
    //          list of platforms is empty
    public VideoGame(String title, int playerCount, int releaseYear, List<String> platforms) {
        if (title == null || playerCount < 0 || releaseYear < 0 || platforms.isEmpty()) {
            throw new IllegalArgumentException("Invalid title, player count, release year,"
                                                +" or platforms.");
        }
        this.title = title;
        this.playerCount = playerCount;
        this.releaseYear = releaseYear;
        this.platforms = new ArrayList<>(platforms);
    }

    // Behavior:
    //   - This method returns a list of the support platforms for the VideoGame.
    // Parameters:
    // Returns:
    //   - a list of the support platforms for the VideoGame.
    // Exceptions:
    public List<String> getPlatforms() {
        return new ArrayList<>(platforms);
    }

    // Behavior:
    //   - This method returns a String containing the title, player count, release year, and 
    //     supported platforms of the VideoGame into file format.
    //     File Format:                           File Format Example:
    //             Title                   ->                    Overwatch
    //             Player Count            ->                    2394000
    //             Release Year            ->                    2014
    //             Supported Platforms     ->                    PC PS4 Xbox Switch
    // Parameters:
    // Returns:
    //   - Returns String of the VideoGame's information in file format.
    // Exceptions:
    public String toFileString() {
        String platformsLine = "";
        for (int i = 0; i < this.platforms.size(); i++) {
            platformsLine += this.platforms.get(i);
            if (i < platforms.size() - 1) {
                platformsLine += " ";
            }
        }
        String format = title + "\n" + playerCount + "\n" + releaseYear + "\n" + platformsLine;
        return format;
    }

    // Behavior:
    //   - This method returns a String representation of the VideoGame, including it's title, 
    //     player count, release year, and supported platforms.
    //    Representation: 
    //         Video Game: title | Player Count: 000 | Release Year: 2000 | Platforms: [PC]
    //
    // Parameters:
    // Returns:
    //   - Returns String represemtation of the VideoGame.
    // Exceptions:
    @Override
    public String toString() {
        String format = "Video Game: " + this.title + " | Player Count: " + this.playerCount 
        + " | Release Year: " + this.releaseYear + " | Platforms: " + platforms.toString();
        return format;
    }
    
    // Behavior:
    //   - This method compares given object to the VideoGame, returning true if they are equal and 
    //     false if not. Compares title, player count, release year, and supported platforms, if not 
    //     all characteristics are the same, returns false.
    // Parameters:
    //   - o: Object being compared with the VideoGame.
    // Returns:
    //   - Returns boolean:
    //          True: given object has same characteristics as the VideoGame.
    //          False: given object does not have same characteristics as the VideoGame.
    // Exceptions:
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof VideoGame) {
            VideoGame otherGame = (VideoGame) o;
            return this.title.equals(otherGame.title)
                    && this.playerCount == otherGame.playerCount
                    && this.releaseYear == otherGame.releaseYear
                    && this.platforms.equals(otherGame.platforms);
                   
        } else {
            return false;
        }
    }

    // Behavior:
    //   - This method generates and returns a unique hash code for each object, using a hash code 
    //     algorithm. The hash code is consistent with equality, so VideoGames that are equal will
    //     have the same hash code.
    // Parameters:
    // Returns:
    //   - Returns int representing the VideoGame's generated hash code.
    // Exceptions:
    @Override
    public int hashCode() {
        return 31 * this.title.hashCode()
         + 31 * (this.playerCount 
         + 31 * (this.releaseYear
         + 31 * this.platforms.hashCode()));
    }

    // Behavior:
    //   - This method prompts the user for the title, player count, release year, and supported 
    //     platforms for a VideoGame, and then constructs and returns a new VideoGame with the 
    //     given attributes.
    // Parameters:
    //   - scan: Scanner that allows user input to be read.
    // Returns:
    //   - Returns a VideoGame instance with the user given attributes.
    // Exceptions:
    public static VideoGame parse(Scanner scan) {
        System.out.print("What is the title of your video game? ");
        String title = scan.nextLine();

        System.out.print("What is the current player count of your video game? ");
        int playerCount = Integer.parseInt(scan.nextLine());

        System.out.print("What is the year your video game released? ");
        int releaseYear = Integer.parseInt(scan.nextLine());

        System.out.print("How many platforms does your video game support? ");
        int platformCount = Integer.parseInt(scan.nextLine());
        List<String> platforms = new ArrayList<>();
        for (int i = 0; i < platformCount; i++) {
            System.out.print("Enter platform #" + (i + 1) + ": ");
            platforms.add(scan.nextLine());
        }
        return new VideoGame(title, playerCount, releaseYear, platforms);
    }

    // Behavior:
    //   - This method constructs a VideoGame using formated data from a file. If any of the 
    //     data is not 4 lines, null is returned.
    //     File Format:                           File Format Example:
    //             Title                   ->                    Overwatch
    //             Player Count            ->                    2394000
    //             Release Year            ->                    2014
    //             Supported Platforms     ->                    PC PS4 Xbox Switch
    // Parameters:
    //   - scan: Scanner that allows file data to be read.
    // Returns:
    //   - Returns a VideoGame instance with the file data.
    //   - Returns null if there wasn't 4 lines in the file to be scanned.
    // Exceptions:
    public static VideoGame parseFile(Scanner scan) {
        if(!scan.hasNextLine()) {
            return null;
        }
        String title = scan.nextLine();

        if(!scan.hasNextLine()) {
            return null;
        }
        int playerCount = Integer.parseInt(scan.nextLine());

        if(!scan.hasNextLine()) {
            return null;
        }
        int releaseYear = Integer.parseInt(scan.nextLine());

        if(!scan.hasNextLine()) {
            return null;
        }
        String platformLine = scan.nextLine();
        
        Scanner platformScanner = new Scanner(platformLine);
        List<String> platforms = new ArrayList<>();
        while (platformScanner.hasNext()) {
            platforms.add(platformScanner.next());
        }
        return new VideoGame(title, playerCount, releaseYear, platforms);
    }

    // Behavior:
    //   - This method uses an algorithm to compare this VideoGame to another in order to be 
    //     sorted. The comparison uses an ordering based on each VideoGames title, player count
    //     , release year, and supported platforms.
    //     Ordering:
    //     1. Title(alphabetically, ascending)
    //     2. If titles are equal, player count(descending)
    //     3. If player count is equal, release year(descending)
    //     4. If release years are equal, number of platforms(descending)
    // Parameters:
    //   - other: VideoGame to compare to.
    // Returns:
    //   - Returns and integer that is:
    //          negative if this VideoGame is less than the other one.
    //          zero if this VideoGame is equal to the other one.
    //          positive if this VideoGame is greater than the other one.
    // Exceptions:
    public int compareTo(VideoGame other) {
        if (!this.title.equals(other.title)) {
            return this.title.compareTo(other.title);
        } else if (this.playerCount != other.playerCount) {
            return Integer.compare(other.playerCount, this.playerCount);
        } else if (this.releaseYear != other.releaseYear) {
            return Integer.compare(other.releaseYear, this.releaseYear);
        } else {
            return Integer.compare(other.platforms.size(), this.platforms.size());
        }
    }
}