package id.sandalov.neural.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Layer {
    Neuron[] neurons;

    public Neuron[] getNeurons() {
        return neurons;
    }
}

class InputLayer extends Layer {
    public InputLayer(int inAmt, String filename) {
        neurons = new InputNeuron[inAmt + 1];
        for(Neuron n : neurons) {
            n = new InputNeuron();
        }
        neurons[inAmt] = new Bias();
        initInputLayer(filename);
    }

    private void initInputLayer(String filename) {
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
}

class HiddenLayer extends Layer {

}

class OutputLayer extends Layer {
    OutputNeuron[] outputNeurons;
    public OutputLayer(int outAmt) {
        outputNeurons = new OutputNeuron[outAmt];
    }
}