// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP23002IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP23002IPackager extends DSPPackager
{

    public DSP23002IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(1, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(1, "Description for field 3")
        });
        addBody("23002I".toUpperCase(), fld);
    }
}
