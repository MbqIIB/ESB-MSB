// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP85020RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP85020RPackager extends DSPPackager
{

    public DSP85020RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(3, "Description for field 1"), new DSPCharField(10, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPCharField(10, "Description for field 4"), new DSPCharField(4, "Description for field 5"), new DSPCharField(50, "Description for field 6"), new DSPCharField(1, "Description for field 7")
        });
        addBody("85020R".toUpperCase(), fld);
    }
}
