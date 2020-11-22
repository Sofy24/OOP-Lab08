package it.unibo.oop.lab.advanced;

public final class Configuration {

    private final int max;
    private final int min;
    private final int attemps;

    private Configuration(final int max, final int min, final int attemps) {
        this.max = max;
        this.min = min;
        this.attemps = attemps;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getAttempts() {
        return attemps;
    }

    public boolean isConsistent() {
        return attemps > 0 && min < max; 
    }

    public static class Builder {

        private static final int MIN = 0;
        private static final int MAX = 100;
        private static final int ATTEMPTS = 10;

        private int min = MIN;
        private int max = MAX;
        private int attempts = ATTEMPTS;
        private boolean consumed = false;

        public Builder setMin(final int min) {
            this.min = min;
            return this;
        }

        public Builder setMax(final int max) {
            this.max = max;
            return this;
        }

        public Builder setAttempts(final int attempts) {
            this.attempts = attempts;
            return this;
        }

        public final Configuration build() {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;
            return new Configuration(max, min, attempts);
        }
    }
}
