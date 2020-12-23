package com.mirtapp.mirtapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller



@SpringBootApplication
class MirtappApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(MirtappApplication::class.java, *args)
		}
	}
}
