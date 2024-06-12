/**
 * Shan Truong, Aryan Verma, Jerry Wu
 * June 11, 2024
 * Term class that holds the term name and definitions
 */
package collisionvisualizer;

public class Term implements Cloneable {

    private String name;        // Name of the term
    private String definition;  // Definition of the term
    /**
     * Default constructor initializes the term with empty name and definition.
     */
    public Term() {
        name = "";
        definition = "";
    }
    /**
     * Parameterized constructor initializes the term with a given name and definition.
     *
     * @param name the name of the term
     * @param definition the definition of the term
     */
    public Term(String name, String definition) {
        this();
        this.name = name;
        this.definition = definition;
    }
    /**
     * Gets the name of the term.
     *
     * @return the name of the term
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the term.
     *
     * @param name the new name of the term
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the definition of the term.
     *
     * @return the definition of the term
     */
    public String getDefinition() {
        return definition;
    }
    /**
     * Sets the definition of the term.
     *
     * @param definition the new definition of the term
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Checks if this term is equal to another term.
     *
     * @param t the term to compare with
     * @return true if the terms are equal, false otherwise
     */
    public boolean equals(Term t) {
        return name.equals(t.name) && definition.equals(t.definition);
    }
    /**
     * Creates and returns a copy (clone) of this term.
     *
     * @return a clone of this term
     */
    public Term clone() {
        Term t = new Term(name, definition);
        return t;
    }
    /**
     * Returns a string representation of the term.
     *
     * @return a string representing the term
     */
    public String toString() {
        return "Term: " + name + "\n Definition: " + definition;
    }

}