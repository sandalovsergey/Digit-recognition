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

   /* private Neuron[] inputNeurons;
    private Neuron[] outputNeurons;*/

    public NeuralNetwork(int inAmt, int outAmt, int hiddenLayersAmt, double learningRate, int maxEpoch) {
        this.inAmt = inAmt;
        this.outAmt = outAmt;
        this.hiddenLayersAmt = hiddenLayersAmt;
        this.learningRate = learningRate;
        this.maxEpoch = maxEpoch;
        this.inLayer = new InputLayer();
        this.outLayer = new OutputLayer();
        HiddenLayer[] hiddenLayers = new HiddenLayer[this.hiddenLayersAmt];

        /*inputNeurons = new Neuron[INPUT_NEURON_NUMBERS];
        outputNeurons = new Neuron[OUTPUT_NEURON_NUMBERS];

        for (int i = 0; i < INPUT_NEURON_NUMBERS; ++i) {
            inputNeurons[i] = new InputNeuron(OUTPUT_NEURON_NUMBERS);
        }

        for (int i = 0; i < OUTPUT_NEURON_NUMBERS; ++i) {
            outputNeurons[i] = new OutputNeuron(INPUT_NEURON_NUMBERS);
        }

        for (Neuron n : inputNeurons) {
            n.setConnection(outputNeurons);
        }

        for (Neuron n : outputNeurons) {
            n.setConnection(inputNeurons);
        }*/
    }
}
