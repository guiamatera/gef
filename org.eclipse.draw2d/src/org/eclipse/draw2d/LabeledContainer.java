/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.draw2d;

import org.eclipse.swt.graphics.Font;

/**
 * A Container with a title bar describing the contents of the container. The frame is 
 * generated by a {@link LabeledBorder}.
 */
public class LabeledContainer
	extends Figure
{

/**
 * Constructs a default container with a {@link GroupBoxBorder}.
 * 
 * @since 2.0
 */
public LabeledContainer() {
	this(new GroupBoxBorder());
}

/**
 * Constructs a labeled container with the border given as input.
 *
 * @param border the border
 * @since 2.0
 */
public LabeledContainer(Border border) {
	setBorder(border);
	setOpaque(true);
}

private static LabeledBorder findLabeledBorder(Border border) {
	if (border instanceof LabeledBorder)
		return (LabeledBorder)border;
	if (border instanceof CompoundBorder) {
		CompoundBorder cb = (CompoundBorder)border;
		LabeledBorder labeled = findLabeledBorder(cb.getInnerBorder());
		if (labeled == null)
			labeled = findLabeledBorder(cb.getOuterBorder());
		return labeled;
	}
	return null;
}

/**
 * Returns the text of the LabeledContainer's label.
 *
 * @return the label text
 * @since 2.0
 */
public String getLabel() {
	return getLabeledBorder().getLabel();
}

/**
 * Returns the LabeledBorder of this container.
 *
 * @return the border
 * @since 2.0
 */
protected LabeledBorder getLabeledBorder() {
	return findLabeledBorder(getBorder());
}

/**
 * Sets the title of the container.
 *
 * @param s the new title text
 * @since 2.0
 */
public void setLabel(String s) {
	getLabeledBorder().setLabel(s);
	revalidate();
	repaint();
}

/**
 * Sets the font to be used for the container title.
 *
 * @param f the new font
 * @since 2.0
 */
public void setLabelFont(Font f) {
	getLabeledBorder().setFont(f);
	revalidate();
	repaint();
}

}