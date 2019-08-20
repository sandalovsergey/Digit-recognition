package id.sandalov.neural.network;

import java.util.Iterator;
import java.util.Random;

public class NeuralNetwork {
    private final int inAmt;
    private final int outAmt;
    private final int hiddenLayersAmt;
    private double learningRate;
    private final int maxEpoch;
    private int curEpoch = 1;
    private InputLayer inLayer;
    private OutputLayer outLayer;
    //HiddenLayer[] hiddenLayers;
    private SampleStorage sampleStorage;
    public static final Random random = new Random(47);


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



    public void learning() {
        while (needLearning()) {

            Iterator<Sample> sampleIter = sampleStorage.iterator();
            while (sampleIter.hasNext()) {
                Sample currentSample = sampleIter.next();
                inLayer.setInputs(currentSample);

                Iterator<Neuron> it = outLayer.iterator();
                while (it.hasNext()) {
                    OutputNeuron n = (OutputNeuron) it.next();
                    double[] weights = n.getWeight();
                    Iterator<Neuron> itPrev = inLayer.iterator();
                    double sum = 0.0;
                    for (double w : weights) {
                        sum += w * itPrev.next().getOutput();
                    }

                    sum += inLayer.getBias().getOutput(); //Вынести в функцию суммирования
                    n.setInput(sum);

                }
                outLayer.accumDelta(learningRate, currentSample);
            }
        }
    }
}
