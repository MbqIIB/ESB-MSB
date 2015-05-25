// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP56115IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP56115IPackager extends DSPPackager
{

    public DSP56115IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPNumericField(6, "Description for field 1"), new DSPCharField(10, "Description for field 2"), new DSPPackedField(5, 0, "Description for field 3")
        });
        addBody("56115I".toUpperCase(), fld);
    }
}
