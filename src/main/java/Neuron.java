import java.util.Random;

public abstract class Neuron {
    private double input;
    private double output;

    public double getInput() {
        return input;
    }

    public double getOutput() {
        return output;
    }

    public void setInput(double input) {
        this.input = input;
    }

    protected void setOutput(double output) {
        this.output = output;
    }

    public static double Sigmoid(double x) {
        return 1.0 / (1 + Math.exp(-x));
    }

    public void setConnection(Neuron[] arr) {

    }
}

class InputNeuron extends Neuron {
    private Neuron[] next;

    public InputNeuron(int numConnections) {
        next = new Neuron[numConnections];
    }

    public Neuron[] getNext() {
        return next;
    }

    @Override
    public void setInput(double input) {
        super.setInput(input);
        super.setOutput(input);
    }

    @Override
    public void setConnection(Neuron[] arr) {
        for (int i = 0; i < next.length; ++i) {
            next[i] = arr[i];
        }
    }
}

class OutputNeuron extends Neuron {
    private Neuron[] prev;
    private double[] weights;
    private final Random random = new Random(47);

    public OutputNeuron(int numConnections) {
        prev = new Neuron[numConnections];
        weights = new double[numConnections];
        for (int i = 0; i < weights.length; ++i) {
            weights[i] = random.nextGaussian();
        }
    }

    public Neuron[] getPrev() {
        return prev;
    }

    @Override
    public void setInput(double input) {
        super.setInput(input);
        super.setOutput(Sigmoid(input));
    }

    @Override
    public void setConnection(Neuron[] arr) {
        for (int i = 0; i < prev.length; ++i) {
            prev[i] = arr[i];
        }
    }
}

class HiddenNeuron extends Neuron {

}

class Bias {

}