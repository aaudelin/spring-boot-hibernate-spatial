package fr.aaudelin.geo.geoexample.entities

import org.locationtech.jts.geom.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Coordinate(
        @Id @GeneratedValue var id: Long? = null,
        var name: String,
        var wgs84: Point?
)

data class CoordinateResponse(
        var id: Long?,
        var name: String,
        var wgs84_x: Double?,
        var wgs84_y: Double?
)

@Controller
class CoordinateController {

    @Autowired lateinit var coordinateRepository: CoordinateRepository

    @GetMapping("/coordinate")
    @ResponseBody
    fun getCoordinate(): CoordinateResponse {
        val coordinate = coordinateRepository.getOne(1)
        return CoordinateResponse(coordinate.id, coordinate.name, coordinate.wgs84?.x, coordinate.wgs84?.y)
    }
}

interface CoordinateRepository: JpaRepository<Coordinate, Long>