// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPV2F2DSPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPV2F2DSPackager extends DSPPackager
{

    public DSPV2F2DSPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(1, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(35, "Description for field 3"), new DSPCharField(1, "Description for field 4")
        });
        addBody("V2F2DS".toUpperCase(), fld);
    }
}
