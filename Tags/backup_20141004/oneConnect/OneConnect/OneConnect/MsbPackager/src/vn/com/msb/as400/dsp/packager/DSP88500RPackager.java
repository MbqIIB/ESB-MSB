// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP88500RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP88500RPackager extends DSPPackager
{

    public DSP88500RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPPackedField(6, 9, "Description for field 3"), new DSPPackedField(3, 0, "Description for field 4"), new DSPPackedField(6, 9, "Description for field 5"), new DSPCharField(1, "Description for field 6"), new DSPPackedField(6, 9, "Description for field 7"), new DSPPackedField(6, 9, "Description for field 8"), new DSPPackedField(3, 0, "Description for field 9"), new DSPCharField(1, "Description for field 10"), 
            new DSPPackedField(4, 0, "Description for field 11"), new DSPPackedField(4, 0, "Description for field 12"), new DSPCharField(1, "Description for field 13"), new DSPCharField(1, "Description for field 14"), new DSPCharField(1, "Description for field 15"), new DSPPackedField(4, 0, "Description for field 16")
        });
        addBody("88500R".toUpperCase(), fld);
    }
}
