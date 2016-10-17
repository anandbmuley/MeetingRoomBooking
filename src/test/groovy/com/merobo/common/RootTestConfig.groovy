package com.merobo.common

import org.jmock.Mockery
import org.jmock.lib.legacy.ClassImposteriser
import org.testng.annotations.BeforeClass

class RootTestConfig {

    protected Mockery context

    @BeforeClass
    public void init(){
        context = new Mockery()
        // Below config is required for mocking class
        context.setImposteriser(ClassImposteriser.INSTANCE)
    }

}
