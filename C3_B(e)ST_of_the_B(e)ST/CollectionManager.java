// Tristan Suwito
// 6 June 2025
// CSE 123
// TA: Rushil Arun & Chris Ma
// C3: B(e)ST of the B(e)ST
// This class represents a manager for a collection of VideoGames. It can be used to 
// add new games, check to see if the collection has a specific game, create a string 
// representation of the collection, save the collection to a file, and filter games based off of
// what platforms they support.
import java.io.*;
import java.util.*;

public class CollectionManager {
    // TODO: Implement your CollectionManager here!
    private GameNode root;

    // Behavior:
    //   - This method constructs an empty CollectionManager with no VideoGames in the collection.
    // Parameters:
    // Returns:
    // Exceptions:
    public CollectionManager() {
        this.root = null;
    }

    // Behavior:
    //   - This method constructs a CollectionManager by taking a list of video games from a file
    //     and adding them to the collection. Files must be in specific format to be read properly.
    //     Throws an IllegalArgumentException if inputted Scanner data is null.
    //     File Format:                           File Format Example:
    //             Title                   ->                    Overwatch
    //             Player Count            ->                    2394000
    //             Release Year            ->                    2014
    //             Supported Platforms     ->                    PC PS4 Xbox Switch
    // Parameters:
    //   - scan: Scanner containing file data to be read.
    // Returns:
    // Exceptions:
    //   - Throws an IllegalArgumentException if inputted Scanner data is null.
    public CollectionManager(Scanner scan) {
        if (scan == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        while (scan.hasNextLine()) {
            VideoGame game = VideoGame.parseFile(scan);
            if (game != null) {
                add(game);
            }
        }
    }


    // Behavior:
    //   - This method adds the given VideoGame to the collection. Throws an 
    //     IllegalArgumentException if given VideoGame is null.
    // Parameters:
    //   - game: the given VideoGame that is to be added to the collection.
    // Returns:
    // Exceptions:
    //   - Throws an IllegalArgumentException if given VideoGame is null.
    public void add(VideoGame game) {
        if (game == null) {
            throw new IllegalArgumentException("VideoGame cannot be null.");
        }
        this.root = add(root, game);
    }

    // Behavior:
    //   - This method recursively puts the given VideoGame into the binary search tree with the
    //     root at a specificed node based on the VideoGame's natural ordering.
    //     Natural Ordering:
    //     1. Title(alphabetically, ascending)
    //     2. If titles are equal, player count(descending)
    //     3. If player count is equal, release year(descending)
    //     4. If release years are equal, number of platforms(descending)
    // Parameters:
    //   - node: the current root of the subtree.
    //   - game: the given VideoGame that is to be added to the collection.
    // Returns:
    //   - Returns the GameNode root of the updated subtree with the new VideoGame inserted.
    // Exceptions:
    private GameNode add(GameNode node, VideoGame game) {
        if (node == null) {
            return new GameNode(game);
        }
        int compareVal = game.compareTo(node.game);
        if (compareVal < 0) {
            node.left = add(node.left, game);
        } else if (compareVal > 0) {
            node.right = add(node.right, game);
        }
        return node;
    }

    // Behavior:
    //   - This method checks if the given VideoGame is in the collection. Throws an 
    //     IllegalArgumentException if the given VideoGame is null.
    // Parameters:
    //   - game: the given VideoGame that is to be checked to see if it is in the collection.
    // Returns:
    //   - Returns boolean:
    //              True: If the given game is in the collection.
    //              False: If the given game is not in the collection. 
    // Exceptions:
    //   - Throws IllegalArgumentException if the given VideoGame is null.
    public boolean contains(VideoGame game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null.");
        }
        return contains(this.root, game);
    }

    // Behavior:
    //   - This method recursively checks if the given VideoGame exists in the subtree rooted at 
    //     the specified node. It uses the natural ordering of VideoGames to traverse the binary 
    //     search tree. Returns a boolean based on if the given VideoGame is gound in the subtree.
    //     Natural Ordering:
    //     1. Title(alphabetically, ascending)
    //     2. If titles are equal, player count(descending)
    //     3. If player count is equal, release year(descending)
    //     4. If release years are equal, number of platforms(descending)
    // Parameters:
    //   - node: the current node in the binary search tree.
    //   - game: the given VideoGame that is to be checked to see if it is in the subtree.
    // Returns:
    //   - Returns boolean:
    //              True: If the given game is found in the subtree.
    //              False: If the given game is not found in the subtree. 
    // Exceptions:
    private boolean contains(GameNode node, VideoGame game) {
        if (node == null) {
            return false;
        }
        int compareVal = game.compareTo(node.game);
        if (compareVal < 0) {
            return contains(node.left, game);
        } else if (compareVal > 0) {
            return contains(node.right, game);
        } else {
            return true;
        }
    }

    // Behavior:
    //   - This method returns a String representation of the collection.
    //     VideoGames appear in the natural ordering.
    //     Representation formatting: 
    //              Video Game: title | Player Count: 000 | Release Year: 2000 | Platforms: [PC]
    //     Natural Ordering:
    //     1. Title(alphabetically, ascending)
    //     2. If titles are equal, player count(descending)
    //     3. If player count is equal, release year(descending)
    //     4. If release years are equal, number of platforms(descending)
    // Parameters:
    // Returns:
    //   - Returns String representation of the collection.
    // Exceptions:
    @Override
    public String toString() {
        return toString(root);
    }

    // Behavior:
    //   - This method recursively creates a String representation of the subtree rooted at the 
    //     given node. The String representation is created using a natural ordering.
    //     Natural Ordering:
    //     1. Title(alphabetically, ascending)
    //     2. If titles are equal, player count(descending)
    //     3. If player count is equal, release year(descending)
    //     4. If release years are equal, number of platforms(descending)
    // Parameters:
    //   - node: the root of the subtree to convert to a String.
    // Returns:
    //   - Returns String representation containing the natural ordering of VideoGames.
    // Exceptions:
    private String toString(GameNode node) {
        if (node == null) {
            return "";
        }
        String format = "";
        format += toString(node.left);
        format += node.game.toString() + "\n";
        format += toString(node.right);
        return format;
    }

    // Behavior:
    //   - This method saves the current collection to the given PrintStream. The VideoGames are
    //     printed to the file in a pre-order traversal and in specific formatting.
    //     File Format:                           File Format Example:
    //             Title                   ->                    Overwatch
    //             Player Count            ->                    2394000
    //             Release Year            ->                    2014
    //             Supported Platforms     ->                    PC PS4 Xbox Switch
    // Parameters:
    //   - output: PrintStream that allows collection to be printed onto a file.
    // Returns:
    // Exceptions:
    //   - Throw an IllegalArgumentException if ouput is null.
    public void save(PrintStream output) {
        if (output == null) {
            throw new IllegalArgumentException("Output cannot be null");
        }
        save(root, output);
    }

    // Behavior:
    //   - This method recursively saves the data of each VideoGame in the tree starting from the
    //     given node and using a pre-order traversal. The VideoGames are printed to the file in a
    //     pre-order traversal and in specific formatting.
    //     File Format:                           File Format Example:
    //             Title                   ->                    Overwatch
    //             Player Count            ->                    2394000
    //             Release Year            ->                    2014
    //             Supported Platforms     ->                    PC PS4 Xbox Switch
    // Parameters:
    //   - node: the current node in the tree to save.
    //   - output: PrintStream that allows collection to be printed onto a file.
    // Returns:
    // Exceptions:
    //   - Throw an IllegalArgumentException if ouput is null.
    private void save(GameNode node, PrintStream output) {
        if (node != null) {
            output.println(node.game.toFileString());
            save(node.left, output);
            save(node.right, output);
        }
    }

    // Behavior:
    //   - This method returns a List of VideoGames in the collection that support a specified
    //     platform.
    // Parameters:
    //   - platform: String of the given platform to filter games by.
    // Returns:
    //   - Returns a List of VideoGames that suppoer the given platform.
    // Exceptions:
    //   - Throw an IllegalArgumentException if the given platform is null.
    public List<VideoGame> filter(String platform) {
        if (platform == null) {
            throw new IllegalArgumentException("Platform cannot be null.");
        }
        List<VideoGame> result = new ArrayList<>();
        filter(root, platform, result);
        return result;
    }
    
    // Behavior:
    //   - This method recursively traverse the tree to using pre-order traversal to find and 
    //     collect all VideoGames that support the specifed platform. For each node, it checks
    //     if the game's list of supported platforms includes the given platform case 
    //     insensitively. If a match is found, the game is added to the result list.
    // Parameters:
    //   - node: The current GameNode in the tree being looked at.
    //   - platform: String of the given platform to filter games by.
    //   - result: the List collecting all the matching VideoGames with the specified platform.
    // Returns:
    // Exceptions:
    private void filter(GameNode node, String platform, List<VideoGame> result) {
        if (node != null) {
            VideoGame game = node.game;
            boolean hasPlatform = false;
            for (String plat : game.getPlatforms()) {
                if (!hasPlatform && plat.equalsIgnoreCase(platform)) {
                    hasPlatform = true;
                }
            }
            if (hasPlatform) {
                result.add(game);
            }
            filter(node.left, platform, result);
            filter(node.right, platform, result);
        }
    }

    // This class represents a node in the binary search tree used by the CollectionManager. Each 
    // node stores a VideoGame and references to its left and right children.
    private static class GameNode {
        public final VideoGame game;
        public GameNode left;
        public GameNode right;

        // Behavior:
        //   - This method constructs a GameNode with the given VideoGame.
        // Parameters:
        //   - game: the given VideoGame to be stored in the GameNode.
        // Returns:
        // Exceptions:
        public GameNode(VideoGame game) {
            this(game, null, null);
        }

        // Behavior:
        //   - This method constructs a GameNode with the given VideoGame and children nodes.
        // Parameters:
        //   - game: the given VideoGame to be stored in the GameNode.
        //   - left: the left child of the GameNode.
        //   - right: the right child of the GameNode.
        // Returns:
        // Exceptions:
        public GameNode(VideoGame game, GameNode left, GameNode right) {
            this.game = game;
            this.left = left;
            this.right = right;
        }
    }
}