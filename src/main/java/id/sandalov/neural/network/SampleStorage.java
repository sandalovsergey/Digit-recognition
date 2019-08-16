package id.sandalov.neural.network;

import id.sandalov.neural.network.OldSample;
import id.sandalov.neural.network.Sample;

import java.util.ArrayList;


public class SampleStorage {
    private String storagePath;
    private ArrayList<Sample> sampleContainer = new ArrayList<Sample>();
    private static final int SAMPLE_AMOUNT = 1;
    private static final String EXTENSION = ".sample";

    public SampleStorage(String storagePath) {
        this.storagePath = storagePath;

        for(int i = 0; i < SAMPLE_AMOUNT; ++i) {
            String samplePath = "/" + i + "_" + 1 + EXTENSION;
            Sample sample = new OldSample(storagePath + samplePath);
            sampleContainer.add(sample);
        }
    }

    public Sample getSampleByNumber(int number) {
        return sampleContainer.get(number);
    }
}
