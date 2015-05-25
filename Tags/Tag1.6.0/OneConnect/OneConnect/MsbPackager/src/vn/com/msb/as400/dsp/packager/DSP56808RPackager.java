// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP56808RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP56808RPackager extends DSPPackager
{

    public DSP56808RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(40, "Description for field 3"), new DSPCharField(40, "Description for field 4"), new DSPCharField(40, "Description for field 5"), new DSPCharField(40, "Description for field 6"), new DSPCharField(40, "Description for field 7"), new DSPCharField(40, "Description for field 8"), new DSPCharField(16, "Description for field 9"), new DSPCharField(10, "Description for field 10"), 
            new DSPCharField(4, "Description for field 11"), new DSPPackedField(3, 0, "Description for field 12")
        });
        addBody("56808R".toUpperCase(), fld);
    }
}
