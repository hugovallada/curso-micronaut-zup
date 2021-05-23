package com.github.hugovallada.casadocodigo

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.github.hugovallada.casadocodigo")
		.start()
}

