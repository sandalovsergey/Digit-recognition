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

    public void setConnection(Layer layer) {

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
    private Neuron[] rightNeurons;

    public InputNeuron(int numConnections) {
        rightNeurons = new Neuron[numConnections];
    }

    public Neuron[] getRightNeurons() {
        return rightNeurons;
    }

    @Override
    public void setInput(double input) {
        super.setInput(input);
        super.setOutput(input);
    }

    @Override
    public void setConnection(Layer outLayer) {
        for (int i = 0; i < rightNeurons.length; ++i) {
            //rightNeurons[i] = arr[i];
        }
    }
}

class OutputNeuron extends WeightedNeuron {
    private Neuron[] leftNeurons;
    private final Random random = new Random(47);

    public OutputNeuron(int numConnections) {
        leftNeurons = new Neuron[numConnections];
        weights = new double[numConnections];
        for (int i = 0; i < weights.length; ++i) {
            weights[i] = random.nextGaussian();
        }
    }

    public Neuron[] getLeftNeurons() {
        return leftNeurons;
    }

    @Override
    public void setInput(double input) {
        super.setInput(input);
        super.setOutput(Sigmoid(input));
    }

    @Override
    public void setConnection(Layer layer) {
        for (int i = 0; i < leftNeurons.length; ++i) {
            //leftNeurons[i] = arr[i];
        }
    }
}

class HiddenNeuron extends Neuron {

}

class Bias {

}