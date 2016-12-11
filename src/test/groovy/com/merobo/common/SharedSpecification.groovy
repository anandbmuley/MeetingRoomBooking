package com.merobo.common

import spock.lang.Specification

class SharedSpecification extends Specification{

    def setup(){
        enableStrictMocking()
    }

    def enableStrictMocking(){
        0 * _
    }

}
