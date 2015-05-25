// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP56105RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP56105RPackager extends DSPPackager
{

    public DSP56105RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPPackedField(9, 2, "Description for field 3"), new DSPPackedField(2, 0, "Description for field 4"), new DSPPackedField(9, 2, "Description for field 5"), new DSPPackedField(2, 0, "Description for field 6")
        });
        addBody("56105R".toUpperCase(), fld);
    }
}
