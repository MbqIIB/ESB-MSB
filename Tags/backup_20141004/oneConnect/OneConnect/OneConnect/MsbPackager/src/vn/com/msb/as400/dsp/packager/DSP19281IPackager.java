// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP19281IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP19281IPackager extends DSPPackager
{

    public DSP19281IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(2, "Description for field 3"), new DSPPackedField(4, 0, "Description for field 4"), new DSPPackedField(4, 0, "Description for field 5")
        });
        addBody("19281I".toUpperCase(), fld);
    }
}