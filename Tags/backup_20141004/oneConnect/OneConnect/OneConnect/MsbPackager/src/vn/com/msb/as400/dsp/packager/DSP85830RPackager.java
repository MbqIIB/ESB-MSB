// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP85830RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP85830RPackager extends DSPPackager
{

    public DSP85830RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(40, "Description for field 2"), new DSPPackedField(8, 2, "Description for field 3"), new DSPPackedField(7, 2, "Description for field 4"), new DSPPackedField(8, 2, "Description for field 5")
        });
        addBody("85830R".toUpperCase(), fld);
    }
}
