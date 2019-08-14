package id.sandalov.neural.network;

public class NeuralNetwork {
    private final int inAmt;
    private final int outAmt;
    private final int hiddenLayersAmt;
    private double learningRate;
    private final int maxEpoch;
    private int curEpoch = 1;
    InputLayer inLayer;
    OutputLayer outLayer;
    HiddenLayer[] hiddenLayers;

    public NeuralNetwork(int inAmt, int outAmt, int hiddenLayersAmt, double learningRate, int maxEpoch) {
        this.inAmt = inAmt;
        this.outAmt = outAmt;
        this.hiddenLayersAmt = hiddenLayersAmt;
        this.learningRate = learningRate;
        this.maxEpoch = maxEpoch;
        this.inLayer = new InputLayer(this.inAmt, "");
        this.outLayer = new OutputLayer(this.outAmt);
        //this.hiddenLayers = new HiddenLayer[this.hiddenLayersAmt];
    }
}
