// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP57820IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP57820IPackager extends DSPPackager
{

    public DSP57820IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(4, "Description for field 3"), new DSPPackedField(6, 0, "Description for field 4"), new DSPPackedField(3, 0, "Description for field 5"), new DSPCharField(10, "Description for field 6"), new DSPCharField(2, "Description for field 7"), new DSPPackedField(4, 0, "Description for field 8"), new DSPCharField(3, "Description for field 9"), new DSPPackedField(7, 0, "Description for field 10"), 
            new DSPPackedField(7, 0, "Description for field 11"), new DSPPackedField(8, 0, "Description for field 12")
        });
        addBody("57820I".toUpperCase(), fld);
    }
}