package id.sandalov.neural.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Layer implements Iterable<Neuron>{
    protected Neuron[] neurons;
    protected Bias bias;

    public Neuron[] getNeurons() {
        return neurons;
    }

    public Bias getBias() {
        return bias;
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
    private Layer nextLayer;

    public Layer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    public InputLayer(int inAmt) {
        neurons = new InputNeuron[inAmt];
        for (int i = 0; i < inAmt; ++i) {
            neurons[i] = new InputNeuron();
        }
        //TODO: узнать, почему выше не работает
        /*for(Neuron n : neurons) {
            n = new InputNeuron();
        }*/
        bias = new Bias();
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
        setInputs(sample);
    }

    public void setInputs(Sample sample) {
        if (sample.size() != neurons.length) {
            throw new IllegalArgumentException("inAmt and sample length not match");
            //System.exit(-2);
        }
        Iterator<Double> it = sample.iterator(); //а может while it.hasNext() ?
        for (Neuron n : neurons) {
            n.setInput(it.next());
        }
    }
}

class HiddenLayer extends Layer {

}

class OutputLayer extends Layer {
    //OutputNeuron[] outputNeurons;
    private Layer previousLayer;

    public OutputLayer(int outAmt, Layer previousLayer) {
        this.previousLayer = previousLayer;
        neurons = new OutputNeuron[outAmt];
        /*for (Neuron n : outputNeurons) {
            n = new OutputNeuron(this.previousLayer.size());
        }*/
        for (int i = 0; i < outAmt; ++i) {
            neurons[i] = new OutputNeuron(this.previousLayer.size());
        }
    }

    private int maxActiveOutput() {
        int index = 0;
        double max = neurons[0].getOutput();
        for (int i = 0; i < neurons.length; ++i) {
            if (neurons[i].getOutput() > max) {
                max = neurons[i].getOutput();
                index = i;
            }
        }

        int number = index == 9 ? 0 : index + 1;
        return number;
    }

    public void accumDelta(double learningRate, Sample currentSample) {
        int numConnections = previousLayer.size();
    }
}