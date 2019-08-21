package id.sandalov.neural.network;

import java.io.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class NeuralNetwork implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int inAmt;
    private final int outAmt;
    private final int hiddenLayersAmt;
    private double learningRate;
    private final int maxEpoch;
    private int curEpoch = 1;
    private InputLayer inLayer;
    private OutputLayer outLayer;
    //HiddenLayer[] hiddenLayers;
    private transient SampleStorage sampleStorage;
    public transient static final Random random = new Random(47);


    public NeuralNetwork(int inAmt, int outAmt, int hiddenLayersAmt,
                         double learningRate, int maxEpoch, String storagePath) {
        this.inAmt = inAmt;
        this.outAmt = outAmt;
        this.hiddenLayersAmt = hiddenLayersAmt;
        this.learningRate = learningRate;
        this.maxEpoch = maxEpoch;
        this.sampleStorage = new SampleStorage(storagePath);
        this.inLayer = new InputLayer(this.inAmt);

        this.outLayer = new OutputLayer(this.outAmt, this.inLayer);
        //this.hiddenLayers = new HiddenLayer[this.hiddenLayersAmt];
        this.inLayer.setNextLayer(outLayer);
    }

    private boolean needLearning() {
        return curEpoch++ > maxEpoch ? false : true;
    }

    private void backwardPropagation(Sample currentSample) {
        outLayer.accumNeuronDeltas(learningRate, currentSample);
        outLayer.meanNeuronDeltas(this.sampleStorage.size());
        outLayer.correctNeuronWeights();
    }

    private double calculateInput(Iterator<Neuron> inIter, double[] weights) {
        double sum = 0.0;
        for (double w : weights) {
            sum += w * inIter.next().getOutput();
        }
        //sum += inLayer.getBias().getOutput();

        return sum;
    }

    private void forwardPropagation(Sample currentSample) {
        inLayer.setInputs(currentSample);
        Iterator<Neuron> outIter = outLayer.iterator();
        while (outIter.hasNext()) {
            OutputNeuron n = (OutputNeuron) outIter.next();
            double[] weights = n.getWeights();
            Iterator<Neuron> inIter = inLayer.iterator();
            double sum = calculateInput(inIter, weights);
            n.setInput(sum);
        }
    }

    private void oneEpochLearning() {
        Iterator<Sample> sampleIter = sampleStorage.iterator();
        while (sampleIter.hasNext()) {
            Sample currentSample = sampleIter.next();
            forwardPropagation(currentSample);
            backwardPropagation(currentSample);
        }
    }

    public void learning() {
        while (needLearning()) {
            oneEpochLearning();
        }
    }

    private int guessFromSample(Sample sample) {
        int mark = -1;
        forwardPropagation(sample);
        mark = this.outLayer.maxActiveOutput();
        return mark;
    }

    public int guessingTestSample(String filename) {
        Sample testSample = new OldSample(filename);
        return guessFromSample(testSample);
    }

    public int guessingInput() {
        Scanner scannerIn = new Scanner(System.in);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("src/main/tests/input.sample"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; ++i) {
            try {
                String line = scannerIn.nextLine();
                out.write(line);
                out.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return guessingTestSample("src/main/tests/input.sample");
    }

    public void save(String path) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NeuralNetwork load(String filename) {
        NeuralNetwork neuralNetwork = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            neuralNetwork = (NeuralNetwork) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return neuralNetwork;
    }
}
