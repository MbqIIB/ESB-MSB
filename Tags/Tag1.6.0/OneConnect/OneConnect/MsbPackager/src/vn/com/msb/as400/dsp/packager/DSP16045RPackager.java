// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP16045RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP16045RPackager extends DSPPackager
{

    public DSP16045RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(6, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPCharField(40, "Description for field 4"), new DSPCharField(40, "Description for field 5"), new DSPCharField(40, "Description for field 6"), new DSPCharField(40, "Description for field 7"), new DSPCharField(40, "Description for field 8"), new DSPCharField(40, "Description for field 9"), new DSPCharField(40, "Description for field 10"), 
            new DSPCharField(1, "Description for field 11"), new DSPCharField(20, "Description for field 12"), new DSPCharField(3, "Description for field 13"), new DSPPackedField(5, 0, "Description for field 14"), new DSPCharField(3, "Description for field 15"), new DSPCharField(30, "Description for field 16"), new DSPPackedField(10, 0, "Description for field 17"), new DSPCharField(40, "Description for field 18"), new DSPCharField(3, "Description for field 19"), new DSPPackedField(5, 2, "Description for field 20"), 
            new DSPPackedField(4, 0, "Description for field 21"), new DSPPackedField(10, 0, "Description for field 22"), new DSPCharField(1, "Description for field 23")
        });
        addBody("16045R".toUpperCase(), fld);
    }
}
