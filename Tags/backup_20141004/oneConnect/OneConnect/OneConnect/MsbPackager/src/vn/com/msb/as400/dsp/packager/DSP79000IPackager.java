// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP79000IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP79000IPackager extends DSPPackager
{

    public DSP79000IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(40, "Description for field 2"), new DSPCharField(40, "Description for field 3"), new DSPCharField(20, "Description for field 4"), new DSPCharField(1, "Description for field 5"), new DSPCharField(1, "Description for field 6"), new DSPCharField(8, "Description for field 7"), new DSPCharField(10, "Description for field 8"), new DSPCharField(8, "Description for field 9"), new DSPCharField(8, "Description for field 10"), 
            new DSPCharField(4, "Description for field 11"), new DSPCharField(1, "Description for field 12"), new DSPPackedField(3, 0, "Description for field 13"), new DSPPackedField(3, 0, "Description for field 14"), new DSPPackedField(3, 0, "Description for field 15"), new DSPPackedField(10, 0, "Description for field 16"), new DSPCharField(40, "Description for field 17"), new DSPCharField(8, "Description for field 18"), new DSPCharField(8, "Description for field 19"), new DSPCharField(8, "Description for field 20"), 
            new DSPCharField(8, "Description for field 21"), new DSPPackedField(10, 0, "Description for field 22"), new DSPCharField(8, "Description for field 23"), new DSPPackedField(10, 0, "Description for field 24"), new DSPCharField(8, "Description for field 25"), new DSPCharField(1, "Description for field 26"), new DSPCharField(4, "Description for field 27")
        });
        addBody("79000I".toUpperCase(), fld);
    }
}
