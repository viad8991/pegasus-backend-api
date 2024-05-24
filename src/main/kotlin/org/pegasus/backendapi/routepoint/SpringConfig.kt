package org.pegasus.backendapi.routepoint

import org.pegasus.backendapi.routepoint.entertainment.EntertainmentService
import org.pegasus.backendapi.routepoint.hotel.IHotelService
import org.pegasus.backendapi.routepoint.hotel.impl.YandexTravelService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val hotelInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean<IHotelService> {
        YandexTravelService()
    }

    bean {
        EntertainmentService()
    }

}
