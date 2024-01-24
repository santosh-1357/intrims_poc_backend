package common.library.constants.app;

import common.library.constants.app.FwConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FwUtils implements FwConstants {

    public static List<String> getListOfStringFromCommaSepartedString(String str) {
        if(isNotBlankOrNullString(str)) {
            return Arrays.asList(str.split("\\s*,\\s*"));
        }
        return null;
    }

    public static boolean hasId(Long pId) {
        return pId != null
                && pId.longValue() > 0;
    }

    public static boolean hasId(Integer pId) {
        return pId != null
                && pId.intValue() > 0;
    }

    public static boolean hasId(Short pId) {
        return pId != null
                && pId.shortValue() > 0;
    }

    public static boolean hasId(Float pId) {
        return pId != null
                && pId.longValue() > 0;
    }

    public static boolean hasStringId(String pValue) {
        return isNotBlankOrNullString(pValue);
    }

    public static String trim(String pValue) {
        if (isNotBlankOrNullString(pValue)) {
            return pValue.trim();
        }
        return pValue;
    }

    public static String trim(String pValue, int maxlength) {
        if (isNotBlankOrNullString(pValue)) {
            String str = pValue.trim();
            if (str.length() > maxlength) {
                return str.substring(0, maxlength);
            } else {
                return str;
            }
        }
        return pValue;
    }

    public static String toUpper(String pValue) {
        if (isNotBlankOrNullString(pValue)) {
            return pValue.trim().toUpperCase();
        }
        return pValue;
    }

    public static String toLower(String pValue) {
        if (isNotBlankOrNullString(pValue)) {
            return pValue.trim().toLowerCase();
        }
        return pValue;
    }

    public static boolean isNotBlankOrNullString(String pValue) {
        return StringUtils.isNotBlank(pValue)
                && !pValue.trim().equalsIgnoreCase(NULL_STRING);
    }

    public static boolean isBlankOrNull(String pValue) {
        return StringUtils.isBlank(pValue)
                || pValue.trim().equalsIgnoreCase(NULL_STRING);
    }

    public static boolean isYes(String pValue) {
        return StringUtils.isNotBlank(pValue)
                && pValue.trim().equalsIgnoreCase(Y_FLAG);
    }

    public static boolean isNo(String pValue) {
        return StringUtils.isNotBlank(pValue)
                && pValue.trim().equalsIgnoreCase(N_FLAG);
    }

    public static boolean match(String pValue1, String pValue2) {
        return StringUtils.equals(pValue1, pValue2);
    }

    public static boolean match(boolean pValue1, boolean pValue2) {
        return pValue1 == pValue2;
    }

    public static boolean match(Date pDate1, Date pDate2) {
        return (pDate1 == null && pDate2 == null)
                || (pDate1 != null && pDate2 != null
                && org.apache.commons.lang3.time.DateUtils.truncatedEquals(pDate1, pDate2, Calendar.DAY_OF_MONTH));
    }

    public static boolean match(Integer pValue1, Integer pValue2) {
        return (pValue1 == null && pValue2 == null)
                || (pValue1 != null && pValue2 != null
                && (pValue1.intValue() == pValue2.intValue()));
    }

    public static boolean match(Long pValue1, Long pValue2) {
        return (pValue1 == null && pValue2 == null)
                || (pValue1 != null && pValue2 != null
                && (pValue1.longValue() == pValue2.longValue()));
    }

    public static boolean match(Short pValue1, Short pValue2) {
        return (pValue1 == null && pValue2 == null)
                || (pValue1 != null && pValue2 != null
                && (pValue1.shortValue() == pValue2.shortValue()));
    }

    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null
                || collection.isEmpty();
    }

    public static boolean isNotNullOrNotEmpty(final Collection<?> collection) {
        return !isNullOrEmpty(collection);
    }

    public static <E, K> Map<K, List<E>> groupBy(List<E> list, Function<E, K> keyFunction) {
        return Optional.ofNullable(list).orElseGet(ArrayList::new).stream().collect(Collectors.groupingBy(keyFunction));
    }

    public static String[] splitByComma(String toMultiple) {
        String[] toSplit = null;
        if (isNotBlankOrNullString(toMultiple)) {
            toSplit = toMultiple.split(",");
        }
        return toSplit;
    }


    public static String[] split(String delimeter, String value) {
        String[] toSplit = null;
        if (isNotBlankOrNullString(delimeter) && isNotBlankOrNullString(value)) {
            toSplit = value.split(delimeter);
        }
        return toSplit;
    }
}
