// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP25002RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP25002RPackager extends DSPPackager
{

    public DSP25002RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPPackedField(3, 0, "Description for field 2"), new DSPCharField(40, "Description for field 3"), new DSPPackedField(3, 0, "Description for field 4")
        });
        addBody("25002R".toUpperCase(), fld);
    }
}