package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    //private static final int MIN = 0;
    //private static final int MAX = 100;
    //private static final int ATTEMPTS = 10;
    private final DrawNumber model;
    private final DrawNumberView view;
    //final BufferedReader r = new BufferedReader(new InputStreamReader(new )) 

    /**
     * 
     */
    public DrawNumberApp(final String configFile, final DrawNumberView... views) {
        this.view = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view : views) {
            this.view.setObserver(this);
            this.view.start();
        }
        final Configuration.Builder configurationBuilder = new Configuration.Builder();
        try (var contents = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(configFile)))) {
            for (var configLine = contents.readLine(); configLine != null; configLine = contents.readLine() ) {
                final String[] lineElements = configLine.split(":");
                if(lineElements.length == 2) {
                    final int value = Integer.parseInt(lineElements[1].trim());
                    if (lineElements[0].contains("max")) {
                        configurationBuilder.setMax(value);
                    } else if (lineElements[0].contains("min")) {
                        configurationBuilder.setMin(value);
                    } else if (lineElements[0].contains("attempts")) {
                        configurationBuilder.setAttempts(value);
                    }
                } else {
                    displayError("I cannot understand \"" + configLine + '"');
                }
            }
        } catch (IOException | NumberFormatException e) {
            displayError(e.getMessage());
        }
        final Configuration configuration = configurationBuilder.build();
        if (configuration.isConsistent()) {
            this.model = new DrawNumberImpl(configuration);
        } else {
            displayError("Inconsistent configuration: "
                    + "min: " + configuration.getMin() + ", "
                    + "max: " + configuration.getMax() + ", "
                    + "attempts: " + configuration.getAttempts() + ". Using defaults instead.");
            this.model = new DrawNumberImpl(new Configuration.Builder().build());
        }
    }

    private void displayError(final String err) {
        for (final DrawNumberView view : views) {
            view.displayError(err);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for(final DrawNumberView view : views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp("config.yml", new DrawNumberViewImpl(), new DrawNumberViewImpl(), new PrintStreamView(System.out), new PrintStreamView("output.log"));
    }

}
