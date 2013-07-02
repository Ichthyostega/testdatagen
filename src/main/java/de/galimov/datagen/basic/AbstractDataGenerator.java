package de.galimov.datagen.basic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.galimov.datagen.api.DataGenerator;
import de.galimov.datagen.api.GenerationStep;
import de.galimov.datagen.recording.OngoingRecordingHolder;

public abstract class AbstractDataGenerator<T> implements DataGenerator<T> {
    private T value;

    private final List<DataGenerator<?>> childGenerators = new LinkedList<DataGenerator<?>>();
    private final List<GenerationStep<T>> generationSteps = new LinkedList<GenerationStep<T>>();

    private Class<?> generatedClass = Object.class;

    @Override
    public void newGenerationCycle(Set<DataGenerator<?>> generators) {
        if(generators.contains(this)) {
            return;
        }

        value = null;
        generators.add(this);

        for (DataGenerator<?> childGenerator : childGenerators) {
            childGenerator.newGenerationCycle(generators);
        }

        for (GenerationStep<T> generationStep : generationSteps) {
            generationStep.newGenerationCycle(generators);
        }
    }

    @Override
    public T getValue() {
        if (value == null) {
            OngoingRecordingHolder.endRecordingForGeneratorIfItIsCurrent(this);
            value = generateInternal();
            for (GenerationStep<T> generationStep : generationSteps) {
                value = generationStep.apply(value);
            }
        }

        return value;
    }

    @Override
    public T generate() {
        newGenerationCycle(new HashSet<DataGenerator<?>>());
        return getValue();
    }

    @Override
    public Class<?> getGeneratedClass() {
        return generatedClass;
    }

    @Override
    public void setGeneratedClass(Class<?> generatedClass) {
        this.generatedClass = generatedClass;
    }

    @Override
    public void setSeed(long seed) {
    }

    protected void registerChildGenerator(DataGenerator<?> dataGenerator) {
        childGenerators.add(dataGenerator);
    }

    @Override
    public void add(GenerationStep<T> generationStep) {
        generationSteps.add(generationStep);
    }

    protected abstract T generateInternal();
}