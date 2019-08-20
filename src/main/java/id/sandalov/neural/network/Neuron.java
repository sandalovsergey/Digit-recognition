package id.sandalov.neural.network;

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

    double[] deltas;

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

    public OutputNeuron(int numConnections) {
        weights = new double[numConnections];
        deltas = new double[numConnections];
        for (int i = 0; i < weights.length; ++i) {
            weights[i] = NeuralNetwork.random.nextGaussian();
            deltas[i] = 0;
        }
    }
    @Override
    public void setInput(double input) {
        super.setInput(input);
        super.setOutput(Sigmoid(input));
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