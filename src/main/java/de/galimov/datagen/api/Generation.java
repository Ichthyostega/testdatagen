package de.galimov.datagen.api;

import de.galimov.datagen.basic.ConstantGenerator;
import de.galimov.datagen.recording.OngoingRecordingHolder;
import de.galimov.datagen.recording.RecordingHelperUtils;

import static de.galimov.datagen.recording.RecordingProxyCreator.createRecordingProxy;

public class Generation {
    @SuppressWarnings("unchecked")
    public static <T> T on(DataGenerator<T> generator) {
        OngoingRecordingHolder.endRecordingForGeneratorIfItIsCurrent(generator);
        OngoingRecordingHolder.setCurrentGenerator(generator);
        if(generator.getGeneratedClass() == null) {
            throw new IllegalStateException("If you use dynamic recording on generators, then you have to set their generated class using " +
                    "the DataGenerator.setGeneratedClass(...) method");
        }
        return (T) createRecordingProxy(generator.getGeneratedClass());
    }

    @SuppressWarnings("unchecked")
    public static <T> T generated(DataGenerator<T> generator) {
        return RecordingHelperUtils.generated(generator);
    }

    public static int value(int value) {
        return generated(new ConstantGenerator<Integer>(Integer.class, value));
    }

    public static long value(long value) {
        return generated(new ConstantGenerator<Long>(Long.class, value));
    }

    public static double value(double value) {
        return generated(new ConstantGenerator<Double>(Double.class, value));
    }

    public static float value(float value) {
        return generated(new ConstantGenerator<Float>(Float.class, value));
    }

    public static boolean value(boolean value) {
        return generated(new ConstantGenerator<Boolean>(Boolean.class, value));
    }

    public static short value(short value) {
        return generated(new ConstantGenerator<Short>(Short.class, value));
    }

    public static byte value(byte value) {
        return generated(new ConstantGenerator<Byte>(Byte.class, value));
    }

    public static char value(char value) {
        return generated(new ConstantGenerator<Character>(Character.class, value));
    }

    public static <T> T value(T value) {
        return generated(new ConstantGenerator<T>(Object.class, value));
    }

    public static <T> ConstantGenerator<T> constant(T value) {
        return new ConstantGenerator<T>(Object.class, value);
    }

    public static ConstantGenerator<Integer> constant(int value) {
        return new ConstantGenerator<Integer>(Integer.class, value);
    }

    public static ConstantGenerator<Long> constant(long value) {
        return new ConstantGenerator<Long>(Long.class, value);
    }

    public static ConstantGenerator<Double> constant(double value) {
        return new ConstantGenerator<Double>(Double.class, value);
    }

    public static ConstantGenerator<Float> constant(float value) {
        return new ConstantGenerator<Float>(Float.class, value);
    }

    public static ConstantGenerator<Boolean> constant(boolean value) {
        return new ConstantGenerator<Boolean>(Boolean.class, value);
    }

    public static ConstantGenerator<Short> constant(short value) {
        return new ConstantGenerator<Short>(Short.class, value);
    }

    public static ConstantGenerator<Byte> constant(byte value) {
        return new ConstantGenerator<Byte>(Byte.class, value);
    }

    public static ConstantGenerator<Character> constant(char value) {
        return new ConstantGenerator<Character>(Character.class, value);
    }
}
