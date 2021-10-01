package dbosoft.testApp.intarfaces;

import java.util.List;

public interface Parser<T> {
    public List<T> parse(String nameFile);
}
