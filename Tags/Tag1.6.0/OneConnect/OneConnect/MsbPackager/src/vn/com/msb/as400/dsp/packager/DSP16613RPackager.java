// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP16613RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP16613RPackager extends DSPPackager
{

    public DSP16613RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPPackedField(10, 0, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPCharField(40, "Description for field 4"), new DSPCharField(40, "Description for field 5"), new DSPCharField(40, "Description for field 6"), new DSPCharField(40, "Description for field 7"), new DSPCharField(40, "Description for field 8"), new DSPCharField(3, "Description for field 9"), new DSPPackedField(1, 0, "Description for field 10"), 
            new DSPPackedField(1, 0, "Description for field 11")
        });
        addBody("16613R".toUpperCase(), fld);
    }
}
