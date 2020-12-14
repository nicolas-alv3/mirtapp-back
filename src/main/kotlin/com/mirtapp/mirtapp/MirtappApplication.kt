package com.mirtapp.mirtapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
@SpringBootApplication
class MirtappApplication {
	@RequestMapping("/")
	@ResponseBody
	fun home(): String {
		return "Hello World!"
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(MirtappApplication::class.java, *args)
		}
	}
}
