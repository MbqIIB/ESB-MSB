// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP73213IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP73213IPackager extends DSPPackager
{

    public DSP73213IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(4, 0, "Description for field 1"), new DSPPackedField(4, 0, "Description for field 2"), new DSPCharField(1, "Description for field 3")
        });
        addBody("73213I".toUpperCase(), fld);
    }
}
