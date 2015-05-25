// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP86720RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP86720RPackager extends DSPPackager
{

    public DSP86720RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(5, "Description for field 1"), new DSPCharField(3, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPPackedField(10, 0, "Description for field 4"), new DSPCharField(1, "Description for field 5"), new DSPPackedField(6, 9, "Description for field 6"), new DSPCharField(20, "Description for field 7"), new DSPPackedField(6, 9, "Description for field 8"), new DSPPackedField(10, 0, "Description for field 9"), new DSPCharField(4, "Description for field 10"), 
            new DSPCharField(1, "Description for field 11"), new DSPCharField(50, "Description for field 12"), new DSPCharField(40, "Description for field 13"), new DSPCharField(40, "Description for field 14"), new DSPPackedField(10, 0, "Description for field 15"), new DSPPackedField(1, 0, "Description for field 16"), new DSPCharField(10, "Description for field 17"), new DSPCharField(20, "Description for field 18")
        });
        addBody("86720R".toUpperCase(), fld);
    }
}
