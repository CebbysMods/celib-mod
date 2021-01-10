package com.cebbys.celib.utilities

import java.lang.ClassCastException

inline fun <reified TOut> throwCast(v: Any?) = if (v is TOut) v else throw ClassCastException()