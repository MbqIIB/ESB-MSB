// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP25003RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP25003RPackager extends DSPPackager
{

    public DSP25003RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(3, 0, "Description for field 1"), new DSPPackedField(10, 0, "Description for field 2"), new DSPCharField(4, "Description for field 3"), new DSPCharField(40, "Description for field 4")
        });
        addBody("25003R".toUpperCase(), fld);
    }
}
