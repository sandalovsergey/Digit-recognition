package id.sandalov.neural.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Layer implements Iterable<Neuron>{
    Neuron[] neurons;

    public Neuron[] getNeurons() {
        return neurons;
    }

    public int size() {
        return neurons.length;
    }

    @Override
    public Iterator<Neuron> iterator() {
        return new Iterator<Neuron>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < neurons.length;
            }

            @Override
            public Neuron next() {
                return neurons[i++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

class InputLayer extends Layer {
    Layer nextLayer;

    private InputLayer(int inAmt) {
        neurons = new InputNeuron[inAmt + 1];
        for(Neuron n : neurons) {
            n = new InputNeuron();
        }
        neurons[inAmt] = new Bias();
    }

    public InputLayer(int inAmt, String filename) {
        this(inAmt);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: cant read input");
            e.printStackTrace();
            System.exit(-1);
        }

        Sample sample = new OldSampleMarked(filename);
        if (sample.size() != neurons.length - 1) {
            throw new IllegalArgumentException("inAmt and sample length not match");
            //System.exit(-2);
        }
        Iterator<Double> it = sample.iterator();
        for (Neuron n : neurons) {
            n.setInput(it.next());
        }
    }

    public InputLayer(int inAmt, Sample sample) {
        this(inAmt);
        if (sample.size() != neurons.length - 1) {
            throw new IllegalArgumentException("inAmt and sample length not match");
            //System.exit(-2);
        }
        Iterator<Double> it = sample.iterator();
        for (Neuron n : neurons) {
            n.setInput(it.next());
        }
    }
}

class HiddenLayer extends Layer {

}

class OutputLayer extends Layer {
    OutputNeuron[] outputNeurons;
    Layer previousLayer;

    public OutputLayer(int outAmt, Layer previousLayer) {
        this.previousLayer = previousLayer;
        outputNeurons = new OutputNeuron[outAmt];
        for (Neuron n : outputNeurons) {
            n = new OutputNeuron(this.previousLayer.size());
        }
    }

    private int maxActiveOutput() {
        int index = 0;
        double max = outputNeurons[0].getOutput();
        for (int i = 0; i < outputNeurons.length; ++i) {
            if (outputNeurons[i].getOutput() > max) {
                max = outputNeurons[i].getOutput();
                index = i;
            }
        }

        int number = index == 9 ? 0 : index + 1;
        return number;
    }

    public void weightsCorrection(double learningRate) {
        int numConnections = previousLayer.size();


    }
}