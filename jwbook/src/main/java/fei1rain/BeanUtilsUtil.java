/*
 * BeanUtilsUtil.java
 * 20091104
 */
package fei1rain;

import org.apache.commons.beanutils.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.*;

import java.lang.reflect.*;
import java.util.*;

/**
 * @author PCHB
 */
public class BeanUtilsUtil {

    private static final String SETTER_PREFIX = "set";
    private static final String GETTER_PREFIX = "get";
    private static final String GET_CLASS = "getClass";

    public static <T> T[] createArrayProperties(Class<T> t, String name, Map<String, String[]> map) {
        T[] rtn = (T[]) Array.newInstance(t, 0);

        List<Integer> indexes = new ArrayList<>();
        for (String key : map.keySet()) {
            String nameOpen = name + "[";
            if (key.startsWith(nameOpen)) {
                int indexOfNameOpenAndLength = key.indexOf(nameOpen) + nameOpen.length();
                int indexOfClose = key.indexOf("]", indexOfNameOpenAndLength);
                indexes.add(Integer.parseInt(key.substring(indexOfNameOpenAndLength, indexOfClose)));
            }
        }
        Integer maxIndexes = Collections.max(indexes);
        rtn = (T[]) Array.newInstance(t, maxIndexes + 1);
        try {
            for (int i = 0; i < rtn.length; i++) {
                rtn[i] = t.getDeclaredConstructor().newInstance();
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
        }

        return rtn;
    }

    public static <T> List<T> createListProperties(Class<T> t, String name, Map<String, String[]> map) {
        List<T> rtn = new ArrayList<>();

        List<Integer> indexes = new ArrayList<>();
        for (String key : map.keySet()) {
            String nameOpen = name + "[";
            if (key.startsWith(nameOpen)) {
                int indexOfNameOpenAndLength = key.indexOf(nameOpen) + nameOpen.length();
                int indexOfClose = key.indexOf("]", indexOfNameOpenAndLength);
                indexes.add(Integer.parseInt(key.substring(indexOfNameOpenAndLength, indexOfClose)));
            }
        }
        Integer maxIndexes = Collections.max(indexes);
        try {
            for (int i = 0; i <= maxIndexes; i++) {
                rtn.add(t.getDeclaredConstructor().newInstance());
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
        }

        return rtn;
    }

    public static void reversePopulate(Map<String, Object> map, Object orig) {
        List<String> gettableProperties = getGettableProperties(orig);
        for (String property : gettableProperties) {
            try {
                map.put(property, PropertyUtils.getProperty(orig, property));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            }
        }
    }

    public static void copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException iae) {
        } catch (InvocationTargetException ite) {
        }
    }

    public static void copyPropertiesInclude(Object dest, Object orig, String[] names) {
        for (String name : names) {
            try {
                PropertyUtils.setProperty(dest, name, PropertyUtils.getProperty(orig, name));
            } catch (IllegalAccessException iae) {
            } catch (InvocationTargetException ite) {
            } catch (NoSuchMethodException nsme) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void copyPropertiesIncludePrimitive(Object dest, Object orig) {
        List<String> gettableProperties = getGettableProperties(orig);
        List<String> settableProperties = getSettableProperties(dest);
        Collection<String> intersectionProperties = CollectionUtils.intersection(gettableProperties, settableProperties);
        for (String property : intersectionProperties) {
            if (isPrimitive(property, orig)) {
                try {
                    PropertyUtils.setProperty(dest, property, PropertyUtils.getProperty(orig, property));
                } catch (IllegalAccessException iae) {
                } catch (InvocationTargetException ite) {
                } catch (NoSuchMethodException nsme) {
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void copyPropertiesExclude(Object dest, Object orig, String[] names) {
        List<String> gettableProperties = getGettableProperties(orig);
        List<String> settableProperties = getSettableProperties(dest);
        Collection<String> intersectionProperties = CollectionUtils.intersection(gettableProperties, settableProperties);
        for (String property : intersectionProperties) {
            if (!ArrayUtils.contains(names, property)) {
                try {
                    PropertyUtils.setProperty(dest, property, PropertyUtils.getProperty(orig, property));
                } catch (IllegalAccessException iae) {
                } catch (InvocationTargetException ite) {
                } catch (NoSuchMethodException nsme) {
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void copyPropertiesExclude(Object dest, Object orig, String name) {
        List<String> gettableProperties = getGettableProperties(orig);
        List<String> settableProperties = getSettableProperties(dest);
        Collection<String> intersectionProperties = CollectionUtils.intersection(gettableProperties, settableProperties);
        for (String property : intersectionProperties) {
            if (!property.equals(name)) {
                try {
                    PropertyUtils.setProperty(dest, property, PropertyUtils.getProperty(orig, property));
                } catch (IllegalAccessException iae) {
                } catch (InvocationTargetException ite) {
                } catch (NoSuchMethodException nsme) {
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void copyPropertiesExcludeCollection(Object dest, Object orig) {
        List<String> gettableProperties = getGettableProperties(orig);
        List<String> settableProperties = getSettableProperties(dest);
        Collection<String> intersectionProperties = CollectionUtils.intersection(gettableProperties, settableProperties);
        for (String property : intersectionProperties) {
            if (!isCollection(property, orig)) {
                try {
                    PropertyUtils.setProperty(dest, property, PropertyUtils.getProperty(orig, property));
                } catch (IllegalAccessException iae) {
                } catch (InvocationTargetException ite) {
                } catch (NoSuchMethodException nsme) {
                }
            }
        }
    }

    private static List<Method> getGetterMethods(Object o) {
        List<Method> rtn = null;

        rtn = new ArrayList<Method>();
        Method[] methods = o.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith(GETTER_PREFIX) && !method.getName().equals(GET_CLASS)) {
                rtn.add(method);

            }
        }

        return rtn;
    }

    private static List<String> getGettableProperties(Object o) {
        List<String> rtn = null;

        rtn = new ArrayList<String>();
        List<Method> methods = getGetterMethods(o);
        for (Method method : methods) {
            String propertyName = method.getName().substring(GETTER_PREFIX.length());
            propertyName = StringUtils.uncapitalize(propertyName);
            rtn.add(propertyName);
        }

        return rtn;
    }

    private static List<Method> getSetterMethods(Object o) {
        List<Method> rtn = null;

        rtn = new ArrayList<Method>();
        Method[] methods = o.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith(SETTER_PREFIX)) {
                rtn.add(method);
            }
        }

        return rtn;
    }

    private static List<String> getSettableProperties(Object o) {
        List<String> rtn = null;

        rtn = new ArrayList<String>();
        List<Method> methods = getSetterMethods(o);
        for (Method method : methods) {
            String propertyName = method.getName().substring(SETTER_PREFIX.length());
            propertyName = StringUtils.uncapitalize(propertyName);
            rtn.add(propertyName);
        }

        return rtn;
    }

    private static boolean isCollection(String property, Object o) {
        boolean rtn = false;

        try {
            Object p = PropertyUtils.getProperty(o, property);
            if (o.getClass().isArray()) {
                rtn = true;
            }
            if (Collection.class.isInstance(p)) {
                rtn = true;
            }
            if (Map.class.isInstance(p)) {
                rtn = true;
            }
        } catch (IllegalAccessException iae) {
        } catch (InvocationTargetException ite) {
        } catch (NoSuchMethodException nsme) {
        }

        return rtn;
    }

    private static boolean isPrimitive(String property, Object o) {
        boolean rtn = false;

        try {
            Class c = PropertyUtils.getPropertyType(o, property);
            Object p = PropertyUtils.getProperty(o, property);

            if (c.isPrimitive()) {
                rtn = true;
            } else {
                if (Boolean.class.isInstance(p)) {
                    rtn = true;
                }
                if (Character.class.isInstance(p)) {
                    rtn = true;
                }
                if (Number.class.isInstance(p)) {
                    rtn = true;
                }
                if (String.class.isInstance(p)) {
                    rtn = true;
                }
                if (Date.class.isInstance(p)) {
                    rtn = true;
                }
            }
        } catch (IllegalAccessException iae) {
        } catch (InvocationTargetException ite) {
        } catch (NoSuchMethodException nsme) {
        }

        return rtn;
    }
}
