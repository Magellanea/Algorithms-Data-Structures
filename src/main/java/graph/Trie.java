package graph;

/**
 * Created by yakoub on 10/07/15.
 */
class TrieNode {
    private TrieNode[] characters;
    private char character;
    //indicates if a word ends at that Node or not
    private boolean isWordEnd;

    public TrieNode() {
        characters = new TrieNode[26];
        isWordEnd = false;
    }

    public TrieNode(char c) {
        this();
        character = c;
    }

    /**
     * Inserts a char at this node at a given char
     * @param c The char
     * @return The node that contains the char.
     * Note here that there's an assumption that the characters
     * are from the range of lowercase alphabet
     */
    public TrieNode insertChar(char c) {
        int idx = (int) (c - 'a');
        if (characters[idx] == null)
            characters[idx] = new TrieNode(character);
        return characters[idx];
    }

    /**
     * Checks if a given node has a subnode with a given char
     * @param c The given char
     * @return if a given node has a subnode with a given char
     */
    public boolean hasNode(char c) {
        int idx = (int) (c - 'a');
        return characters[idx] != null;
    }

    /**
     * Sets the given node as a word end
     */
    public void endWord() {
        isWordEnd = true;
    }

    public boolean isWordEnd() {
        return isWordEnd;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        char[] chars = word.toCharArray();
        TrieNode currentNode = root;
        int cLength = chars.length;
        for (int i = 0; i < cLength; i++)
            currentNode = currentNode.insertChar(chars[i]);
        currentNode.endWord();
    }

    private boolean find(String word, boolean shouldBeWordEnd) {
        char[] chars = word.toCharArray();
        TrieNode currentNode = root;
        int cLength = chars.length;
        for (int i = 0; i < cLength; i++) {
            if (currentNode.hasNode(chars[i]))
                currentNode = currentNode.insertChar(chars[i]);
            else
                return false;
        }
        if (shouldBeWordEnd && !currentNode.isWordEnd())
            return false;
        return true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return find(word, true);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return find(prefix, false);
    }

}
