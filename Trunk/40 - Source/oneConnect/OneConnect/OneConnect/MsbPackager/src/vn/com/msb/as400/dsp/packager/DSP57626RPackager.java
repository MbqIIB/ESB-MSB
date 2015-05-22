// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP57626RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP57626RPackager extends DSPPackager
{

    public DSP57626RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(3, 0, "Description for field 1"), new DSPCharField(16, "Description for field 2"), new DSPCharField(4, "Description for field 3"), new DSPPackedField(4, 0, "Description for field 4"), new DSPCharField(1, "Description for field 5"), new DSPPackedField(3, 0, "Description for field 6"), new DSPCharField(8, "Description for field 7"), new DSPPackedField(2, 0, "Description for field 8")
        });
        addBody("57626R".toUpperCase(), fld);
    }
}
