// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP86525RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP86525RPackager extends DSPPackager
{

    public DSP86525RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(2, "Description for field 1"), new DSPPackedField(4, 0, "Description for field 2"), new DSPCharField(50, "Description for field 3"), new DSPPackedField(4, 0, "Description for field 4"), new DSPPackedField(4, 0, "Description for field 5"), new DSPPackedField(8, 2, "Description for field 6"), new DSPPackedField(10, 0, "Description for field 7"), new DSPCharField(1, "Description for field 8"), new DSPPackedField(10, 0, "Description for field 9"), new DSPCharField(3, "Description for field 10"), 
            new DSPPackedField(10, 0, "Description for field 11"), new DSPPackedField(8, 2, "Description for field 12")
        });
        addBody("86525R".toUpperCase(), fld);
    }
}
