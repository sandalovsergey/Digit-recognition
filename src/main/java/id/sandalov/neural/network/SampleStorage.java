package id.sandalov.neural.network;

import java.util.ArrayList;
import java.util.Iterator;

public class SampleStorage implements Iterable<Sample> {
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

    @Override
    public Iterator<Sample> iterator() {
        return new Iterator<Sample>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < sampleContainer.size();
            }

            @Override
            public Sample next() {
                return sampleContainer.get(i++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public int size() {
        return sampleContainer.size();
    }
}
