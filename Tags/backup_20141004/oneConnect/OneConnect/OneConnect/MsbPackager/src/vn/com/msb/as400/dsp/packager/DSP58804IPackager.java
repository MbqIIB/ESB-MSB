// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP58804IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP58804IPackager extends DSPPackager
{

    public DSP58804IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPNumericField(5, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPNumericField(7, "Description for field 3"), new DSPCharField(1, "Description for field 4")
        });
        addBody("58804I".toUpperCase(), fld);
    }
}
