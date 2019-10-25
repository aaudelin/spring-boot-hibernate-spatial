package fr.aaudelin.geo.geoexample

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class GeoExampleApplication

fun main(args: Array<String>) {
	runApplication<GeoExampleApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}
