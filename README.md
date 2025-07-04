# Video Game Manager
This project is a console-based video game collection manager that allows users to build, manage, and explore a personalized library of video games. Built in Java, the system supports user interaction via text prompts, file input/output, and structured data organization using a binary search tree.

The application lets users add games manually or load from a file, search and filter games, and save their collection in a custom format. A full JUnit test suite ensures correctness and stability of all key features.

## Features
- Load games from a file in structured format
- Add new video games via interactive console prompts
- Check if a specific game exists in the collection
- Save the entire collection to a file for later use
- Display all games in sorted order (by title, player count, release year, and platform count)
- Filter games based on supported platform (e.g., PC, Switch)
- Robust error handling for invalid input
- Full JUnit test suite for public methods

## Algorithms and Data Structures Used
### Binary Search Tree (BST):
- Custom CollectionManager class uses a BST to store and organize VideoGame objects.

### Games are automatically sorted using natural ordering:

- Title (alphabetical, ascending)

- Player count (descending)

- Release year (descending)

- Number of platforms (descending)

### Recursion:
- Recursive methods for adding games, checking membership, printing collection, filtering, and saving to file.

