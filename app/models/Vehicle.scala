package models

/**
  * Created by Hervé Darritchon on 17/01/2016.
  *
  */
case class Vehicle(name: String,
                   model: String,
                   manufacturer: String,
                   cost_in_credits: String,
                   length: String,
                   max_atmosphering_speed: String,
                   crew: String,
                   passengers: String,
                   cargo_capacity: String,
                   consumables: String,
                   vehicle_class: String,
                   pilots: List[String],
                   films: List[String],
                   created: String,
                   edited: String,
                   url: String)

