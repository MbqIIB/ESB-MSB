// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP55633RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP55633RPackager extends DSPPackager
{

    public DSP55633RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(3, 0, "Description for field 1"), new DSPCharField(16, "Description for field 2"), new DSPCharField(4, "Description for field 3"), new DSPCharField(20, "Description for field 4"), new DSPPackedField(10, 0, "Description for field 5"), new DSPCharField(1, "Description for field 6"), new DSPCharField(16, "Description for field 7"), new DSPCharField(2, "Description for field 8"), new DSPCharField(10, "Description for field 9"), new DSPPackedField(8, 2, "Description for field 10"), 
            new DSPCharField(1, "Description for field 11"), new DSPPackedField(3, 0, "Description for field 12"), new DSPCharField(8, "Description for field 13"), new DSPPackedField(2, 0, "Description for field 14")
        });
        addBody("55633R".toUpperCase(), fld);
    }
}