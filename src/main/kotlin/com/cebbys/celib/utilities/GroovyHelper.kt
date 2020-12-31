package com.cebbys.celib.utilities

import groovy.lang.Binding
import groovy.lang.GroovyClassLoader
import groovy.lang.GroovyCodeSource
import groovy.lang.GroovyShell
import org.codehaus.groovy.control.CompilerConfiguration
import java.io.File

class GroovyHelper : GroovyShell {
    constructor(parent: ClassLoader? = null, binding: Binding? = Binding(), config: CompilerConfiguration = CompilerConfiguration.DEFAULT) : super(parent, binding, config)

    fun parseClassFile(path: String) = classLoader.parseClass(File(path))
    fun parseClassFile(file: File)   = classLoader.parseClass(file)
    fun parseClass(code: String)     = classLoader.parseClass(code)
    fun parseClass(code: GroovyCodeSource, shouldCacheSource: Boolean = code.isCachable) = classLoader.parseClass(code,  shouldCacheSource)

    companion object {
        fun parseFile(parent: ClassLoader?, path: String)                                                       = GroovyClassLoader(parent).parseClass(File(path))
        fun parseFile(parent: ClassLoader?, file: File)                                                         = GroovyClassLoader(parent).parseClass(file)
        fun parse(parent: ClassLoader?, code: String)                                                           = GroovyClassLoader(parent).parseClass(code)
        fun parse(parent: ClassLoader?, code: GroovyCodeSource, shouldCacheSource: Boolean = code.isCachable)   = GroovyClassLoader(parent).parseClass(code, shouldCacheSource)
    }
}