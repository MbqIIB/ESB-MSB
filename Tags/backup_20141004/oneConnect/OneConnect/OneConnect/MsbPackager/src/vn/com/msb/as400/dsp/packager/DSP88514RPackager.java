// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP88514RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP88514RPackager extends DSPPackager
{

    public DSP88514RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPPackedField(5, 0, "Description for field 3"), new DSPPackedField(8, 2, "Description for field 4"), new DSPPackedField(6, 9, "Description for field 5"), new DSPPackedField(5, 0, "Description for field 6"), new DSPPackedField(3, 0, "Description for field 7"), new DSPPackedField(6, 9, "Description for field 8"), new DSPCharField(1, "Description for field 9"), new DSPPackedField(6, 9, "Description for field 10"), 
            new DSPPackedField(6, 9, "Description for field 11"), new DSPPackedField(4, 0, "Description for field 12"), new DSPPackedField(4, 0, "Description for field 13"), new DSPPackedField(4, 0, "Description for field 14"), new DSPPackedField(2, 0, "Description for field 15"), new DSPPackedField(2, 0, "Description for field 16"), new DSPCharField(1, "Description for field 17")
        });
        addBody("88514R".toUpperCase(), fld);
    }
}
