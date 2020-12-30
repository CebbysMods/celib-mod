package com.cebbys.celib.utilities

import groovy.lang.Binding
import groovy.lang.GroovyShell
import org.codehaus.groovy.control.CompilerConfiguration

class GroovyHelper : GroovyShell {
    constructor(parent: ClassLoader? = null, binding: Binding? = null, config: CompilerConfiguration) : super(parent, binding, config)
}