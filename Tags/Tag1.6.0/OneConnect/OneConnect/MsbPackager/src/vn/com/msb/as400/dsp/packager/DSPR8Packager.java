// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPR8Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPR8Packager extends DSPPackager
{

    public DSPR8Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(5, "Description for field 1"), new DSPCharField(35, "Description for field 2"), new DSPCharField(35, "Description for field 3"), new DSPCharField(35, "Description for field 4"), new DSPCharField(35, "Description for field 5"), new DSPCharField(35, "Description for field 6"), new DSPCharField(35, "Description for field 7")
        });
        addBody("R8".toUpperCase(), fld);
    }
}
