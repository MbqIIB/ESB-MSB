// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP75510RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP75510RPackager extends DSPPackager
{

    public DSP75510RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPPackedField(3, 0, "Description for field 2"), new DSPPackedField(3, 0, "Description for field 3"), new DSPPackedField(3, 0, "Description for field 4"), new DSPCharField(4, "Description for field 5"), new DSPCharField(4, "Description for field 6"), new DSPCharField(40, "Description for field 7"), new DSPPackedField(9, 2, "Description for field 8"), new DSPCharField(2, "Description for field 9")
        });
        addBody("75510R".toUpperCase(), fld);
    }
}
