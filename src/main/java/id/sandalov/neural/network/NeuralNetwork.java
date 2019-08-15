package id.sandalov.neural.network;

import java.util.Iterator;

public class NeuralNetwork {
    private final int inAmt;
    private final int outAmt;
    private final int hiddenLayersAmt;
    private double learningRate;
    private final int maxEpoch;
    private int curEpoch = 1;
    InputLayer inLayer;
    OutputLayer outLayer;
    //HiddenLayer[] hiddenLayers;

    public NeuralNetwork(int inAmt, int outAmt, int hiddenLayersAmt, double learningRate, int maxEpoch) {
        this.inAmt = inAmt;
        this.outAmt = outAmt;
        this.hiddenLayersAmt = hiddenLayersAmt;
        this.learningRate = learningRate;
        this.maxEpoch = maxEpoch;
        this.inLayer = new InputLayer(this.inAmt, "E:\\JetBrainsAcademy\\" +
                "Projects\\Digit-recognition\\src\\main\\resources\\9_1.sample");

        this.outLayer = new OutputLayer(this.outAmt, this.inLayer);
        //this.hiddenLayers = new HiddenLayer[this.hiddenLayersAmt];
        this.inLayer.nextLayer = outLayer;
    }

    private boolean needLearning() {
        return true;
    }



    public void learning() {
        while (needLearning()) {
            Iterator<Neuron> it = outLayer.iterator();
            while (it.hasNext()) {
                OutputNeuron n = (OutputNeuron) it.next();
                double[] weights = n.getWeight();
                Iterator<Neuron> itPrev = inLayer.iterator();
                double sum = 0.0;
                for (double w : weights) {
                    sum += w * itPrev.next().getOutput();
                }
                n.setInput(sum);

                outLayer.weightsCorrection();
            }
        }
    }
}
