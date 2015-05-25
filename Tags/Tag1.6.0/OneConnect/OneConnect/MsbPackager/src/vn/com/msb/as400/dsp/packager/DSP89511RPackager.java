// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP89511RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP89511RPackager extends DSPPackager
{

    public DSP89511RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(1, "Description for field 1"), new DSPPackedField(10, 0, "Description for field 2"), new DSPPackedField(3, 0, "Description for field 3")
        });
        addBody("89511R".toUpperCase(), fld);
    }
}
