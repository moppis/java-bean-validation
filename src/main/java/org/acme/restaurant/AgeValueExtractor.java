package org.acme.restaurant;

import dev.personnummer.Personnummer;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.UnwrapByDefault;
import javax.validation.valueextraction.ValueExtractor;

@UnwrapByDefault
public class AgeValueExtractor implements ValueExtractor<@ExtractedValue(type = int.class) Personnummer> {
    @Override
    public void extractValues(Personnummer originalValue, ValueReceiver receiver) {
        int res = 0;
        if (originalValue == null) {
            res = -1;
        } else if (Integer.valueOf(originalValue.getAge()) == null) {
            res = -2;
        } else {
            res = originalValue.getAge();
        }
        receiver.value(null, res);
    }
}
