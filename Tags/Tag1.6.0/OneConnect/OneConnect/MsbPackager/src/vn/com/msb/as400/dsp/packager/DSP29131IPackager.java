// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP29131IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP29131IPackager extends DSPPackager
{

    public DSP29131IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPPackedField(10, 0, "Description for field 3"), new DSPCharField(1, "Description for field 4"), new DSPPackedField(8, 2, "Description for field 5"), new DSPPackedField(8, 2, "Description for field 6"), new DSPCharField(1, "Description for field 7"), new DSPCharField(1, "Description for field 8"), new DSPPackedField(5, 2, "Description for field 9"), new DSPPackedField(8, 2, "Description for field 10"), 
            new DSPPackedField(8, 2, "Description for field 11")
        });
        addBody("29131I".toUpperCase(), fld);
    }
}
