// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP26004IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP26004IPackager extends DSPPackager
{

    public DSP26004IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(25, "Description for field 1"), new DSPCharField(25, "Description for field 2"), new DSPCharField(25, "Description for field 3"), new DSPCharField(1, "Description for field 4"), new DSPCharField(4, "Description for field 5")
        });
        addBody("26004I".toUpperCase(), fld);
    }
}