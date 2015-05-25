// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPD9Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPD9Packager extends DSPPackager
{

    public DSPD9Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPPackedField(10, 0, "Description for field 3"), new DSPPackedField(10, 0, "Description for field 4"), new DSPPackedField(10, 0, "Description for field 5"), new DSPPackedField(7, 2, "Description for field 6"), new DSPPackedField(7, 2, "Description for field 7"), new DSPPackedField(7, 2, "Description for field 8"), new DSPPackedField(7, 2, "Description for field 9")
        });
        addBody("D9".toUpperCase(), fld);
    }
}
