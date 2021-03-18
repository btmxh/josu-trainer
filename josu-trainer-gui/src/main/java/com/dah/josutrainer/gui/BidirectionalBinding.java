package com.dah.josutrainer.gui;

import java.util.function.Function;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;

// From:
// https://stackoverflow.com/questions/60341778/bindbidirectional-with-function
public class BidirectionalBinding<T, U> {

    private final Property<T> source;
    private final Property<U> target;

    private final ChangeListener<? super T> sourceListener;
    private final ChangeListener<? super U> targetListener;
    private boolean changing = false;

    public BidirectionalBinding(Property<T> source, Property<U> target, Function<T, U> mapping,
            Function<U, T> inverseMapping) {
        this.source = source;
        this.target = target;

        target.setValue(mapping.apply(source.getValue()));

        sourceListener = (obs, oldSourceValue, newSourceValue) -> {
            if (!changing) {
                changing = true;
                target.setValue(mapping.apply(newSourceValue));
            }
            changing = false;
        };
        source.addListener(sourceListener);

        targetListener = (obs, oldTargetValue, newTargetValue) -> {
            if (!changing) {
                changing = true;
                source.setValue(inverseMapping.apply(newTargetValue));
            }
            changing = false;
        };
        target.addListener(targetListener);

    }

    public void unbind() {
        source.removeListener(sourceListener);
        target.removeListener(targetListener);
    }
}