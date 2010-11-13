/*
 * Mutability Detector
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * 
 * Further licensing information for this project can be found in
 * license/LICENSE.txt
 */

package org.mutabilitydetector.checkers;

import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

/**
 * Used to check for the existence of an access flag used in ASM visitors.
 * 
 * The class is designed to be used in a fluent way, a typical usage would be:
 * 
 * <code>
 * 
 * {@link #method(access)}.isPrivate();
 * {@link #field(access)}.isStatic();
 * </code>
 * 
 */
public class AccessModifierQuery {
	private int access;

	private AccessModifierQuery(int access) {
		this.access = access;
	}

	private boolean includesAccess(int access) {
		return (this.access & access) != 0;
	}
	
	public static AccessModifierQuery method(int access) {
		return new AccessModifierQuery(access);
	}

	public static AccessModifierQuery type(int access) {
		return new AccessModifierQuery(access);
	}
	
	public static AccessModifierQuery field(int access) {
		return new AccessModifierQuery(access);
	}

	private boolean is(int flag) {
		return includesAccess(flag);
	}

	public boolean isPrivate() { return is(ACC_PRIVATE); }
	public boolean isNotPrivate() { return !is(ACC_PRIVATE); }

	public boolean isFinal() { return is(ACC_FINAL); }

	public boolean isAbstract() { return is(ACC_ABSTRACT); }

	public boolean isInterface() { return is(ACC_INTERFACE); }

	public boolean isStatic() { return is(ACC_STATIC); }
	public boolean isNotStatic() { return !is(ACC_STATIC); }

}
