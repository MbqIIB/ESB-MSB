// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP86529RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP86529RPackager extends DSPPackager
{

    public DSP86529RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(3, 0, "Description for field 1"), new DSPCharField(4, "Description for field 2"), new DSPCharField(50, "Description for field 3"), new DSPPackedField(6, 9, "Description for field 4"), new DSPPackedField(6, 9, "Description for field 5"), new DSPPackedField(4, 0, "Description for field 6")
        });
        addBody("86529R".toUpperCase(), fld);
    }
}