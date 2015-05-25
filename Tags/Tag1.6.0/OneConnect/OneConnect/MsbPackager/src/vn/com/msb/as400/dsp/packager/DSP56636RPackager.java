// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP56636RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP56636RPackager extends DSPPackager
{

    public DSP56636RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(3, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(4, "Description for field 3"), new DSPCharField(20, "Description for field 4"), new DSPCharField(16, "Description for field 5"), new DSPPackedField(5, 0, "Description for field 6"), new DSPPackedField(9, 2, "Description for field 7")
        });
        addBody("56636R".toUpperCase(), fld);
    }
}
