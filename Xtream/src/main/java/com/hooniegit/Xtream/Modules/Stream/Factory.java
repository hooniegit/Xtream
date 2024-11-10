package com.hooniegit.Xtream.Modules.Stream;

import com.hooniegit.Xtream.Modules.Stream.Sample.Two;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.BasePooledObjectFactory;

public class Factory extends BasePooledObjectFactory<Two> {
    @Override
    public Two create() {
        // Need To Fix Here Based On Your Custom Class Properties
        return new Two("", 0);
    }

    @Override
    public PooledObject<Two> wrap(Two two) {
        return new org.apache.commons.pool2.impl.DefaultPooledObject<>(two);
    }
}
