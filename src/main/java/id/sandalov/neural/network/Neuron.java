package id.sandalov.neural.network;

import java.util.Random;

public abstract class Neuron {
    private double input;
    private double output;

    public void setOutput(double output) {
        this.output = output;
    }

    public double getOutput() {
        return output;
    }

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public static double Sigmoid(double x) {
        return 1.0 / (1 + Math.exp(-x));
    }
}

abstract class WeightedNeuron extends Neuron {
    double[] weights;

    public double[] getWeight() {
        return weights;
    }

    public void setWeight(double[] weights) {
        this.weights = weights;
    }
}

class InputNeuron extends Neuron {
    public InputNeuron() {

    }

    @Override
    public void setInput(double input) {
        super.setInput(input);
        super.setOutput(input);
    }
}

class OutputNeuron extends WeightedNeuron {
    private final Random random = new Random(47);

    public OutputNeuron(int numConnections) {
        weights = new double[numConnections];
        for (int i = 0; i < weights.length; ++i) {
            weights[i] = random.nextGaussian();
        }
    }
    @Override
    public void setInput(double input) {
        setInput(input);
        setOutput(Sigmoid(input)); //sum
    }
}

class HiddenNeuron extends Neuron {

}

class Bias extends Neuron {
    public Bias() {
        setInput(1);
        setOutput(1);
    }
}