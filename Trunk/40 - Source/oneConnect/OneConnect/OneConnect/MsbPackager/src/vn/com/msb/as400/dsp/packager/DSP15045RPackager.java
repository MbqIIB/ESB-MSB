// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP15045RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP15045RPackager extends DSPPackager
{

    public DSP15045RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(6, "Description for field 1"), new DSPCharField(40, "Description for field 2"), new DSPPackedField(10, 0, "Description for field 3")
        });
        addBody("15045R".toUpperCase(), fld);
    }
}
