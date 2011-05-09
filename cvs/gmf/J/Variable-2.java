/*
 * <copyright>
 *
 * Copyright (c) 2005-2006 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.gmf.internal.xpand.expression;

/**
 * @author Sven Efftinge
 * @author Arno Haase
 */
public class Variable {

    private String name;

    private Object value;

    public Variable(final String name, final Object value) {
        if (name == null) {
			throw new IllegalArgumentException("name must not be null!");
		}
        this.name = name;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        result = PRIME * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
			return true;
		}
        if (obj == null) {
			return false;
		}
        if (getClass() != obj.getClass()) {
			return false;
		}
        final Variable other = (Variable) obj;
        if (name == null) {
            if (other.name != null) {
				return false;
			}
        } else if (!name.equals(other.name)) {
			return false;
		}
        if (value == null) {
            return other.value == null;
        } else {
			return value.equals(other.value);
		}
    }

    @Override
	public String toString () {
        return "Variable [" + name + "="+ value + "]";
    }
}
