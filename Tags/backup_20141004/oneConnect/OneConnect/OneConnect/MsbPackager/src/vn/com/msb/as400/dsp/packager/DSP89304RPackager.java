// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP89304RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP89304RPackager extends DSPPackager
{

    public DSP89304RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPCharField(3, "Description for field 2"), new DSPPackedField(10, 0, "Description for field 3"), new DSPPackedField(10, 0, "Description for field 4")
        });
        addBody("89304R".toUpperCase(), fld);
    }
}
