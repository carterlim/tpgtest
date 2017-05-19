package com.tpg;
import java.lang.reflect.Method;
import java.util.Comparator;

public class BeanComparator implements Comparator<Object> {
	private String[] field;
	private Class<?>[] paramClass;
	private Object[] paramVal;
	private boolean[] ascending;
	
	public BeanComparator(String[] field, boolean[] ascending) {
		this.field = field;
		this.ascending = ascending;
	}
	
	public BeanComparator(String[] field, Class<?>[] paramClass, Object[] paramVal, boolean[] ascending) {
		this.field = field;
		this.paramClass = paramClass;
		this.paramVal = paramVal;
		this.ascending = ascending;
	}
	
	public int compare(Object o1, Object o2) {
		int result = 0;
		
		try {
			if (o1.getClass() != o2.getClass()) {
				return 0;
			}
			
			if (o1.getClass().equals(String.class)) {
				o1 = isNumeric((String) o1)?Integer.parseInt((String) o1):o1;
				o2 = isNumeric((String) o2)?Integer.parseInt((String) o2):o2;
			}
	
			for (int i=0; i<field.length; i++) {
				Comparable<Object> comparable1 = null;
				if (this.paramClass == null)
					comparable1 = getValue(field[i], o1);
				else
					comparable1 = getValue(field[i], paramClass[i], paramVal[i], o1);
				
				Comparable<Object> comparable2 = null;
				if (this.paramClass == null)
					comparable2 = getValue(field[i], o2);
				else
					comparable2 = getValue(field[i], paramClass[i], paramVal[i], o2);
				
				if (ascending[i]) {
					if (comparable1 == null && comparable2 == null)
						result = 0;
					else if (comparable1 == null)
						result = -1;
					else if (comparable2 == null)
						result = 1;
					else
						result = comparable1.compareTo(comparable2);
	
					if (result != 0)
						break;
				} else {
					if (comparable1 == null && comparable2 == null)
						result = 0;
					else if (comparable2 == null)
						result = -1;
					else if (comparable1 == null)
						result = 1;
					else
						result = comparable2.compareTo(comparable1);
	
					if (result != 0)
						break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private Comparable<Object> getValue(String field, Object o) {
		try {
			String getter = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
			Method m = o.getClass().getMethod(getter, new Class<?>[0]);
			return (Comparable<Object>) m.invoke(o, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private Comparable<Object> getValue(String field, Class<?> paramClass, Object paramVal, Object o) {
		try {
			String getter = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
			Method m = o.getClass().getMethod(getter, paramClass);
			return (Comparable<Object>) m.invoke(o, paramVal);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
    private boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
	
    private boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}