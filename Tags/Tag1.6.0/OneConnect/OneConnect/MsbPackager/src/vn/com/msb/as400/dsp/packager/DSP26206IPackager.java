// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP26206IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP26206IPackager extends DSPPackager
{

    public DSP26206IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(3, 0, "Description for field 1"), new DSPCharField(10, "Description for field 2"), new DSPCharField(4, "Description for field 3"), new DSPPackedField(6, 0, "Description for field 4")
        });
        addBody("26206I".toUpperCase(), fld);
    }
}
