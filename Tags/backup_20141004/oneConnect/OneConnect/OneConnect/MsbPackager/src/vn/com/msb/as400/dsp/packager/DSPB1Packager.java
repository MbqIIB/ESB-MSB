// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPB1Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPB1Packager extends DSPPackager
{

    public DSPB1Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPPackedField(10, 0, "Description for field 3"), new DSPCharField(1, "Description for field 4"), new DSPPackedField(10, 0, "Description for field 5"), new DSPPackedField(2, 0, "Description for field 6"), new DSPPackedField(9, 2, "Description for field 7"), new DSPPackedField(6, 0, "Description for field 8"), new DSPPackedField(3, 0, "Description for field 9")
        });
        addBody("B1".toUpperCase(), fld);
    }
}
