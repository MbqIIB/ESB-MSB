// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP56622RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP56622RPackager extends DSPPackager
{

    public DSP56622RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(10, "Description for field 3"), new DSPCharField(20, "Description for field 4"), new DSPCharField(2, "Description for field 5"), new DSPPackedField(9, 2, "Description for field 6"), new DSPPackedField(4, 0, "Description for field 7"), new DSPCharField(40, "Description for field 8"), new DSPPackedField(10, 0, "Description for field 9"), new DSPCharField(1, "Description for field 10"), 
            new DSPCharField(12, "Description for field 11"), new DSPCharField(40, "Description for field 12"), new DSPCharField(12, "Description for field 13"), new DSPCharField(40, "Description for field 14"), new DSPPackedField(3, 0, "Description for field 15"), new DSPCharField(1, "Description for field 16"), new DSPCharField(1, "Description for field 17"), new DSPCharField(1, "Description for field 18"), new DSPCharField(1, "Description for field 19"), new DSPCharField(1, "Description for field 20")
        });
        addBody("56622R".toUpperCase(), fld);
    }
}
