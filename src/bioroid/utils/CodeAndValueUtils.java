package bioroid.utils;

import java.util.Collection;

import bioroid.model.CodeAndValue;

public class CodeAndValueUtils {

    public static int getValue(String code, Collection<CodeAndValue> codeValues) {
        if (CollectionUtils.isNotEmpty(codeValues)) {
            for (CodeAndValue codeAndValue : codeValues) {
                if (StringUtils.equals(codeAndValue.getCode(), code)) {
                    return codeAndValue.getValue();
                }
            }
        }
        return 0;
    }

    public static void adjustValue(String code, int value, Collection<CodeAndValue> codeValues) {
        if (CollectionUtils.isNotEmpty(codeValues)) {
            for (CodeAndValue codeAndValue : codeValues) {
                if (StringUtils.equals(codeAndValue.getCode(), code)) {
                    codeAndValue.setValue(codeAndValue.getValue() + value);
                }
            }
        }
    }
}
