package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public final class PrintStreamView implements DrawNumberView{

    private final PrintStream out;
    
    public PrintStreamView(PrintStream stream) {
        this.out = stream;
    }
    
    public PrintStreamView(final String path) throws FileNotFoundException {
        out = new PrintStream(new FileOutputStream(new File(path)));
    }
    
    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        
    }

    @Override
    public void start() {
        
    }

    @Override
    public void numberIncorrect() {
        out.println("You must enter a number");
        
    }

    @Override
    public void result(DrawResult res) {
        out.println(res.getDescription());
        
    }

    @Override
    public void displayError(String message) {
        out.println("[ERROR]: " + message);
        
    }

}
