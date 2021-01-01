package com.cebbys.celib.utilities

import groovy.lang.Binding
import groovy.lang.GroovyClassLoader
import groovy.lang.GroovyCodeSource
import groovy.lang.GroovyShell
import net.minecraft.util.Pair
import org.codehaus.groovy.control.CompilerConfiguration
import java.io.File

class GroovyHelper : GroovyShell {
    constructor(parent: ClassLoader? = null, binding: Binding? = Binding(), config: CompilerConfiguration = CompilerConfiguration.DEFAULT) : super(parent, binding, config)

    /**
     * @param props Array of pairs of names and values
     */
    fun setProperties(props: Array<Pair<String, Any?>>) = props.forEach{ element -> setProperty(element.left, element.right) }

    /**
     * @param names Array with names
     */
    fun removeProperties(names: Array<String>) = names.forEach { name -> context.variables.remove(name) }

    /**
     * @param names Array with names
     */
    fun getProperties(names: Array<String>): ArrayList<Any?> {
        val values = ArrayList<Any?>()
        names.forEach { name -> values.add(getProperty(name)) }
        return values
    }

    /**
     * @param name Name of object for removing
     */
    fun removeProperty(name: String) = context.variables.remove(name)

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