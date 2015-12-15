package si.um.obu.app.model;

import javax.validation.constraints.Size;

public class Token {

    @Size(min = 1)
    private String value;

    public Token() {
        super();
    }

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                '}';
    }
}
