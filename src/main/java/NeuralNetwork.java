public class NeuralNetwork {
    public static final int INPUT_NEURON_NUMBERS = 3;
    public static final int OUTPUT_NEURON_NUMBERS = 2;
    public static final double learningRate = 0.5;
    private static int epoch = 1;


    private Neuron[] inputNeurons;
    private Neuron[] outputNeurons;

    public NeuralNetwork() {
        inputNeurons = new Neuron[INPUT_NEURON_NUMBERS];
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
        }
    }
}
