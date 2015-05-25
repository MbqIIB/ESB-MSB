// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP88311IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP88311IPackager extends DSPPackager
{

    public DSP88311IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(3, "Description for field 2"), new DSPPackedField(10, 0, "Description for field 3"), new DSPPackedField(8, 2, "Description for field 4"), new DSPPackedField(6, 9, "Description for field 5"), new DSPCharField(2, "Description for field 6"), new DSPPackedField(8, 2, "Description for field 7"), new DSPPackedField(8, 2, "Description for field 8"), new DSPPackedField(3, 0, "Description for field 9"), new DSPPackedField(6, 9, "Description for field 10")
        });
        addBody("88311I".toUpperCase(), fld);
    }
}
