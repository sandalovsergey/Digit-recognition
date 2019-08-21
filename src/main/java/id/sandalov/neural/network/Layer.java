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
        return neurons.length + 1;
    }

    @Override
    public Iterator<Neuron> iterator() {
        return new Iterator<Neuron>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < neurons.length + 1;
            }

            @Override
            public Neuron next() {
                if (i < neurons.length) {
                    return neurons[i++];
                } else {
                    ++i;
                    return bias;
                }
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

    @Override
    public int size() {
        return neurons.length;
    }

    @Override
    public Iterator<Neuron> iterator() {
        return new Iterator<Neuron>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size();
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

    public int maxActiveOutput() {
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

    private int[] initIdeals(int mark) {
        int[] ideals = new int[this.size()];
        for (int i = 0; i < ideals.length; ++i) {
            ideals[i] = 0;
        }

        if (mark == 0) {
            ideals[9] = 1;
        } else {
            ideals[mark - 1] = 1; //Не логичнее ли переделать на 0 1 2 3 4 5 6 7 8 9 порядок?
        }

        return ideals;
    }

    public void accumNeuronDeltas(double learningRate, Sample currentSample) {
        if (currentSample instanceof OldSampleMarked) {
            OldSampleMarked sample = (OldSampleMarked) currentSample;
            int mark = sample.getMark(); // 1 2 3 4 5 6 7 8 9 0 <--- order
            int[] ideals = initIdeals(mark);
            Iterator<Neuron> outIter = this.iterator();
            int cntOut = 0;
            while (outIter.hasNext()) {
                OutputNeuron outNeuron = (OutputNeuron) outIter.next();
                int ideal = ideals[cntOut++];
                Iterator<Neuron> inIter = previousLayer.iterator();
                double[] deltas = new double[previousLayer.size()];
                int cntIn = 0;
                while (inIter.hasNext()) {
                    Neuron inNeuron = inIter.next();
                    deltas[cntIn++] = learningRate * inNeuron.getOutput() * (ideal - outNeuron.getOutput());
                }
                outNeuron.addDeltas(deltas);
            }
        }
    }

    public void meanNeuronDeltas(int storageSize) {
        Iterator<Neuron> it = this.iterator();
        while (it.hasNext()) {
            OutputNeuron n = (OutputNeuron) it.next();
            n.meanDeltas(storageSize);
        }
    }

    public void correctNeuronWeights() {
        Iterator<Neuron> it = this.iterator();
        while (it.hasNext()) {
            OutputNeuron n = (OutputNeuron) it.next();
            n.correctWeights();
        }
    }
}