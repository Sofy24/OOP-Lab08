package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public final class ControllerImpl implements Controller {
    private String currentStr;
    private final List<String> history = new ArrayList<>();

    /*public ControllerImpl(String currentStr, List<String> history) {
        this.currentStr = currentStr;
        this.history = history;
    }*/

    public void setStr(final String str) {
        if (str != null)  {
            this.currentStr = str;
        } else {
            System.err.println("Stringa null");
            throw new IllegalStateException();
        }
    }


    public String getStr() {
        return currentStr;
    }


    public List<String> getHistory() {

        return this.history;
    }


    public void printStr() {
        if (currentStr != null)  {
            System.out.println(currentStr);
            history.add(currentStr);
        } else {
            System.err.println("Errore nella stringa");
            throw new IllegalStateException();
        }

    }

}
