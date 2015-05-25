// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPF2Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPF2Packager extends DSPPackager
{

    public DSPF2Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPHexField(1, "Description for field 1"), new DSPFillerField(1, "Description for field 2"), new DSPCharField(35, "Description for field 3"), new DSPFillerField(1, "Description for field 4")
        });
        addBody("F2".toUpperCase(), fld);
    }
}
