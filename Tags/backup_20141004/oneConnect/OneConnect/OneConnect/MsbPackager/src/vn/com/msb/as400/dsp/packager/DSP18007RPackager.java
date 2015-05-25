// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP18007RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP18007RPackager extends DSPPackager
{

    public DSP18007RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(2, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(32, "Description for field 3"), new DSPCharField(20, "Description for field 4"), new DSPCharField(1, "Description for field 5")
        });
        addBody("18007R".toUpperCase(), fld);
    }
}
