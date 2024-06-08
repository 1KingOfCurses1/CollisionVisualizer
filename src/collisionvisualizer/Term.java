/*
 *
 * 
 */
package collisionvisualizer;

public class Term implements Cloneable {

    private String name;
    private String definition;
    
    public Term() {
        name = "";
        definition = "";
    }
    
    public Term(String name, String definition) {
        this();
        this.name = name;
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }


    public boolean equals(Term t) {
        return name.equals(t.name) && definition.equals(t.definition);
    }

    public Term clone() {
        Term t = new Term(name, definition);
        return t;
    }

    public String toString() {
        return "Term: " + name + "\n Definition: " + definition;
    }

}