// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP55115IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP55115IPackager extends DSPPackager
{

    public DSP55115IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPNumericField(6, "Description for field 1"), new DSPCharField(10, "Description for field 2"), new DSPPackedField(5, 0, "Description for field 3"), new DSPNumericField(5, "Description for field 4"), new DSPCharField(10, "Description for field 5"), new DSPCharField(1, "Description for field 6")
        });
        addBody("55115I".toUpperCase(), fld);
    }
}
