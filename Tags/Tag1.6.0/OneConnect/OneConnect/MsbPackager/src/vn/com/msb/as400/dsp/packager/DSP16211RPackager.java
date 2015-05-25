// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP16211RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP16211RPackager extends DSPPackager
{

    public DSP16211RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(12, "Description for field 1"), new DSPCharField(4, "Description for field 2"), new DSPCharField(25, "Description for field 3"), new DSPPackedField(3, 0, "Description for field 4"), new DSPPackedField(10, 0, "Description for field 5"), new DSPPackedField(3, 0, "Description for field 6"), new DSPPackedField(3, 0, "Description for field 7")
        });
        addBody("16211R".toUpperCase(), fld);
    }
}
