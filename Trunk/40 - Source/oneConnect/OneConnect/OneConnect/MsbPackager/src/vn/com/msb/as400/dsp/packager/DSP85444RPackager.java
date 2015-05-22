// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP85444RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP85444RPackager extends DSPPackager
{

    public DSP85444RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(2, "Description for field 1"), new DSPPackedField(8, 2, "Description for field 2"), new DSPCharField(40, "Description for field 3"), new DSPCharField(1, "Description for field 4")
        });
        addBody("85444R".toUpperCase(), fld);
    }
}
