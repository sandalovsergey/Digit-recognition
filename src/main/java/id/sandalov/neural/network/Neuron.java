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

    public double[] getWeights() {
        return weights;
    }

    public double[] getDeltas() {
        return deltas;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public void setDeltas(double[] deltas) {
        this.deltas = deltas;
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

    public void addDeltas(double[] deltas) {
        if (deltas.length != this.deltas.length) {
            System.out.println("Different deltas len");
            System.exit(-1);
        }

        for (int i = 0; i < deltas.length; ++i) {
            this.deltas[i] += deltas[i];
        }
    }

    public void meanDeltas(int storageSize) {
        for (int i = 0; i < deltas.length; ++i) {
            deltas[i] /= storageSize; //Почему не работает через foreach???
        }
    }

    public void correctWeights() {
        for (int i = 0; i < weights.length; ++i) {
            weights[i] += deltas[i];
        }
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