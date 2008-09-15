//  RGFontMap.java
//  RGFontMap
//
//  Created by Roman Glass on 01.09.08.
//  Copyright (c) 2008 Roman Glass. All rights reserved.

import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.Toolkit;

public class RGFontMap {
    private static final int defaultFontSize = 20;
    private static final String defaultSampleText = "0 123 456 789";
        
    public static void main(final String args[]) {
        new RGFontMapPreferences(defaultFontSize, defaultSampleText);
        new RGFontMapFrame(defaultFontSize,
                           defaultSampleText);
    }
}
